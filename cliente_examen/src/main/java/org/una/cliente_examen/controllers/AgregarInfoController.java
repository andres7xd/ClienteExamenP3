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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.cliente_examen.App;

/**
 * FXML Controller class
 *
 * @author rache
 */
public class AgregarInfoController implements Initializable {

    @FXML
    private ComboBox<String> cbxItems;
    @FXML
    private Label lblObjetoAgregar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;

    @FXML
    private Label lblprueba;
    String PalabraIdentificadora = "";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.cbxItems.getItems().addAll("Provincia", "Cantón",
                "Distrito",
                "Unidad",
                "Tipo");
  

    }

    public void IdentificadorDeInfo() {

        if ("Provincia".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("PROVINCIA");
        }
        if ("Cantón".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("CANTÓN");
        }
        if ("Distrito".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("DISTRITO");
        }
        if ("Unidad".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("UNIDAD");
        }
        if ("Tipo".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("TIPO");
        }
    }

    @FXML
    private void OnActionBtnAgregar(ActionEvent event) {
        String camposConError = "";
        if (this.txtNombre.getText() == null || "".equals(this.txtNombre.getText())) {
            camposConError = "Nombre";
        }
        if ("".equals(this.txtCodigo.getText()) || this.txtCodigo.getText() == null) {
            camposConError += "\nCódigo";
        }
        if (!"".equals(camposConError)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Información requerida faltante\n" + camposConError);
            alert.show();
        }
    }

    @FXML
    private void OnActionBtnSalir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("Dashboard.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }

    @FXML
    private void OnActionCbxInfo(ActionEvent event) {
        this.PalabraIdentificadora = this.cbxItems.getValue();
        this.IdentificadorDeInfo();
    }

}
