/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.TareasDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author Luis
 */
public class TareasService {

    List<TareasDTO> tareasDTO;
    private final String urlFindAll = "http://localhost:8099/exa_lmc_tareas";
    private final String urlCreate = "http://localhost:8099/exa_lmc_tareas/";
    private final String urlModify = "http://localhost:8099/exa_lmc_tareas/";
    private final String urlDelete = "http://localhost:8099/exa_lmc_tareas/";

    public List<TareasDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ConnectionUtils.ListFromConnectionTareas(urlFindAll, TareasDTO.class);
    }

    public void add(TareasDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnection(urlCreate, object);
    }

    public static TareasService getInstance() {
        return TareasServiceHolder.INSTANCE;
    }

    public void modify(Long id, TareasDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnectionModify(urlModify + id, object);
    }

    public void delete(Long id) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnectionDelete(urlDelete + id, null);
    }

    private static class TareasServiceHolder {

        private static final TareasService INSTANCE = new TareasService();
    }
}
