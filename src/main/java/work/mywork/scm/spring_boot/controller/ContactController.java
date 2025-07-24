package work.mywork.scm.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import work.mywork.scm.spring_boot.entity.Contact;
import work.mywork.scm.spring_boot.exception.DuplicateResourceException;
import work.mywork.scm.spring_boot.repository.ContactRepository;

import java.util.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        if (contactRepository.existsByEmail(contact.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (contactRepository.existsByPhoneNumber(contact.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        contact.setId(UUID.randomUUID().toString());
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact updatedContact) {
        return contactRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedContact.getName());
                    existing.setEmail(updatedContact.getEmail());
                    existing.setPhoneNumber(updatedContact.getPhoneNumber());
                    return ResponseEntity.ok(contactRepository.save(existing));
                })
                .orElseThrow(() -> new NoSuchElementException("Contact not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public List<Contact> searchByName(@RequestParam String name) {
        return contactRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/by-phone")
    public List<Contact> searchByPhone(@RequestParam("cno") String phoneNumber) {
        return contactRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber);
    }

    @GetMapping("/by-email")
    public List<Contact> searchByEmail(@RequestParam String email) {
        return contactRepository.findByEmailContainingIgnoreCase(email);
    }
}