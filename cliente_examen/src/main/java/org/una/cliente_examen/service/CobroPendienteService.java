/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.CobroPendienteDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author Andres
 */
public class CobroPendienteService {
     private final String urlFindAll ="http://localhost:8099/exa_jzm_cobros_pendientes";
     private final String urlCrear ="http://localhost:8099/exa_jzm_cobros_pendientes/";
     
     public List<CobroPendienteDTO> getAll() throws InterruptedException, ExecutionException, IOException {

        return ConnectionUtils.ListFromConnectionCobroPendiente(urlFindAll, CobroPendienteDTO.class);
    }
     
     public void add(CobroPendienteDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnection(urlCrear, object);
    }
    
         public static CobroPendienteService getInstance() {
        return CobroPendienteServiceHolder.INSTANCE;
    }

    private static class CobroPendienteServiceHolder {

        private static final CobroPendienteService INSTANCE = new CobroPendienteService();
    }
}
