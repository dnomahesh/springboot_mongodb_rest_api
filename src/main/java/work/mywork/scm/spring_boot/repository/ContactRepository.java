package work.mywork.scm.spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import work.mywork.scm.spring_boot.entity.contact.Contact;

@RepositoryRestResource(path="contacts", collectionResourceRel="contacts")
public interface ContactRepository extends JpaRepository<Contact, String> {

    // @JsonPath("contacts/search")
    @RestResource(exported=false)
    List<Contact> findByEmailContainingIgnoreCase(String email);

    @RestResource(path="by-phone",rel="by-phone")
    List<Contact> findByPhoneNumberContainingIgnoreCase(@Param("cno") String phoneNumber);

    
    @RestResource(path="by-name")
    List<Contact> findByNameContainingIgnoreCase(@Param("name") String Name);


}
