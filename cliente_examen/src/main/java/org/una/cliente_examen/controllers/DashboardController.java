/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.una.cliente_examen.App;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class DashboardController implements Initializable {

    @FXML
    private Button btnControlTareas;
    @FXML
    private Button btnControlPoblaciones;
    @FXML
    private Button btnControlMembresias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionBtnControlTareas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("ControlTareas.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }

    @FXML
    private void actionBtnControlPoblaciones(ActionEvent event) {
    }

    @FXML
    private void actionBtnControlMembresias(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("ControlCobros.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }
    
   
    
}
