package service;

import dao.Issue;
import dao.Login;
import dao.Programador;
import dto.LoginDTO;
import dto.ProgramadorDTO;
import mapper.LoginMapper;
import mapper.ProgramadorMapper;
import org.bson.types.ObjectId;
import repository.LoginRepository;
import repository.ProgramadorRepository;
import utils.Cifrador;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class LoginService extends BaseService<Login, ObjectId, LoginRepository> {
    LoginMapper mapper = new LoginMapper();
    ProgramadorMapper pmapper = new ProgramadorMapper();


    public LoginService(LoginRepository repository) {
        super(repository);
    }
    public Optional<List<Login>> getAllLogins() throws SQLException {
        return null;
    }

    public LoginDTO login(ObjectId id, String userPassword) throws SQLException {
        try {
            Programador user = getProgramadorById(id);
            Cifrador cif = Cifrador.getInstance();
            if ((user != null) && user.getContraseña().equals(cif.SHA256(userPassword))) {
                Login insert = new Login();
                insert.setId(user.getId());
                insert.setInstante(Timestamp.from(Instant.now()));
                LoginDTO login = mapper.toDTO(repository.save(insert));
                return login;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private Programador getProgramadorById(ObjectId id) throws SQLException {
        ProgramadorService service = new ProgramadorService(new ProgramadorRepository());
        return pmapper.fromDTO(service.getProgramadorById(id));
    }

    public boolean logout(ObjectId id) throws SQLException {
        return repository.deleteByProgrammerId(id);
    }
}
