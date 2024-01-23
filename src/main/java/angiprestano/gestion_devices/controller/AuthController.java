package angiprestano.gestion_devices.controller;

import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.exceptions.BadRequestException;
import angiprestano.gestion_devices.payload.LoginUserDTO;
import angiprestano.gestion_devices.payload.LoginUserResponsiveDTO;
import angiprestano.gestion_devices.payload.UserDTO;
import angiprestano.gestion_devices.payload.UserResponsiveDTO;
import angiprestano.gestion_devices.service.AuthService;
import angiprestano.gestion_devices.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public LoginUserResponsiveDTO login(@RequestBody LoginUserDTO body){
        String accessToken= authService.authentificationUser(body);
        return new LoginUserResponsiveDTO(accessToken);
    }
    @PostMapping("/register")
    public UserResponsiveDTO createUser(@RequestBody @Validated UserDTO userDTO, BindingResult validation){
        // Per completare la validazione devo in qualche maniera fare un controllo del tipo:
        // se ci sono errori -> manda risposta con 400 Bad Request
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!"); // L'eccezione arriverà agli error handlers tra i quali c'è quello che manderà la risposta con status code 400
        } else {
            User newUser = usersService.save(userDTO);
            return new UserResponsiveDTO(newUser.getId());
        }
    }
}
