package com.mark.encoder;

import com.google.gson.JsonObject;
import com.mark.message.ChatMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>{

    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("type","chat");
        jsonObject.addProperty("username", chatMessage.getUsername());
        jsonObject.addProperty("message", chatMessage.getMessage());
        jsonObject.addProperty("target", chatMessage.getTarget());
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
