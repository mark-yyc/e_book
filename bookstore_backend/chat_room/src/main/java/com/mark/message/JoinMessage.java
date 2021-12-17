package com.mark.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@EqualsAndHashCode(callSuper = true)
@Data
public class JoinMessage extends Message{
    private String username;

    public JoinMessage(String username) {
        this.username = username;
    }
}
