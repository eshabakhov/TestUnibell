package test.crm.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends EntityNotFoundException {
    public ClientNotFoundException(Long clientId) {
        super(String.format("Client %d not found", clientId));
    }
}
