/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.dto;

/**
 *
 * @author Andres
 */
public class ClienteConMembresiaDTO {
     private Long id;
    private ClienteDTO cliente;
    private MembresiaDTO membresia;

    public ClienteConMembresiaDTO() {
    }

    public ClienteConMembresiaDTO(Long id, ClienteDTO cliente, MembresiaDTO membresia) {
        this.id = id;
        this.cliente = cliente;
        this.membresia = membresia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public MembresiaDTO getMembresia() {
        return membresia;
    }

    public void setMembresia(MembresiaDTO membresia) {
        this.membresia = membresia;
    }
    
    
}
