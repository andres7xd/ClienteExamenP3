/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.CantonesDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author rache
 */
public class CantonService {

    List<CantonesDTO> cantonDto;
    private final String urlFindAll = "http://localhost:8099/cantones";

    public List<CantonesDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        cantonDto = ConnectionUtils.ListFromConnectionCantones(urlFindAll, CantonesDTO.class);

        return ConnectionUtils.ListFromConnectionCantones(urlFindAll, CantonesDTO.class);
    }

    public static CantonService getInstance() {
        return CantonesServiceHolder.INSTANCE;
    }

    private static class CantonesServiceHolder {

        private static final CantonService INSTANCE = new CantonService();
    }
}
