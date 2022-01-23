package mapper;

import dao.Login;
import dto.LoginDTO;

public class LoginMapper extends BaseMapper<Login, LoginDTO> {

    @Override
    public Login fromDTO(LoginDTO item) {
        Login login = new Login();
        //login.setters
        return login;
    }

    @Override
    public LoginDTO toDTO(Login item) {
        LoginDTO loginDTO = new LoginDTO();
        //loginDTO.setters
        return loginDTO;
    }
}
