package work.mywork.scm.spring_boot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import work.mywork.scm.spring_boot.entity.Contact;

@RepositoryRestResource(path = "contacts", collectionResourceRel = "contacts")
public interface ContactRepository extends MongoRepository<Contact, String> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @RestResource(exported = false)
    List<Contact> findByEmailContainingIgnoreCase(String email);

    @RestResource(path = "by-phone", rel = "by-phone")
    List<Contact> findByPhoneNumberContainingIgnoreCase(@Param("cno") String phoneNumber);

    @RestResource(path = "by-name")
    List<Contact> findByNameContainingIgnoreCase(@Param("name") String name);
}
