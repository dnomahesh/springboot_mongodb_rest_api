package work.mywork.scm.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import work.mywork.scm.spring_boot.entity.User;

@RepositoryRestResource(exported= false)
public interface UserRepository extends JpaRepository<User, String> {

}
