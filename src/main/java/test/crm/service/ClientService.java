package test.crm.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.crm.dto.Client;
import test.crm.exception.ClientNotFoundException;
import test.crm.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getList(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(Math.toIntExact(page - 1), Math.toIntExact(pageSize));
        return clientRepository.findAll(pageable).stream().toList();
    }

    public Client get(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        if (!client.isExist()) {
            throw new ClientNotFoundException(id);
        }
        return client;
    }
}
