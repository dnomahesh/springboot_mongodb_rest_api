package work.mywork.scm.spring_boot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import work.mywork.scm.spring_boot.entity.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Contact> findByEmailContainingIgnoreCase(String email);

    List<Contact> findByPhoneNumberContainingIgnoreCase(String phoneNumber);

    List<Contact> findByNameContainingIgnoreCase(String name);
}