package angiprestano.gestion_devices.entities;

import angiprestano.gestion_devices.ENUM.Devtipology;
import angiprestano.gestion_devices.ENUM.StateOfDevices;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@ToString
public class Device {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private StateOfDevices stateOfDevices;
    @Enumerated(EnumType.STRING)
    private Devtipology devtipology;

    public void setUser(User user) {
        this.user = user;
    }

    public void setStateOfDevices(StateOfDevices stateOfDevices) {
        this.stateOfDevices = stateOfDevices;
    }

    public void setDevtipology(Devtipology devtipology) {
        this.devtipology = devtipology;
    }
}
