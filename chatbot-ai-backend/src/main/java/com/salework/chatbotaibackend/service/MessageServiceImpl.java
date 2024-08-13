package com.salework.chatbotaibackend.service;

import com.salework.chatbotaibackend.entity.Message;
import com.salework.chatbotaibackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void saveUserMessage(String message) {
        Message newMessage = new Message();
        newMessage.setContent(message);
        newMessage.setSender("user");
        newMessage.setTimestamp(LocalDateTime.now());
        messageRepository.save(newMessage);

        // Save to Redis with TTL of 30 seconds
        redisTemplate.opsForValue().set("userMessage", message, 16, TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(fixedRate = 15000)
    public void sendBotMessage() {
        String userMessage = redisTemplate.opsForValue().get("userMessage");
        if (userMessage != null) {
            Message responseMessage = new Message();
            responseMessage.setContent("Bot response to: " + userMessage);
            responseMessage.setSender("bot");
            responseMessage.setTimestamp(LocalDateTime.now());
            messageRepository.save(responseMessage);

            // Save to Redis with TTL of 30 seconds
            redisTemplate.opsForValue().set("botMessage", "Bot response to: " + userMessage, 16, TimeUnit.SECONDS);

            // Delete when bot message is sent
            redisTemplate.delete("userMessage");
        }
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getBotResponse() {
        String botMessage = redisTemplate.opsForValue().get("botMessage");
        if (botMessage != null) {
            Message responseMessage = new Message();
            responseMessage.setContent(botMessage);
            responseMessage.setSender("bot");
            responseMessage.setTimestamp(LocalDateTime.now());
            return responseMessage;
        }
        return null;
    }
}
