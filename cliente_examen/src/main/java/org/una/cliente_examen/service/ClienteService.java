/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.ClienteDTO;
import org.una.cliente_examen.utils.ConnectionUtils;



/**
 *
 * @author Andres
 */
public class ClienteService {
     List<ClienteDTO> clienteDTO;
       private final String urlFindAll ="http://localhost:8099/exa_jzm_clientes";
//    private final String urlCreate ="http://localhost:8099/areas_trabajo/";
    
    public List<ClienteDTO> getAll() throws InterruptedException, ExecutionException, IOException {
         clienteDTO =ConnectionUtils.ListFromConnection(urlFindAll, ClienteDTO.class);
       
        
        return ConnectionUtils.ListFromConnection(urlFindAll, ClienteDTO.class);
    }
    
         public static ClienteService getInstance() {
        return ClienteServiceHolder.INSTANCE;
    }

    private static class ClienteServiceHolder {

        private static final ClienteService INSTANCE = new ClienteService();
    }
    
}
