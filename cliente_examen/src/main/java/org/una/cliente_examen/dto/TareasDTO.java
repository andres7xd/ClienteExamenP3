/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.dto;

import java.util.Date;
import org.una.cliente_examen.dto.ProyectosDTO;

/**
 *
 * @author Luis
 */
public class TareasDTO {

    private Long id;
    private Date fecha_inicio;
    private Date fecha_finalizacion;
    private double importancia;
    private double urgencia;
    private double prioridad;
    private double porcentaje_avance;
    private ProyectosDTO proyectos;

    public TareasDTO() {
    }

    public TareasDTO(Long id, Date fecha_inicio, Date fecha_finalizacion, double importancia, double urgencia, double prioridad, double porcentaje_avance, ProyectosDTO proyectos) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizacion = fecha_finalizacion;
        this.importancia = importancia;
        this.urgencia = urgencia;
        this.prioridad = prioridad;
        this.porcentaje_avance = porcentaje_avance;
        this.proyectos = proyectos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public double getImportancia() {
        return importancia;
    }

    public void setImportancia(double importancia) {
        this.importancia = importancia;
    }

    public double getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(double urgencia) {
        this.urgencia = urgencia;
    }

    public double getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(double prioridad) {
        this.prioridad = prioridad;
    }

    public double getPorcentaje_avance() {
        return porcentaje_avance;
    }

    public void setPorcentaje_avance(double porcentaje_avance) {
        this.porcentaje_avance = porcentaje_avance;
    }

    public ProyectosDTO getProyectos() {
        return proyectos;
    }

    public void setProyectos(ProyectosDTO proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "TareasDTO{" + "id=" + id + ", fecha_inicio=" + fecha_inicio + ", fecha_finalizacion=" + fecha_finalizacion + ", importancia=" + importancia + ", urgencia=" + urgencia + ", prioridad=" + prioridad + ", porcentaje_avance=" + porcentaje_avance + ", proyectos=" + proyectos + '}';
    }
    
    
}
