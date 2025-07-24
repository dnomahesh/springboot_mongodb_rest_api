package work.mywork.scm.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "work.mywork.scm.spring_boot.repository")
@ComponentScan(basePackages = "work.mywork.scm.spring_boot")
@EntityScan(basePackages = "work.mywork.scm.spring_boot.entity")  // Not needed for MongoDB, but harmless
public class ScmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScmApplication.class, args);
    }
}