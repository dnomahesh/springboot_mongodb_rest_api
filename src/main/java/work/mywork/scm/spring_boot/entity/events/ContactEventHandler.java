package work.mywork.scm.spring_boot.entity.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import work.mywork.scm.spring_boot.entity.contact.Contact;

@Component
@RepositoryEventHandler(Contact.class)
public class ContactEventHandler  {

    private Logger log = LoggerFactory.getLogger(ContactEventHandler.class);

    // @HandleBeforeSave
    // public void handleBeforeSave(Contact contact) {
    //     contact.setId(UUID.randomUUID().toString());
    // }

    @HandleBeforeCreate
    public void handleBeforeCreate(Contact contact){
        contact.setId(UUID.randomUUID().toString());
        log.info("contact is being created. ->"+contact.getName());
    }

}
