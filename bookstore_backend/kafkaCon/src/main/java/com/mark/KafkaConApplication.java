package com.mark;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConApplication {
    public static Gson gson=new Gson();
    public static void main(String[] args){
        SpringApplication.run(KafkaConApplication.class, args);
    }
}
