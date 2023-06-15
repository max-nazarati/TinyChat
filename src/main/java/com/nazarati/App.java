package com.nazarati;

import com.nazarati.config.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@SpringBootApplication
public class App
{
    @Autowired
    MessageProcessor processor;

    public static void main(String[] args) throws UnknownHostException
    {
        SpringApplication.run(App.class, args);
    }
}
