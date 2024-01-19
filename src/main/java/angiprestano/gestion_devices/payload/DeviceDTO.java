package angiprestano.gestion_devices.payload;

import angiprestano.gestion_devices.ENUM.Devtipology;
import angiprestano.gestion_devices.ENUM.StateOfDevices;
import angiprestano.gestion_devices.entities.User;

public record DeviceDTO(User user, Devtipology devtipology, StateOfDevices stateOfDevices) {
}
