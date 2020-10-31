/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.MembresiaDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author Andres
 */
public class MembresiaService {
      private final String urlFindAll ="http://localhost:8099/exa_jzm_membresias";
     public List<MembresiaDTO> getAll() throws InterruptedException, ExecutionException, IOException {

        return ConnectionUtils.ListFromConnectionMembresia(urlFindAll, MembresiaDTO.class);
    }
    
         public static MembresiaService getInstance() {
        return MembresiaServiceHolder.INSTANCE;
    }

    private static class MembresiaServiceHolder {

        private static final MembresiaService INSTANCE = new MembresiaService();
    }
}
