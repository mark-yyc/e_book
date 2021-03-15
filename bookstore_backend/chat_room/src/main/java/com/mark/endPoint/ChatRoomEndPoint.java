package com.mark.endPoint;

import com.mark.ChatRoomApplication;
import com.mark.decoder.MessageDecoder;
import com.mark.encoder.ChatMessageEncoder;
import com.mark.encoder.JoinMessageEncoder;
import com.mark.encoder.UserMessageEncoder;
import com.mark.message.ChatMessage;
import com.mark.message.JoinMessage;
import com.mark.message.Message;
import com.mark.message.UserMessage;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(
        value="/chatRoom",
        decoders = {MessageDecoder.class},
        encoders={ChatMessageEncoder.class, JoinMessageEncoder.class,
                UserMessageEncoder.class}
)
@Component
public class ChatRoomEndPoint {

    public static final Logger logger=Logger.getLogger("ChatRoomEndPoint");

    @OnOpen
    public void onOpen(Session session){
        logger.log(Level.INFO,"Connection opened: {0}",session.toString());
    }

    @OnMessage
    public void onMessage(final Session session, Message message){
        logger.log(Level.INFO,"Received : {0}",message.toString());
        if(message instanceof ChatMessage){
            ChatMessage chatMessage = (ChatMessage) message;
            if(chatMessage.getSecret().equals("public")){
                sendAll(session, chatMessage);
            }else{
                sendOne(session,chatMessage.getUsername(),chatMessage);
                sendOne(session,chatMessage.getTarget(),chatMessage);
            }
        }else if(message instanceof JoinMessage){
            JoinMessage joinMessage=(JoinMessage) message;
            session.getUserProperties().put("name",joinMessage.getUsername());
            session.getUserProperties().put("active",true);
            sendAll(session,joinMessage);
            sendAll(session,new UserMessage(this.getUserList(session)));
        }
    }

    @OnClose
    public void onClose(Session session){
        logger.log(Level.INFO,"Connection closed: {0}",session.toString());
        session.getUserProperties().put("active",false);
        sendAll(session,new UserMessage(this.getUserList(session)));
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        logger.log(Level.WARNING,"Connection error: {0}",session.toString());
    }

    private synchronized void sendAll(Session session,Object msg){
        try{
            for (Session s:session.getOpenSessions()){
                if(s.isOpen()&& (boolean) s.getUserProperties().get("active")){
                    s.getBasicRemote().sendObject(msg);
                }
            }
        } catch (EncodeException | IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getUserList(Session session){
        List<String> userList=new ArrayList<>();
        for(Session s:session.getOpenSessions()){
            if(s.isOpen()&& (boolean) s.getUserProperties().get("active"))
                userList.add(s.getUserProperties().get("name").toString());
        }
        return userList;
    }

    private synchronized void sendOne(Session session,String username,Object msg){
        try{
            for (Session s:session.getOpenSessions()){
                if(s.isOpen()&&
                        (boolean) s.getUserProperties().get("active")&&
                        s.getUserProperties().get("name").equals(username)){
                    s.getBasicRemote().sendObject(msg);
                }
            }
        } catch (EncodeException | IOException e) {
            e.printStackTrace();
        }
    }
}
