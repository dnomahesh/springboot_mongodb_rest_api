package work.mywork.scm.spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import work.mywork.scm.spring_boot.payload.ApiResponse;

@RestController
public class RootController {


    
    @GetMapping("")
    public ApiResponse<String> onlyBaseUrl() {
        return new ApiResponse<>("Add '/api/' to the end of this url to access the API endpoints");
    }

    // @GetMapping("/")
    // public ApiResponse<String> baseUrlWithSlash() {
    //     return new ApiResponse<>("Add 'api/' to the end of this url to access the API endpoints");
    // }

    
    @GetMapping("/api")
    public ApiResponse<String> baseUrlWithSlashAndApi() {
        return new ApiResponse<>("Add '/' to the end of this url to access the API endpoints");
    }

    
    @GetMapping("/api/")
    public ApiResponse<String> correctedBaseUrl() {
        return new ApiResponse<>("Welcome. You have found the correct Base Url for SpringBoot MongoDb Rest API Project");
    }
}

