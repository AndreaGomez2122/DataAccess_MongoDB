package dto;

import dao.Commit;
import dao.Issue;
import dao.Proyecto;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class RepositorioDTO {
    private ObjectId id;
    private String nombre;
    private Date fecha_creacion;
    private Proyecto proyecto;
    private Set<Commit> commits;
    private Set<Issue> issues;

    @Override
    public String toString() {
        return "RepositorioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", proyecto=" + proyecto.getNombre() +
                ", commits=" + commits.stream().map(Commit::getTitulo) +
                ", issues=" + issues.stream().map(Issue::getTitulo) +
                '}';
    }
}
