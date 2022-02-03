package dto;

import dao.Programador;
import dao.Proyecto;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.Set;

@Data
@Builder
public class DepartamentoDTO {

    private ObjectId id;
    private String nombre;
    private Programador jefe;
    private double presupuesto;
    private Set<Proyecto> proyectos_terminados;
    private Set<Proyecto> proyectos_activos;
    private Set<Programador> historico_jefes;


    @Override
    public String toString() {
        return "DepartamentoDTO{" +

                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jefe='" + jefe.getNombre() + '\'' +
                ", presupuesto='" + presupuesto + '\'' +
                ", proyectos terminados =" + proyectos_terminados.stream().map(Proyecto::getNombre) +
                ", proyectos en curso =" + proyectos_activos.stream().map(Proyecto::getNombre) +
                //", historico de jefes=" + historico_jefes.stream().map(Programador::getNombre) +

                '}';
    }
}