/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.ProyectosDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author Luis
 */
public class ProyectosService {

    List<ProyectosDTO> proyectosDTO;
    private final String urlFindAll = "http://localhost:8099/exa_lmc_proyectos";
    private final String urlCreate = "http://localhost:8099/exa_lmc_proyectos/";
    private final String urlModify = "http://localhost:8099/exa_lmc_proyectos/{id}";
    private final String urlDelete = "http://localhost:8099/exa_lmc_proyectos/{id}";

    public List<ProyectosDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ConnectionUtils.ListFromConnectionProyectos(urlFindAll, ProyectosDTO.class);
    }

    public void add(ProyectosDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnection(urlCreate, object);
    }

    public static ProyectosService getInstance() {
        return ProyectosServiceHolder.INSTANCE;
    }

    public void modify(Long id) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnectionPut(urlModify, id);
    }

    public void delete(Long id) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnectionDelete(urlDelete, id);
    }

    private static class ProyectosServiceHolder {

        private static final ProyectosService INSTANCE = new ProyectosService();
    }
}
