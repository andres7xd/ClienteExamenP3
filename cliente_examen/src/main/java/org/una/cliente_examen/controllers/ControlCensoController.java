/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.una.cliente_examen.dto.CantonesDTO;
import org.una.cliente_examen.dto.DistritosDTO;
import org.una.cliente_examen.dto.ProvinciasDTO;
import org.una.cliente_examen.dto.UnidadesDTO;
import org.una.cliente_examen.service.CantonService;
import org.una.cliente_examen.service.DistritoService;
import org.una.cliente_examen.service.ProvinciaService;
import org.una.cliente_examen.service.UnidadService;

/**
 * FXML Controller class
 *
 * @author rache
 */
public class ControlCensoController implements Initializable {

    @FXML
    private TreeView<String> treeview;
    private List<ProvinciasDTO> Listaprovincias;
    private List<CantonesDTO> Listacantones;
    private List<DistritosDTO> Listadistritos;
    private List<UnidadesDTO> Listaunidades;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Listaprovincias = new ArrayList<>();
        this.Listacantones = new ArrayList<>();
        this.Listadistritos = new ArrayList<>();
        this.Listaunidades = new ArrayList<>();
        this.CreacionTreeView();
        // TODO
    }

    public void CreacionTreeView() {

        try {
            this.Listaprovincias = ProvinciaService.getInstance().getAll();
            this.Listacantones = CantonService.getInstance().getAll();
            this.Listadistritos = DistritoService.getInstance().getAll();
            this.Listaunidades = UnidadService.getInstance().getAll();
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TreeItem<String> padre = new TreeItem<>("PROVINCIAS");

        padre.setExpanded(false);
        System.out.println(this.Listadistritos);
        for (int i = 0; i < this.Listaprovincias.size(); i++) {
            //FALTA COLOCAR EL AREA Y POBLACION          
            TreeItem<String> root = new TreeItem<>(this.Listaprovincias.get(i).getNombre());
            for (int j = 0; j < Listacantones.size(); j++) {
                if (this.Listacantones.get(j).getProvincia().getId() == this.Listaprovincias.get(i).getId()) {
                    TreeItem<String> canton = new TreeItem<>(this.Listacantones.get(j).getNombre());
                    for (int k = 0; k < this.Listadistritos.size(); k++) {
                        if (this.Listadistritos.get(k).getCantones().getId() == this.Listacantones.get(j).getId()) {
                            TreeItem<String> distritos = new TreeItem<>(this.Listadistritos.get(k).getNombre());
                            for (int p = 0; p < this.Listaunidades.size(); p++) {
                                if (this.Listaunidades.get(p).getDistrito().getId() == this.Listadistritos.get(k).getId()) {
                                    TreeItem<String> unidades = new TreeItem<>(this.Listaunidades.get(p).getNombre());
                                    distritos.getChildren().addAll(unidades);
                                }

                            }
                            canton.getChildren().addAll(distritos);
                        }

                    }
                    root.getChildren().addAll(canton);

                }
            }
            padre.getChildren().add(root);
        }
        treeview.setRoot(padre);
    }

}
