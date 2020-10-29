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
public class TiposDTO {

    private Long id;
    private String nombre;
    private Integer codigo;

    public TiposDTO(Long id, String nombre, Integer codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public TiposDTO() {
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
    
}
