package angiprestano.gestion_devices.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id) {
        super("The element is" + id + "not found");
    }
}
