package com.mark.message;

import lombok.Data;

import java.util.List;

@Data
public class UserMessage extends Message{
    private List<String> userList;

    public UserMessage(List<String> userList){
        this.userList=userList;
    }
}
