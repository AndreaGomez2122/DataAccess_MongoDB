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
        return LoginDTO.builder()
        .id(item.getId())
        .token(item.getToken())
        .activo(item.isActivo())
        .instante(item.getInstante())
        .programador(item.getProgramador()).build();
    }
}
