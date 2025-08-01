package work.mywork.scm.spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import work.mywork.scm.spring_boot.entity.ChatMessage;
import work.mywork.scm.spring_boot.repository.ChatMessageRepository;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatRepo;

    @GetMapping("/history/{userId}")
    public List<ChatMessage> getChatHistory(@PathVariable String userId) {
        return chatRepo.findBySenderIdOrReceiverId(userId, userId);
    }

    @PutMapping("/seen/{messageId}")
    public ResponseEntity<?> markAsSeen(@PathVariable String messageId) {
        ChatMessage message = chatRepo.findById(messageId).orElse(null);
        if (message == null) return ResponseEntity.notFound().build();
        message.setStatus("SEEN");
        chatRepo.save(message);
        return ResponseEntity.ok().build();
    }
}