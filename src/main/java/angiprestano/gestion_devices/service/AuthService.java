package angiprestano.gestion_devices.service;

import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.exceptions.UnauthorizedException;
import angiprestano.gestion_devices.payload.LoginUserDTO;
import angiprestano.gestion_devices.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;

    public String authentificationUser(LoginUserDTO body) {
        //1)verifico se l'email dell' utente sia nel DB
        User user = usersService.findByEmail(body.email());
        // 2. In caso affermativo, verifichiamo se la password fornita corrisponde a quella trovata nel db
        if (body.password().equals(user.getPassword())) {
            // 3. Se le credenziali sono OK --> Genere un token JWT e lo ritorno
            return jwtTools.CreateToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali not valid!");
        }
    }
}
