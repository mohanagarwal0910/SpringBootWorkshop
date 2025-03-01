package com.example.UC5_AddressBookAppSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    // Get all contacts
    @GetMapping
    public ResponseEntity<List<AddressBookDTO>> getAllContacts() {
        return ResponseEntity.ok(service.getAllContacts());
    }

    // Get contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDTO> getContactById(@PathVariable Long id) {
        Optional<AddressBookDTO> contact = service.getContactById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new contact
    @PostMapping
    public ResponseEntity<AddressBookDTO> addContact(@RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(service.addContact(dto));
    }

    // Update a contact
    @PutMapping("/{id}")
    public ResponseEntity<AddressBookDTO> updateContact(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        Optional<AddressBookDTO> updatedContact = service.updateContact(id, dto);
        return updatedContact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        if (service.deleteContact(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
