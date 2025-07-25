package work.mywork.scm.spring_boot.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
   
    private DataSize maxFileSize;

    private List<String> allowedFileTypes;

    private List<String> allowedImageTypes;

    private List<String> allowedVideoTypes;

    public DataSize getMaxFileSize() {
        return maxFileSize;
    }

    
    public List<String> getAllowedFileTypes() {
        return allowedFileTypes;
    }
    
    public List<String> getAllowedImageTypes() {
        return allowedImageTypes;
    }
    
    public List<String> getAllowedVideoTypes() {
        return allowedVideoTypes;
    }

    public void setMaxFileSize(DataSize maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
    
    public void setAllowedFileTypes(List<String> allowedFileTypes) {
        this.allowedFileTypes = allowedFileTypes;
    }
    
    public void setAllowedImageTypes(List<String> allowedImageTypes) {
        this.allowedImageTypes = allowedImageTypes;
    }

    
    public void setAllowedVideoTypes(List<String> allowedVideoTypes) {
        this.allowedVideoTypes = allowedVideoTypes;
    }


    public long getMaxFileSizeInBytes() {
        return maxFileSize.toBytes(); 
    }
}

