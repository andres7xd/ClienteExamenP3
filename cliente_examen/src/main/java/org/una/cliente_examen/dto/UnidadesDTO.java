/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author rache
 */
public class UnidadesDTO {
    
    private Long id;
    private String nombre;
    private Integer codigo;
    private BigDecimal area;
    private BigInteger poblacion;
    private TiposDTO tipos;
    private DistritosDTO distrito;

    public UnidadesDTO(Long id, String nombre, Integer codigo, BigDecimal area, BigInteger poblacion, TiposDTO tipos, DistritosDTO distrito) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.area = area;
        this.poblacion = poblacion;
        this.tipos = tipos;
        this.distrito = distrito;
    }

    public UnidadesDTO() {
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigInteger getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(BigInteger poblacion) {
        this.poblacion = poblacion;
    }

    public TiposDTO getTipos() {
        return tipos;
    }

    public void setTipos(TiposDTO tipos) {
        this.tipos = tipos;
    }

    public DistritosDTO getDistrito() {
        return distrito;
    }

    public void setDistrito(DistritosDTO distrito) {
        this.distrito = distrito;
    }
    
}
