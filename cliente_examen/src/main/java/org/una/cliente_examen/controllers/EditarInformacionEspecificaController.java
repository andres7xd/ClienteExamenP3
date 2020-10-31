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
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.cliente_examen.App;
import org.una.cliente_examen.dto.CantonesDTO;
import org.una.cliente_examen.dto.DistritosDTO;
import org.una.cliente_examen.dto.ProvinciasDTO;
import org.una.cliente_examen.dto.TiposDTO;
import org.una.cliente_examen.dto.UnidadesDTO;
import org.una.cliente_examen.service.CantonService;
import org.una.cliente_examen.service.DistritoService;
import org.una.cliente_examen.service.ProvinciaService;
import org.una.cliente_examen.service.TipoService;
import org.una.cliente_examen.service.UnidadService;

/**
 * FXML Controller class
 *
 * @author rache
 */
public class EditarInformacionEspecificaController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtarea;
    @FXML
    private TextField txtpoblacion;
    @FXML
    private ComboBox<String> cbxTipoDedato;
    @FXML
    private TableView tableviewLugares;
    String referencia;
    private List<ProvinciasDTO> Listprovincias;
    private List<CantonesDTO> Listcanton;
    private List<DistritosDTO> Listdistritos;
    private List<UnidadesDTO> Listunidades;
    private List<TiposDTO> Listtipos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Listprovincias = new ArrayList<>();
        this.Listcanton = new ArrayList<>();
        this.Listdistritos = new ArrayList<>();
        this.Listunidades = new ArrayList<>();
        this.Listtipos = new ArrayList<>();
        this.cbxTipoDedato.getItems().addAll("Provincia", "Cantón",
                "Distrito",
                "Unidad",
                "Tipo");

    }
    // TODO

    @FXML
    private void OnActionBtnGuardar(ActionEvent event) {

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
    private void OnActioncbxTipoDato(ActionEvent event) throws InterruptedException, ExecutionException, IOException {
        this.referencia = this.cbxTipoDedato.getValue();
        this.IdentificadorDeInfo();
    }

    public void IdentificadorDeInfo() throws InterruptedException, ExecutionException, IOException {
        if ("Provincia".equals(this.referencia)) {
            this.Listprovincias = ProvinciaService.getInstance().getAll();
            TableColumn<ProvinciasDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getNombre()));
            this.tableviewLugares.getColumns().addAll(colNombre);
            this.tableviewLugares.setItems(FXCollections.observableArrayList(this.Listprovincias));
//            if(this.tableviewLugares.getSelectionModel().getSelectedItem() != null){
//                 Object provincia = this.tableviewLugares.getSelectionModel().getSelectedItem();
//                 if(this.)
//            }
            
           
        }
        if ("Cantón".equals(this.referencia)) {
            this.Listcanton = CantonService.getInstance().getAll();
            this.tableviewLugares.getColumns().clear();
            TableColumn<CantonesDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));
            this.tableviewLugares.getColumns().addAll(colNombre);
            this.tableviewLugares.setItems(FXCollections.observableArrayList(this.Listcanton));
            
        }
        if ("Distrito".equals(this.referencia)) {
            this.Listdistritos = DistritoService.getInstance().getAll();
            this.tableviewLugares.getColumns().clear();
            TableColumn<DistritosDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));
            this.tableviewLugares.getColumns().addAll(colNombre);
            this.tableviewLugares.setItems(FXCollections.observableArrayList(this.Listdistritos));
        }
        if ("Unidad".equals(this.referencia)) {
            this.Listunidades = UnidadService.getInstance().getAll();
            this.tableviewLugares.getColumns().clear();
            TableColumn<UnidadesDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));

            this.tableviewLugares.getColumns().addAll(colNombre);
            this.tableviewLugares.setItems(FXCollections.observableArrayList(this.Listunidades));
        }
        if ("Tipo".equals(this.referencia)) {
            this.Listtipos = TipoService.getInstance().getAll();
            this.tableviewLugares.getColumns().clear();
            TableColumn<TiposDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));

            this.tableviewLugares.getColumns().addAll(colNombre);
            this.tableviewLugares.setItems(FXCollections.observableArrayList(this.Listtipos));
        }

    }
    
}
