package angiprestano.gestion_devices.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@Getter
@Setter
@ToString
public class User {
    @Setter(AccessLevel.NONE)
    private UUID surname;
    private String name;
    private String lastName;
    private String email;
}
