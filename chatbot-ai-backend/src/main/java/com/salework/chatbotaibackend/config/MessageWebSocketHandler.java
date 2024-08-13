package com.salework.chatbotaibackend.config;
import com.salework.chatbotaibackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class MessageWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private MessageService messageService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String userMessage = message.getPayload();
        messageService.saveUserMessage(userMessage);

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                String botResponse = "Bot response to: " + userMessage;
                messageService.getBotResponse();
                session.sendMessage(new TextMessage(botResponse));
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
