package com.example.UC3_AddressBookAppSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public List<AddressBookDTO> getAllContacts() {
        return repository.findAll().stream()
                .map(contact -> new AddressBookDTO(contact.getName(), contact.getPhone(), contact.getEmail(),contact.getAddress()))
                .collect(Collectors.toList());
    }

    public AddressBookDTO addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());

        AddressBook saved = repository.save(contact);
        return new AddressBookDTO(saved.getName(), saved.getPhone(), saved.getEmail(), contact.getAddress());
    }
}
