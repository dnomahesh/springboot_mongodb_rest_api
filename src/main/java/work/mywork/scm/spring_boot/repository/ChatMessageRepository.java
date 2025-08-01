package work.mywork.scm.spring_boot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import work.mywork.scm.spring_boot.entity.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findBySenderIdOrReceiverId(String senderId, String receiverId);
}