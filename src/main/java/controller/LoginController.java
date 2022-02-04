package controller;

import dto.LoginDTO;
import org.bson.types.ObjectId;
import repository.LoginRepository;
import service.LoginService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LoginController {
    private static LoginController controller = null;

    // Mi Servicio unido al repositorio
    private final LoginService loginService;

    // Implementamos nuestro Singleton para el controlador
    private LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public static LoginController getInstance() {
        if (controller == null) {
            controller = new LoginController(new LoginService(new LoginRepository()));
        }
        return controller;
    }

    public Optional<LoginDTO> login(ObjectId id, String userPassword) {
        try {
            LoginDTO login = loginService.login(id, userPassword);
            if (login != null) {
                return Optional.of(login);
            }
        } catch (SQLException e) {
            Optional.empty();
        }
        return  Optional.empty();
    }

    public boolean logout(ObjectId userId) {
        try {
            if (loginService.logout(userId))
                return true;
            else
                return false;
        } catch (SQLException e) {
            System.err.println("Error Login Controller Logout: " + e.getMessage());
            return false;
        }
    }
}
