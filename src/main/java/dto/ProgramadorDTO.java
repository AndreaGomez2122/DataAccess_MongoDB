package dto;

import dao.*;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class ProgramadorDTO {
    private ObjectId id;
    private String nombre;
    private Date fecha_alta;
    private Departamento departamento;
    private Set<Proyecto> proyectos;
    private Set<Commit> commits;
    private Set<Issue> issues;
    private Set<Tecnologia> tecnologias;
    private double salario;
    private String contraseña;

    @Override
    public String toString() {
        return "ProgramadorDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha_alta=" + fecha_alta +
                ", departamento=" + departamento.getNombre() +
                ", proyectos=" + proyectos.stream().map(Proyecto::getNombre) +
                ", commits=" + commits.stream().map(Commit::getId) +
                ", issues=" + issues.stream().map(Issue::getId) +
                ", tecnologias=" + tecnologias +
                ", salario=" + salario + '\'' +
                '}';
    }
}
