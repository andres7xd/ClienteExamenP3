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
public class CobroPendienteDTO {
    private Long id;
    private int año;
    private int periodo;
    private Date fecha_vencimiento;
    private double monto;
    private MembresiaDTO membresia_id;

    public CobroPendienteDTO() {
    }

    public CobroPendienteDTO(Long id, int año, int periodo, Date fecha_vencimiento, double monto, MembresiaDTO membresia_id) {
        this.id = id;
        this.año = año;
        this.periodo = periodo;
        this.fecha_vencimiento = fecha_vencimiento;
        this.monto = monto;
        this.membresia_id = membresia_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public MembresiaDTO getMembresia_id() {
        return membresia_id;
    }

    public void setMembresia_id(MembresiaDTO membresia_id) {
        this.membresia_id = membresia_id;
    }
    
    
    
}
