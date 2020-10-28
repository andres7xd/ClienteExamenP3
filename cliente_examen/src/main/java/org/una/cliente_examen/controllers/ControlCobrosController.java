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
import org.una.cliente_examen.dto.ClienteDTO;
import org.una.cliente_examen.service.ClienteService;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class ControlCobrosController implements Initializable {

    private List<ClienteDTO> clientelist = new ArrayList<ClienteDTO>();
    @FXML
    private TreeView<String> tviewControlCobros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            clientelist = ClienteService.getInstance().getAll();
            System.out.println(clientelist.toString());
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        // TODO
        TreeItem<String> root = new TreeItem<>("Clientes");
//           root.setGraphic(imgroot);
            root.setExpanded(true);
            tviewControlCobros.setRoot(root);
            TreeItem<String> item = new TreeItem<>("Cliente1");
                root.getChildren().add(item);
    }    
    
}
