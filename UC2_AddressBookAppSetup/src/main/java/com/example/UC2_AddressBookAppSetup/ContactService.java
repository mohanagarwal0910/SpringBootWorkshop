package com.example.UC2_AddressBookAppSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return repository.findById(id);
    }

    public Contact saveContact(Contact contact) {
        return repository.save(contact);
    }

    public Optional<Contact> updateContact(Long id, Contact newContact) {
        return repository.findById(id).map(existingContact -> {
            existingContact.setName(newContact.getName());
            existingContact.setPhone(newContact.getPhone());
            existingContact.setEmail(newContact.getEmail());
            existingContact.setAddress(newContact.getAddress());
            return repository.save(existingContact);
        });
    }

    public boolean deleteContact(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
