package com.mark;

import com.google.gson.Gson;
import com.mark.service.BookDetailService;
import com.mark.serviceImpl.BookDetailServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class BookstoreApplication {
    public static Gson gson=new Gson();
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        ApplicationContext applicationContext=SpringApplication.run(BookstoreApplication.class, args);
        LocateRegistry.createRegistry(1009);
        BookDetailService bookDetailService= applicationContext.getBean(BookDetailServiceImpl.class);
        Naming.bind("rmi://127.0.0.1:1009/bookDetailService" , bookDetailService);

    }
}
