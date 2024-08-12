package com.salework.chatbotaibackend.service;

import com.salework.chatbotaibackend.Entity.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    void saveUserMessage(String message);
    void sendBotMessage();
    List<Message> getAllMessages();
}
