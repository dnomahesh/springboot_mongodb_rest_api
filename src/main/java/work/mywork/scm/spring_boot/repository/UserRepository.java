package work.mywork.scm.spring_boot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import work.mywork.scm.spring_boot.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByPhoneNumberContainingIgnoreCase(String phoneNumber);

    List<User> findByNameContainingIgnoreCase(String name);
}