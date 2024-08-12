//package com.salework.chatbotaibackend.utils;
//
//import com.salework.chatbotaibackend.Entity.Message;
//import com.salework.chatbotaibackend.Repository.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class ScheduledTasks {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//    @Scheduled(fixedRate = 30000) // Run each 30s
//    public void sendScheduledMessage() {
//        String message = redisTemplate.opsForValue().get("scheduled_message");
//        if (message != null) {
//            Message botMessage = new Message();
//            botMessage.setContent("Bot response to: " + message);
//            botMessage.setFromUser(false);
//            botMessage.setTimestamp(LocalDateTime.now());
//            messageRepository.save(botMessage);
//            redisTemplate.delete("scheduled_message");
//        }
//    }
//}
