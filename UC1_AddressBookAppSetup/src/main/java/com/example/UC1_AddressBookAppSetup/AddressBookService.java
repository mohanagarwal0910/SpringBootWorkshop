package com.example.UC1_AddressBookAppSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    // Fetch all contacts
    public List<AddressBookEntry> getAllContacts() {
        return repository.findAll();
    }

    // Fetch a contact by ID
    public Optional<AddressBookEntry> getContactById(Long id) {
        return repository.findById(id);
    }

    // Add a new contact
    public AddressBookEntry addContact(AddressBookEntry contact) {
        return repository.save(contact);
    }

    // Update an existing contact
    public Optional<AddressBookEntry> updateContact(Long id, AddressBookEntry newContact) {
        return repository.findById(id).map(existingContact -> {
            existingContact.setName(newContact.getName());
            existingContact.setPhone(newContact.getPhone());
            existingContact.setEmail(newContact.getEmail());
            existingContact.setAddress(newContact.getAddress());
            return repository.save(existingContact);
        });
    }

    // Delete a contact
    public boolean deleteContact(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
