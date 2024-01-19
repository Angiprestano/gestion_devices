package angiprestano.gestion_devices.service;

import angiprestano.gestion_devices.entities.Device;
import angiprestano.gestion_devices.exceptions.NotFoundException;
import angiprestano.gestion_devices.payload.DeviceDTO;
import angiprestano.gestion_devices.repository.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    public Page<Device> getUser(int page, int size, String orderBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return deviceDAO.findAll(pageable);
    }
    public Device findById(UUID id) {
        return deviceDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Device save(DeviceDTO body){

        Device newDispositivo = new Device();
        newDispositivo.setDevtipology(body.devtipology());
        newDispositivo.setStateOfDevices(body.stateOfDevices());

        return deviceDAO.save(newDispositivo);
    }
    public Device findByIdAndUpdate(UUID id,Device body){
        Device found=this.findById(id);
        found.setDevtipology(body.getDevtipology());
        found.setStateOfDevices(body.getStateOfDevices());

        return deviceDAO.save(found);
    }
    public void findByIdAndDelete(UUID id){
        Device found=this.findById(id);
        deviceDAO.save(found);
    }
}
