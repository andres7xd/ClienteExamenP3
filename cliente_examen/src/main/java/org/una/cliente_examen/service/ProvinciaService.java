/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.ProvinciasDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author rache
 */
public class ProvinciaService {

    List<ProvinciasDTO> provinciaDto;
    private final String urlFindAll = "http://localhost:8099/provincias";
     private final String urlCreate = "http://localhost:8099/provincias/";

    public List<ProvinciasDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        provinciaDto = ConnectionUtils.ListFromConnectionProvincias(urlFindAll, ProvinciasDTO.class);

        return ConnectionUtils.ListFromConnectionProvincias(urlFindAll, ProvinciasDTO.class);
    }
     public void add(ProvinciasDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnection(urlCreate, object);
    }

    public static ProvinciaService getInstance() {
        return ProvinciaServiceHolder.INSTANCE;
    }

    private static class ProvinciaServiceHolder {

        private static final ProvinciaService INSTANCE = new ProvinciaService();
    }
}
