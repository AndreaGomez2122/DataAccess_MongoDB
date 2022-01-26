package dto;

import dao.Estado;
import dao.Programador;
import dao.Proyecto;
import dao.Repositorio;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Set;

@Data
public class IssueDTO {
    private ObjectId id;
    private String titulo;
    private String texto;
    private Date fecha;
    private Set<Programador> programadores; //No se añade al constructor. Se le añaden los programadores más tarde.
    private Proyecto proyecto;
    private Repositorio repositorio;
    private Estado estado;

    @Override
    public String toString() {
        return "IssueDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", fecha=" + fecha +
                ", programadores=" + programadores.stream().map(Programador::getNombre) +
                ", proyecto=" + proyecto.getNombre() +
                ", repositorio=" + repositorio.getNombre() +
                ", estado=" + estado +
                '}';
    }
}
