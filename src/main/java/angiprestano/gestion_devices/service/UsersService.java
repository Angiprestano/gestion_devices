package angiprestano.gestion_devices.service;

import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.exceptions.NotFoundException;
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

    public Page<User> getUtenti(int page, int size, String orderBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return userDAO.findAll(pageable);
    }
    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public User save(User body){
        return userDAO.save(body);
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
}
