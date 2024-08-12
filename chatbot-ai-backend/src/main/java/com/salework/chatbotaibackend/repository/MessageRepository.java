package com.salework.chatbotaibackend.repository;

import com.salework.chatbotaibackend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
