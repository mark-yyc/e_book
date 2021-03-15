package com.mark.encoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mark.ChatRoomApplication;
import com.mark.message.UserMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

public class UserMessageEncoder implements Encoder.Text<UserMessage>{
    @Override
    public String encode(UserMessage userMessage) throws EncodeException {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("type","user");
        JsonArray jsonArray=ChatRoomApplication.gson.toJsonTree(userMessage.getUserList(),
                new TypeToken<List<String>>(){}.getType()).getAsJsonArray();
        jsonObject.add("userList",jsonArray);
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
