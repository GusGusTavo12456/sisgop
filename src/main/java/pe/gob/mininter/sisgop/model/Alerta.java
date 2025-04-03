package pe.gob.mininter.sisgop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "Año no válido")
    @Max(value = 2100, message = "Año no válido")
    private Integer anio;

    @NotBlank(message = "El gestor es obligatorio")
    private String gestor;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    // Guardamos códigos (IDs) para ubigeo
    private String departamento; // ID
    private String provincia;    // ID
    private String distrito;     // ID

    private String localidad;

    @NotNull(message = "Debe ingresar la fecha del evento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEvento;

    @NotNull(message = "Debe ingresar la hora del evento")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaEvento;

    private String acciones;
    private String descripcion;
    private String tipoDemanda;
    private String demanda;
    private String actores;
    private String tipoDemandado;
    private String detalleDemandado;
    private String nivelRiesgo;
    private String descripcionRiesgo;
    private String accionesDRC;
    private String proyeccion;
    private String recomendaciones;

    private boolean coordinacion;
    private String tipoDemandantes;
    private String organizacionDemandantes;

    public Alerta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getGestor() { return gestor; }
    public void setGestor(String gestor) { this.gestor = gestor; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public LocalDate getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(LocalDate fechaEvento) { this.fechaEvento = fechaEvento; }

    public LocalTime getHoraEvento() { return horaEvento; }
    public void setHoraEvento(LocalTime horaEvento) { this.horaEvento = horaEvento; }

    public String getAcciones() { return acciones; }
    public void setAcciones(String acciones) { this.acciones = acciones; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getTipoDemanda() { return tipoDemanda; }
    public void setTipoDemanda(String tipoDemanda) { this.tipoDemanda = tipoDemanda; }

    public String getDemanda() { return demanda; }
    public void setDemanda(String demanda) { this.demanda = demanda; }

    public String getActores() { return actores; }
    public void setActores(String actores) { this.actores = actores; }

    public String getTipoDemandado() { return tipoDemandado; }
    public void setTipoDemandado(String tipoDemandado) { this.tipoDemandado = tipoDemandado; }

    public String getDetalleDemandado() { return detalleDemandado; }
    public void setDetalleDemandado(String detalleDemandado) { this.detalleDemandado = detalleDemandado; }

    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public String getDescripcionRiesgo() { return descripcionRiesgo; }
    public void setDescripcionRiesgo(String descripcionRiesgo) { this.descripcionRiesgo = descripcionRiesgo; }

    public String getAccionesDRC() { return accionesDRC; }
    public void setAccionesDRC(String accionesDRC) { this.accionesDRC = accionesDRC; }

    public String getProyeccion() { return proyeccion; }
    public void setProyeccion(String proyeccion) { this.proyeccion = proyeccion; }

    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }

    public boolean isCoordinacion() { return coordinacion; }
    public void setCoordinacion(boolean coordinacion) { this.coordinacion = coordinacion; }

    public String getTipoDemandantes() { return tipoDemandantes; }
    public void setTipoDemandantes(String tipoDemandantes) { this.tipoDemandantes = tipoDemandantes; }

    public String getOrganizacionDemandantes() { return organizacionDemandantes; }
    public void setOrganizacionDemandantes(String organizacionDemandantes) { this.organizacionDemandantes = organizacionDemandantes; }
}
