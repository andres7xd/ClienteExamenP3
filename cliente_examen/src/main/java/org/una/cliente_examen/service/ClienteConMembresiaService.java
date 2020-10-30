/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.ClienteConMembresiaDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author Andres
 */
public class ClienteConMembresiaService {
    
     private final String urlFindAll ="http://localhost:8099/exa_jzm_clientes_con_membresias";
     public List<ClienteConMembresiaDTO> getAll() throws InterruptedException, ExecutionException, IOException {

        return ConnectionUtils.ListFromConnectionClienteMembresia(urlFindAll, ClienteConMembresiaDTO.class);
    }
    
         public static ClienteConMembresiaService getInstance() {
        return ClienteConMembresiaServiceHolder.INSTANCE;
    }

    private static class ClienteConMembresiaServiceHolder {

        private static final ClienteConMembresiaService INSTANCE = new ClienteConMembresiaService();
    }
}
