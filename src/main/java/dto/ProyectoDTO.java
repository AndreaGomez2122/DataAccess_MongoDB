package dto;

import dao.Departamento;
import dao.Programador;
import dao.Repositorio;
import dao.Tecnologia;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class ProyectoDTO {
    private ObjectId id;
    private Programador jefe;
    private String nombre;
    private double presupuesto;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Set<Tecnologia> tecnologias;
    private Repositorio repositorio;
    private Departamento departamento;

    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "id=" + id +
                ", jefe=" + jefe.getNombre() +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", tecnologias=" + tecnologias +
                ", repositorio=" + repositorio.getNombre() +
                ", departamento=" + departamento.getNombre() +
                '}';
    }
}
