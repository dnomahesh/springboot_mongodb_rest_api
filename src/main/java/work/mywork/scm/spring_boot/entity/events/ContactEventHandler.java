package work.mywork.scm.spring_boot.entity.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import work.mywork.scm.spring_boot.entity.Contact;
import work.mywork.scm.spring_boot.exception.DuplicateResourceException;
import work.mywork.scm.spring_boot.repository.ContactRepository;

@Component
@RepositoryEventHandler(Contact.class)
public class ContactEventHandler {

    private Logger log = LoggerFactory.getLogger(ContactEventHandler.class);
    private ContactRepository contactRepository;

    @Autowired
    ContactEventHandler(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(Contact contact) {
        if (contactRepository.existsByEmail(contact.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (contactRepository.existsByPhoneNumber(contact.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        contact.setId(UUID.randomUUID().toString());
        log.info("contact is being created. ->" + contact.getName());

    }

}
