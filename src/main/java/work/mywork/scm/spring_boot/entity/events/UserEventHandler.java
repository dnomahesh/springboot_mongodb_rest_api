package work.mywork.scm.spring_boot.entity.events;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import work.mywork.scm.spring_boot.entity.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler { 
 
    private org.slf4j.Logger log = LoggerFactory.getLogger(UserEventHandler.class);
    
    // @HandleBeforeSave
    // public void handleBeforeSave(User user) {
    //     user.setId(UUID.randomUUID().toString());
    // }
    
    @HandleBeforeCreate
    public void handleBeforeCreate(User user) {
            user.setId(UUID.randomUUID().toString());
        log.info("going to create user -> "+user.getName());
    }
}
