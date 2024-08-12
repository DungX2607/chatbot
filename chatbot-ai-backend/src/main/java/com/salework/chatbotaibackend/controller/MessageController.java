package com.salework.chatbotaibackend.controller;

import com.salework.chatbotaibackend.Entity.Message;
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
    public ResponseEntity<Void> sendMessage(@RequestBody Map<String, String> request) {
        messageService.saveUserMessage(request.get("message"));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
