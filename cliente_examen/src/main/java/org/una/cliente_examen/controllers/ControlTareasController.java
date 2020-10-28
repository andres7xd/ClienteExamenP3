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
import org.una.cliente_examen.dto.ProyectosDTO;
import org.una.cliente_examen.dto.TareasDTO;
import org.una.cliente_examen.service.ProyectosService;
import org.una.cliente_examen.service.TareasService;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class ControlTareasController implements Initializable {

    @FXML
    private TreeView<String> treeViewProyectos;

    private List<ProyectosDTO> proyectolist = new ArrayList<ProyectosDTO>();
    
     private List<TareasDTO> tareaslist = new ArrayList<TareasDTO>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            proyectolist = ProyectosService.getInstance().getAll();
            System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"+proyectolist.toString());
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        }

     try {
            tareaslist = TareasService.getInstance().getAll();
            System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"+tareaslist.toString());
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        }


        TreeItem<String> root = new TreeItem<>("Proyecto");
        root.setExpanded(true);
        treeViewProyectos.setRoot(root);
        TreeItem<String> item = new TreeItem<>("Tarea 1");
        root.getChildren().add(item);
    }

}
