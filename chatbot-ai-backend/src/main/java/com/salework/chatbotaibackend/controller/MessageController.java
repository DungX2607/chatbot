package com.salework.chatbotaibackend.controller;

import com.salework.chatbotaibackend.entity.Message;
import com.salework.chatbotaibackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody Map<String, String> request) {
        messageService.saveUserMessage(request.get("message"));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/bot")
    public ResponseEntity<Message> getBotResponse() {
        Message botMessage = messageService.getBotResponse();
        if (botMessage != null) {
            return ResponseEntity.ok(botMessage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
