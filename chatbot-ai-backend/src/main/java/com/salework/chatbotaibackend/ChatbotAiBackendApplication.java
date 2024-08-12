package com.salework.chatbotaibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatbotAiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatbotAiBackendApplication.class, args);
    }

}
