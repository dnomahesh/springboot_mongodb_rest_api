package work.mywork.scm.spring_boot.entity.events;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import work.mywork.scm.spring_boot.entity.User;
import work.mywork.scm.spring_boot.exception.DuplicateResourceException;
import work.mywork.scm.spring_boot.repository.UserRepository;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler { 
 
    private org.slf4j.Logger log = LoggerFactory.getLogger(UserEventHandler.class);
    private final UserRepository userRepository;

    
    @Autowired
    public UserEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        user.setId(UUID.randomUUID().toString());
        log.info("Going to create user -> " + user.getName());
    }
}
