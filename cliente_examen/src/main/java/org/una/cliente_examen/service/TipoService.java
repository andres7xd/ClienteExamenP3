/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.cliente_examen.dto.TiposDTO;
import org.una.cliente_examen.utils.ConnectionUtils;

/**
 *
 * @author rache
 */
public class TipoService {

    List<TiposDTO> tiposDto;
    private final String urlFindAll = "http://localhost:8099/tipos";
    private final String urlCreate = "http://localhost:8099/unidades/";

    public List<TiposDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        tiposDto = ConnectionUtils.ListFromConnectionTipos(urlFindAll, TiposDTO.class);

        return ConnectionUtils.ListFromConnectionTipos(urlFindAll, TiposDTO.class);
    }

    public void add(TiposDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtils.ObjectToConnection(urlCreate, object);
    }

    public static TipoService getInstance() {
        return TipoServiceHolder.INSTANCE;
    }

    private static class TipoServiceHolder {

        private static final TipoService INSTANCE = new TipoService();
    }
}
