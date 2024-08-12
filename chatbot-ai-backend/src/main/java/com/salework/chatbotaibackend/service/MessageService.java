package com.salework.chatbotaibackend.service;

import com.salework.chatbotaibackend.entity.Message;

import java.util.List;

public interface MessageService {
    void saveUserMessage(String message);
    void sendBotMessage();
    List<Message> getAllMessages();
}
