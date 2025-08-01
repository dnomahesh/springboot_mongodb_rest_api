package work.mywork.scm.spring_boot.entity;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_messages")
public class ChatMessage {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private String status; // SENT, DELIVERED, SEEN
    private boolean isReply;
    private String replyToMessageId;
    private boolean isForwarded;
    private Instant timestamp;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isReply() {
        return isReply;
    }
    public void setReply(boolean isReply) {
        this.isReply = isReply;
    }
    public String getReplyToMessageId() {
        return replyToMessageId;
    }
    public void setReplyToMessageId(String replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }
    public boolean isForwarded() {
        return isForwarded;
    }
    public void setForwarded(boolean isForwarded) {
        this.isForwarded = isForwarded;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public ChatMessage(String id, String senderId, String receiverId, String content, String status, boolean isReply,
            String replyToMessageId, boolean isForwarded, Instant timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.status = status;
        this.isReply = isReply;
        this.replyToMessageId = replyToMessageId;
        this.isForwarded = isForwarded;
        this.timestamp = timestamp;
    }
    public ChatMessage() {
    }
    @Override
    public String toString() {
        return "ChatMessage [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", content="
                + content + ", status=" + status + ", isReply=" + isReply + ", replyToMessageId=" + replyToMessageId
                + ", isForwarded=" + isForwarded + ", timestamp=" + timestamp + ", getId()=" + getId()
                + ", getSenderId()=" + getSenderId() + ", getReceiverId()=" + getReceiverId() + ", getContent()="
                + getContent() + ", getStatus()=" + getStatus() + ", isReply()=" + isReply() + ", getClass()="
                + getClass() + ", getReplyToMessageId()=" + getReplyToMessageId() + ", isForwarded()=" + isForwarded()
                + ", getTimestamp()=" + getTimestamp() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    
    
}