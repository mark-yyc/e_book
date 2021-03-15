package com.mark.decoder;

import com.google.gson.reflect.TypeToken;
import com.mark.ChatRoomApplication;
import com.mark.message.ChatMessage;
import com.mark.message.JoinMessage;
import com.mark.message.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageDecoder implements Decoder.Text<Message>{
    private Map<String,String> messageMap;

    @Override
    public Message decode(String s) throws DecodeException {
        Message msg=null;
        if(willDecode(s)){
            switch (messageMap.get("type")){
                case "chat":
                    msg=new ChatMessage(
                            messageMap.get("username"),
                            messageMap.get("message"),
                            messageMap.get("secret"),
                            messageMap.get("target")
                    );
                    break;
                case "join":
                    msg=new JoinMessage(messageMap.get("username"));
            }
        }else{
            throw new DecodeException(s,"[Message] Can't decode .");
        }
        return msg;
    }

    @Override
    public boolean willDecode(String s) {
        boolean flag=false;
        messageMap=new HashMap<>();
        messageMap= ChatRoomApplication.gson.fromJson(s,new TypeToken<Map<String,String>>(){}.getType());
        Set<String> keys=messageMap.keySet();
        if(keys.contains("type")){
            switch (messageMap.get("type")) {
                case "chat":
                    String[] chatMsgKeys = {"username", "message","secret"};
                    if (keys.containsAll(Arrays.asList(chatMsgKeys)))
                        flag = true;
                    break;
                case  "join":
                    if(keys.contains("username"))
                        flag=true;
                    break;
            }
        }
        return flag;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
