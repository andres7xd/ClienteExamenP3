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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.cliente_examen.App;
import org.una.cliente_examen.dto.ProyectosDTO;
import org.una.cliente_examen.service.ProyectosService;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class CreacionProyectosController implements Initializable {
    
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button actionbtnAtras;
    
    ProyectosDTO proyectosDTO = new ProyectosDTO();
    ProyectosService proyectosService = new ProyectosService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void actionCrearProyecto(ActionEvent event) {
        
        try {
            proyectosDTO.setNombre(txtNombre.getText());
            proyectosDTO.setDescripcion(txtDescripcion.getText());
            proyectosService.add(proyectosDTO);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setTitle("Mensaje");
            alert.setHeaderText("El proyecto se ha creada con éxito.");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("El proyecto no se ha podido crear.");
            alert.show();
        }
    }
    
    @FXML
    private void actionAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("ControlTareas.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }
    
}
