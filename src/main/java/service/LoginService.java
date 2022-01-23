package service;

import dao.Login;
import dto.LoginDTO;
import mapper.LoginMapper;
import org.bson.types.ObjectId;
import repository.LoginRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginService extends BaseService<Login, ObjectId, LoginRepository> {
    LoginMapper mapper = new LoginMapper();

    public LoginService(LoginRepository repository) {
        super(repository);
    }

    public List<LoginDTO> getAllLogins() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public LoginDTO getLoginById(ObjectId id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public LoginDTO postLogin(LoginDTO loginDTO) throws SQLException {
        Login login = this.save(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }

    public LoginDTO updateLogin(LoginDTO loginDTO) throws SQLException {
        Login login = this.update(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }

    public LoginDTO deleteLogin(LoginDTO loginDTO) throws SQLException {
        Login login = this.delete(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }
}
