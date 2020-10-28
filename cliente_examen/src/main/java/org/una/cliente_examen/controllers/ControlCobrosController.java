/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class ControlCobrosController implements Initializable {

    @FXML
    private TreeView<String> tviewControlCobros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TreeItem<String> root = new TreeItem<>("Clientes");
//           root.setGraphic(imgroot);
            root.setExpanded(true);
            tviewControlCobros.setRoot(root);
            TreeItem<String> item = new TreeItem<>("Cliente1");
                root.getChildren().add(item);
    }    
    
}
