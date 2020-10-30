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
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class EditarInfoController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private ComboBox<String> cbxIInfoAEscoger;
    @FXML
    private TableView TableView;
    String PalabraIdentificadora = "";
    private List<ProvinciasDTO> Listprovincias;
    private List<CantonesDTO> Listcanton;
    private List<DistritosDTO> Listdistritos;
    private List<UnidadesDTO> Listunidades;
    private List<TiposDTO> Listtipos;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        this.Listprovincias = new ArrayList<>();
        this.Listcanton = new ArrayList<>();
        this.Listdistritos = new ArrayList<>();
        this.Listunidades = new ArrayList<>();
        this.Listtipos = new ArrayList<>();
        this.cbxIInfoAEscoger.getItems()
                .addAll("Provincia", "Cantón",
                        "Distrito",
                        "Unidad",
                        "Tipo");

    }

    public void IdentificadorDeInfo() {

        if ("Provincia".equals(this.PalabraIdentificadora)) {
            this.lblTitulo.setText("PROVINCIA");
        }
        if ("Cantón".equals(this.PalabraIdentificadora)) {
            this.lblTitulo.setText("CANTÓN");
        }
        if ("Distrito".equals(this.PalabraIdentificadora)) {
            this.lblTitulo.setText("DISTRITO");
        }
        if ("Unidad".equals(this.PalabraIdentificadora)) {
            this.lblTitulo.setText("UNIDAD");
        }
        if ("Tipo".equals(this.PalabraIdentificadora)) {
            this.lblTitulo.setText("TIPO");
        }
    }

    @FXML
    private void OnActionCbxInfo(ActionEvent event) throws InterruptedException, ExecutionException, IOException {
        this.PalabraIdentificadora = this.cbxIInfoAEscoger.getValue();
        this.IdentificadorDeInfo();
        this.LlenarTabla();
    }

    @FXML
    private void OnActionBtnEditar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class
                .getResource("InfoEditable.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(creacionDocs);

        window.show();
    }

    @FXML
    private void OnActionBtnSalir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class
                .getResource("Dashboard.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(creacionDocs);

        window.show();
    }

    public void LlenarTabla() throws InterruptedException, ExecutionException, IOException {
        if ("Provincia".equals(this.PalabraIdentificadora)) {
            this.Listprovincias = ProvinciaService.getInstance().getAll();

            TableColumn<ProvinciasDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getNombre()));
            TableColumn<ProvinciasDTO, String> colCodigo = new TableColumn<>("codigo");
            colCodigo.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getCodigo())));
            TableColumn<ProvinciasDTO, String> colId = new TableColumn<>("id");
            colId.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getId())));
            this.TableView.getColumns().addAll(colNombre, colCodigo, colId);
            this.TableView.setItems(FXCollections.observableArrayList(this.Listprovincias)); 
            ProvinciasDTO provinciaseleccionada = (ProvinciasDTO) this.TableView.getSelectionModel().getSelectedItem();
            System.out.println(provinciaseleccionada);
        }
        if ("Cantón".equals(this.PalabraIdentificadora)) {
            this.Listcanton = CantonService.getInstance().getAll();
            this.TableView.getColumns().clear();
            TableColumn<CantonesDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));
            TableColumn<CantonesDTO, String> colCodigo = new TableColumn<>("codigo");
            colCodigo.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getCodigo())));
            TableColumn<CantonesDTO, String> colId = new TableColumn<>("id");
            colId.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getId())));
            this.TableView.getColumns().addAll(colNombre, colCodigo, colId);
            this.TableView.setItems(FXCollections.observableArrayList(this.Listcanton));
        }
        if ("Distrito".equals(this.PalabraIdentificadora)) {
            this.Listdistritos = DistritoService.getInstance().getAll();
            this.TableView.getColumns().clear();
            TableColumn<DistritosDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));
            TableColumn<DistritosDTO, String> colCodigo = new TableColumn<>("codigo");
            colCodigo.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getCodigo())));
            TableColumn<DistritosDTO, String> colId = new TableColumn<>("id");
            colId.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getId())));
            this.TableView.getColumns().addAll(colNombre, colCodigo, colId);
            this.TableView.setItems(FXCollections.observableArrayList(this.Listdistritos));
        }
        if ("Tipo".equals(this.PalabraIdentificadora)) {
            this.Listtipos = TipoService.getInstance().getAll();
            this.TableView.getColumns().clear();
            System.out.println(Listprovincias.get(0).getNombre());
            TableColumn<TiposDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));
            TableColumn<TiposDTO, String> colCodigo = new TableColumn<>("codigo");
            colCodigo.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getCodigo())));
            TableColumn<TiposDTO, String> colId = new TableColumn<>("id");
            colId.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getId())));
            this.TableView.getColumns().addAll(colNombre, colCodigo, colId);
            this.TableView.setItems(FXCollections.observableArrayList(this.Listtipos));
        }
        if ("Unidad".equals(this.PalabraIdentificadora)) {
            this.Listunidades = UnidadService.getInstance().getAll();
            this.TableView.getColumns().clear();
            System.out.println(Listprovincias.get(0).getNombre());
            TableColumn<UnidadesDTO, String> colNombre = new TableColumn<>("nombre");
            colNombre.setCellValueFactory((p) -> new SimpleObjectProperty(p.getValue().getNombre()));
            TableColumn<UnidadesDTO, String> colCodigo = new TableColumn<>("codigo");
            colCodigo.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getCodigo())));
            TableColumn<UnidadesDTO, String> colId = new TableColumn<>("id");
            colId.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getId())));
            this.TableView.getColumns().addAll(colNombre, colCodigo, colId);
            this.TableView.setItems(FXCollections.observableArrayList(this.Listunidades));
        }
        
    }
}
