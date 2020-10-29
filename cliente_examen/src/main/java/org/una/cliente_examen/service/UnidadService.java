/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.UnidadesDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author rache
 */
public class UnidadService {
        List<UnidadesDTO> unidadDto;
    private final String urlFindAll = "http://localhost:8099/unidades";

    public List<UnidadesDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        unidadDto = ConnectionUtils.ListFromConnectionUnidades(urlFindAll, UnidadesDTO.class);

        return ConnectionUtils.ListFromConnectionUnidades(urlFindAll, UnidadesDTO.class);
    }

    public static UnidadService getInstance() {
        return UnidadServiceHolder.INSTANCE;
    }

    private static class UnidadServiceHolder {

        private static final UnidadService INSTANCE = new UnidadService();
    }
}

