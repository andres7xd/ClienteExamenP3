/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.dto;

/**
 *
 * @author rache
 */
public class DistritosDTO {

    private Long id;
    private String nombre;
    private Integer codigo;
    private CantonesDTO cantones;

    public DistritosDTO(Long id, String nombre, Integer codigo, CantonesDTO cantones) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantones = cantones;
    }

    public DistritosDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public CantonesDTO getCantones() {
        return cantones;
    }

    public void setCantones(CantonesDTO cantones) {
        this.cantones = cantones;
    }
    
}
