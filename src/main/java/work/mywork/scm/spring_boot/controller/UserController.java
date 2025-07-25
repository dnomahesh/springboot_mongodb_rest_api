package work.mywork.scm.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.mywork.scm.spring_boot.entity.User;
import work.mywork.scm.spring_boot.exception.DuplicateResourceException;
import work.mywork.scm.spring_boot.repository.UserRepository;
import work.mywork.scm.spring_boot.service.StorageService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        user.setId(UUID.randomUUID().toString());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name")
    public Page<User> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedUser.getName());
                    existing.setEmail(updatedUser.getEmail());
                    existing.setPhoneNumber(updatedUser.getPhoneNumber());
                    return ResponseEntity.ok(userRepository.save(existing));
                })
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-phone")
    public boolean searchByPhone(@RequestParam("phone") String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @GetMapping("/by-email")
    public boolean searchByEmail(@RequestParam String email) {
        return userRepository.existsByEmail(email);
    }

    @PutMapping("/{id}/uploadImage")
    public ResponseEntity<User> uploadUserImage(@PathVariable String id, @RequestParam("image") MultipartFile file) {
        String fileUrl = storageService.store(file);
        User user = userRepository.findById(id).orElseThrow();
        user.setImageUrl(fileUrl);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/uploadVideo")
    public ResponseEntity<User> uploadUserVideo(@PathVariable String id, @RequestParam("video") MultipartFile file) {
        String fileUrl = storageService.store(file);
        User user = userRepository.findById(id).orElseThrow();
        user.setVideoUrl(fileUrl);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/media/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveMedia(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        if (file == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}