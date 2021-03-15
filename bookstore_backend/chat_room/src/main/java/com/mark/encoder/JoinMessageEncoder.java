package com.mark.encoder;

import com.google.gson.JsonObject;
import com.mark.message.JoinMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JoinMessageEncoder implements Encoder.Text<JoinMessage> {
    @Override
    public String encode(JoinMessage joinMessage) throws EncodeException {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("type","join");
        jsonObject.addProperty("username",joinMessage.getUsername());
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
