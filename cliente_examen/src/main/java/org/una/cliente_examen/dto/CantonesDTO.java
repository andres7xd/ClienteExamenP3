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
public class CantonesDTO {

    private Long id;
    private String nombre;
    private Integer codigo;
    private ProvinciasDTO provincia;

    public CantonesDTO(Long id, String nombre, Integer codigo, ProvinciasDTO provincia) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.provincia = provincia;
    }

    public CantonesDTO() {
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

    public ProvinciasDTO getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciasDTO provincia) {
        this.provincia = provincia;
    }
    
}
