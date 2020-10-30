/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.DistritosDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author rache
 */
public class DistritoService {

    List<DistritosDTO> distritoDto;
    private final String urlFindAll = "http://localhost:8099/distritos";
    private final String urlCreate = "http://localhost:8099/distritos/";

    public List<DistritosDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        distritoDto = ConnectionUtils.ListFromConnectionDistritos(urlFindAll, DistritosDTO.class);

        return ConnectionUtils.ListFromConnectionDistritos(urlFindAll, DistritosDTO.class);
    }

    public void add(DistritosDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnection(urlCreate, object);
    }

    public static DistritoService getInstance() {
        return DistritoServiceHolder.INSTANCE;
    }

    private static class DistritoServiceHolder {

        private static final DistritoService INSTANCE = new DistritoService();
    }
}
