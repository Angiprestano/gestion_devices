package angiprestano.gestion_devices.controller;

import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.payload.UserDTO;
import angiprestano.gestion_devices.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public Page<User> getUtenti(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return usersService.getUser(page, size, orderBy);
    }
   @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return usersService.findById(id);
    }
    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public User save(@RequestBody UserDTO body) {
        return usersService.save(body);
    }
    @PutMapping("/{id}")
    public User findByAndUpdate(@PathVariable UUID id, @RequestBody User body) {
        return this.usersService.findByIdAndUpdate(id,body);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.usersService.findByIdAndDelete(id);
    }
}
