/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.dto;

import java.util.Date;

/**
 *
 * @author Andres
 */
public class MembresiaDTO {
    
     private String periosidad;
    private double monto;
    private String descripcion;
    private Date fecha_inscripcion;

    public MembresiaDTO(String periosidad, double monto, String descripcion, Date fecha_inscripcion) {
        this.periosidad = periosidad;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public MembresiaDTO() {
    }

    public String getPeriosidad() {
        return periosidad;
    }

    public void setPeriosidad(String periosidad) {
        this.periosidad = periosidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    
    
    
}
