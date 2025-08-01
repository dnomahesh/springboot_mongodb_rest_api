package work.mywork.scm.spring_boot.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(appProperties.getMaxFileSize());
        factory.setMaxRequestSize(appProperties.getMaxFileSize());
        return factory.createMultipartConfig();
    }
}


