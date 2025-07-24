package work.mywork.scm.spring_boot.exception;

import java.time.LocalDateTime;

public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ApiError() {
    }

    @Override
    public String toString() {
        return "ApiError [status=" + status + ", message=" + message + ", timestamp=" + timestamp + ", getStatus()="
                + getStatus() + ", getMessage()=" + getMessage() + ", getTimestamp()=" + getTimestamp()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

    
}

