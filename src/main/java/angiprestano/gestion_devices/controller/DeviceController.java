package angiprestano.gestion_devices.controller;

import angiprestano.gestion_devices.entities.Device;
import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.payload.DeviceDTO;
import angiprestano.gestion_devices.payload.UserDTO;
import angiprestano.gestion_devices.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public Page<Device> getUtenti(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy){
        return deviceService.getUser(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Device findById(@PathVariable UUID id) {
        return deviceService.findById(id);
    }
    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Device save(@RequestBody DeviceDTO body) {
        return deviceService.save(body);
    }
    @PutMapping("/{id}")
    public Device findByAndUpdate(@PathVariable UUID id, @RequestBody Device body) {
        return this.deviceService.findByIdAndUpdate(id,body);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.deviceService.findByIdAndDelete(id);
    }
}
