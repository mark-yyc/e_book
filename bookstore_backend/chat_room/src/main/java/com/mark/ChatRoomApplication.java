package com.mark;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class ChatRoomApplication {
    public static Gson gson=new Gson();

    public static void main(String[] args){
        SpringApplication.run(ChatRoomApplication.class,args);
    }
}
