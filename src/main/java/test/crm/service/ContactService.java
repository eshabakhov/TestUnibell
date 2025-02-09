package test.crm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.crm.dto.Client;
import test.crm.dto.Contact;
import test.crm.enums.TypeContactEnum;
import test.crm.exception.ClientNotFoundException;
import test.crm.repository.ClientRepository;
import test.crm.repository.ContactRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ContactService {

    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;

    public Contact create(Long clientId, Contact contact) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        contact.setClient(client);
        return contactRepository.save(contact);
    }

    public List<Contact> getList(Long clientId, TypeContactEnum typeContactEnum) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        if (!client.isExist()) {
            throw new ClientNotFoundException(clientId);
        }
        if (Objects.nonNull(typeContactEnum)) {
            return contactRepository.findByClientIdAndTypeContact(clientId, typeContactEnum);
        }
        return contactRepository.findByClientId(clientId);
    }
}
