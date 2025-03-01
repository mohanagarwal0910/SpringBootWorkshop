package com.example.UC5_AddressBookAppSetup;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private final List<AddressBook> contacts = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Get all contacts
    public List<AddressBookDTO> getAllContacts() {
        return contacts.stream()
                .map(contact -> new AddressBookDTO(contact.getName(), contact.getPhone(), contact.getEmail(), contact.getAddress()))
                .collect(Collectors.toList());
    }

    // Get contact by ID
    public Optional<AddressBookDTO> getContactById(Long id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .map(contact -> new AddressBookDTO(contact.getName(), contact.getPhone(), contact.getEmail(), contact.getAddress()))
                .findFirst();
    }

    // Add a new contact
    public AddressBookDTO addContact(AddressBookDTO dto) {
        AddressBook newContact = new AddressBook(idCounter.getAndIncrement(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getAddress());
        contacts.add(newContact);
        return dto;
    }

    // Update an existing contact
    public Optional<AddressBookDTO> updateContact(Long id, AddressBookDTO dto) {
        for (AddressBook contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(dto.getName());
                contact.setPhone(dto.getPhone());
                contact.setEmail(dto.getEmail());
                contact.setAddress(dto.getAddress()); // Update address
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }

    // Delete a contact by ID
    public boolean deleteContact(Long id) {
        return contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
