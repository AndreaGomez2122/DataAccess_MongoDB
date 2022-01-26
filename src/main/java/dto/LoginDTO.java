package dto;

import dao.Programador;
import lombok.Data;
import org.bson.types.ObjectId;

import java.sql.Timestamp;

@Data
public class LoginDTO {
    private ObjectId id;
    private Programador programador;
    private Timestamp instante;
    private String token;
    private boolean activo;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id=" + id +
                ", programador=" + programador.getNombre() +
                ", instante=" + instante +
                ", token='" + token + '\'' +
                ", activo=" + activo +
                '}';
    }
}
