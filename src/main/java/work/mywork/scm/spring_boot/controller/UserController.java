package work.mywork.scm.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import work.mywork.scm.spring_boot.entity.User;
import work.mywork.scm.spring_boot.exception.DuplicateResourceException;
import work.mywork.scm.spring_boot.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

    @GetMapping("/by-name")
    public List<User> searchByName(@RequestParam String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/by-phone")
    public List<User> searchByPhone(@RequestParam("phone") String phoneNumber) {
        return userRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber);
    }

    @GetMapping("/by-email")
    public List<User> searchByEmail(@RequestParam String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }
}