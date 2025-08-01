package work.mywork.scm.spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import work.mywork.scm.spring_boot.payload.ApiResponse;

@RestController
public class RootController {

    @GetMapping("/")
    public ApiResponse<String> home() {
        return new ApiResponse<>("Welcome to Smart Contact Manager API");
    }
}

