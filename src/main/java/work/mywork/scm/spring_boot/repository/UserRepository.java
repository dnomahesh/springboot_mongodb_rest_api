package work.mywork.scm.spring_boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import work.mywork.scm.spring_boot.entity.User;

@RepositoryRestResource(exported = true)
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}