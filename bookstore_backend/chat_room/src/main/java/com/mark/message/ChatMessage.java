package com.mark.message;

import lombok.Data;
import lombok.NonNull;

//@EqualsAndHashCode(callSuper = true)
@Data
public class ChatMessage extends Message{
    @NonNull
    private String username;
    @NonNull
    private String message;
    @NonNull
    private String secret;
    private String target;

    public ChatMessage(@NonNull String username, @NonNull String message, @NonNull String secret, String targetName) {
        this.username = username;
        this.message = message;
        this.target = targetName;
        this.secret=secret;
    }
}
