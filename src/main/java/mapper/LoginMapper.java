package mapper;

import dao.Login;
import dto.LoginDTO;

public class LoginMapper extends BaseMapper<Login, LoginDTO> {

    @Override
    public Login fromDTO(LoginDTO item) {
        Login login = new Login();
        login.setId(item.getId());
        login.setToken(item.getToken());
        login.setActivo(item.isActivo());
        login.setInstante(item.getInstante());
        login.setProgramador(item.getProgramador());
        return login;
    }

    @Override
    public LoginDTO toDTO(Login item) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(item.getId());
        loginDTO.setToken(item.getToken());
        loginDTO.setActivo(item.isActivo());
        loginDTO.setInstante(item.getInstante());
        loginDTO.setProgramador(item.getProgramador());
        return loginDTO;
    }
}
