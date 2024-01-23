package angiprestano.gestion_devices.service;

import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.exceptions.BadRequestException;
import angiprestano.gestion_devices.exceptions.NotFoundException;
import angiprestano.gestion_devices.payload.UserDTO;
import angiprestano.gestion_devices.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UserDAO userDAO;

    public Page<User> getUser(int page, int size, String orderBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return userDAO.findAll(pageable);
    }
    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public User save(UserDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("This email " + user.getEmail() + " is functional!");
        });
        User newUser = new User();
        newUser.setUsername(body.username());
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        return userDAO.save(newUser);
    }
    public User findByIdAndUpdate(UUID id,User body){
        User found=this.findById(id);
        found.setUsername(body.getUsername());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        return userDAO.save(found);
    }
    public void findByIdAndDelete(UUID id){
        User found=this.findById(id);
        userDAO.save(found);
    }

    public User findByEmail(String email)throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(()->new NotFoundException("The user with email" + email + "is found"));
    }

}
