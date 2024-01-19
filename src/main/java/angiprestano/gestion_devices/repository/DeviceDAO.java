package angiprestano.gestion_devices.repository;

import angiprestano.gestion_devices.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DeviceDAO extends JpaRepository<Device, UUID> {

}
