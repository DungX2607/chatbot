package com.salework.chatbotaibackend.Repository;

import com.salework.chatbotaibackend.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
