package work.mywork.scm.spring_boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import work.mywork.scm.spring_boot.entity.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Page<Contact> findByNameContainingIgnoreCase(String name, Pageable pageable);
}