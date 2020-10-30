/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Label lblTituloPregunta;
    @FXML
    private ComboBox<String> cbxEditable;
    @FXML
    private Label lblArea;
    @FXML
    private TextField txtarea;
    @FXML
    private Label lblpoblacion;
    @FXML
    private TextField txtpoblacion;
    @FXML
    private Label lblPreguntaTipo;
    @FXML
    private ComboBox<String> cbxTipos;

    private List<ProvinciasDTO> Listprovincias;
    private List<CantonesDTO> Listcanton;
    private List<DistritosDTO> Listdistritos;
    private List<UnidadesDTO> Listunidades;
    private List<TiposDTO> Listtipos;
    private ProvinciaService provinica;
    String PalabraIdentificadora = "";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.Listprovincias = ProvinciaService.getInstance().getAll();
            this.Listcanton = CantonService.getInstance().getAll();
            this.Listdistritos = DistritoService.getInstance().getAll();
            this.Listunidades = UnidadService.getInstance().getAll();
            this.Listtipos = TipoService.getInstance().getAll();
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(AgregarInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cbxTipos.setDisable(true);
        this.lblPreguntaTipo.setDisable(true);
        this.cbxEditable.setDisable(true);
        this.lblTituloPregunta.setDisable(true);
        this.lblArea.setDisable(true);
        this.lblpoblacion.setDisable(true);
        this.txtarea.setDisable(true);
        this.txtpoblacion.setDisable(true);
        this.cbxItems.getItems().addAll("Provincia", "Cantón",
                "Distrito",
                "Unidad",
                "Tipo");

    }

    public void IdentificadorDeInfo() {

        if ("Provincia".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("PROVINCIA");
            this.cbxEditable.setDisable(true);
            this.cbxTipos.setDisable(true);
            this.lblPreguntaTipo.setDisable(true);
            this.lblTituloPregunta.setDisable(true);
            this.lblArea.setDisable(true);
            this.lblpoblacion.setDisable(true);
            this.txtarea.setDisable(true);
            this.txtpoblacion.setDisable(true);

        }
        if ("Cantón".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("CANTÓN");
            this.lblTituloPregunta.setDisable(false);
            this.cbxTipos.setDisable(true);
            this.lblPreguntaTipo.setDisable(true);
            this.cbxEditable.setDisable(false);
            this.lblArea.setDisable(true);
            this.lblpoblacion.setDisable(true);
            this.txtarea.setDisable(true);
            this.txtpoblacion.setDisable(true);
            this.cbxEditable.getItems().clear();
            for (int i = 0; i < this.Listprovincias.size(); i++) {
                this.cbxEditable.getItems().addAll(this.Listprovincias.get(i).getNombre());
                this.lblTituloPregunta.setText("Selecciones una provincia:");
            }

        }

        if ("Distrito".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("DISTRITO");
            this.lblTituloPregunta.setDisable(false);
            this.cbxTipos.setDisable(true);
            this.lblPreguntaTipo.setDisable(true);
            this.cbxEditable.setDisable(false);
            this.lblArea.setDisable(true);
            this.lblpoblacion.setDisable(true);
            this.txtarea.setDisable(true);
            this.txtpoblacion.setDisable(true);
            this.cbxEditable.getItems().clear();
            for (int i = 0; i < this.Listcanton.size(); i++) {
                this.cbxEditable.getItems().addAll(this.Listcanton.get(i).getNombre());
                this.lblTituloPregunta.setText("Selecciones un cantón:");
            }

        }

        if ("Unidad".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("UNIDAD");
            this.lblTituloPregunta.setDisable(false);
            this.cbxEditable.setDisable(false);
            this.cbxTipos.setDisable(false);
            this.lblPreguntaTipo.setDisable(false);
            this.lblArea.setDisable(false);
            this.lblpoblacion.setDisable(false);
            this.txtarea.setDisable(false);
            this.txtpoblacion.setDisable(false);
            this.cbxEditable.getItems().clear();
            for (int i = 0; i < this.Listdistritos.size(); i++) {
                for (int j = 0; j < this.Listtipos.size(); j++) {
                    this.cbxTipos.getItems().addAll(this.Listtipos.get(j).getNombre());
                    this.lblPreguntaTipo.setText("Selecciones un Tipo:");
                }
                this.cbxEditable.getItems().addAll(this.Listdistritos.get(i).getNombre());
                this.lblTituloPregunta.setText("Selecciones una distrito:");
            }
        }

        if ("Tipo".equals(this.PalabraIdentificadora)) {
            this.lblObjetoAgregar.setText("TIPO");
            this.lblTituloPregunta.setDisable(true);
            this.cbxEditable.setDisable(true);
            this.cbxTipos.setDisable(true);
            this.lblPreguntaTipo.setDisable(true);
            this.lblArea.setDisable(true);
            this.lblpoblacion.setDisable(true);
            this.txtarea.setDisable(true);
            this.txtpoblacion.setDisable(true);

        }
    }

    @FXML
    private void OnActionBtnAgregar(ActionEvent event) throws InterruptedException, ExecutionException, IOException {
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
        } else {
            this.CrearObjeto();
        }
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

    @FXML
    private void OnActionCbxInfo(ActionEvent event
    ) {
        this.PalabraIdentificadora = this.cbxItems.getValue();
        this.IdentificadorDeInfo();
    }

    public void ComboBoxEditable() {
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

    public void CrearObjeto() throws InterruptedException, ExecutionException, IOException {
        ProvinciasDTO provinciacapturada = new ProvinciasDTO();
        CantonesDTO cantoncapturada = new CantonesDTO();
        DistritosDTO distritodcapturado = new DistritosDTO();
        TiposDTO tipocapturado = new TiposDTO();
        if ("Provincia".equals(this.PalabraIdentificadora)) {
            ProvinciasDTO newprovincia = new ProvinciasDTO();
            newprovincia.setNombre(this.txtNombre.getText());
            newprovincia.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            ProvinciaService.getInstance().add(newprovincia);
            this.MensajeDeExito();

        }
        if ("Cantón".equals(this.PalabraIdentificadora)) {
            for (int i = 0; i < this.Listprovincias.size(); i++) {
                if (this.Listprovincias.get(i).getNombre() == this.cbxEditable.getValue()) {
                    provinciacapturada = this.Listprovincias.get(i);
                    System.out.println(provinciacapturada);
                }
            }
            CantonesDTO newcanton = new CantonesDTO();
            newcanton.setNombre(this.txtNombre.getText());
            newcanton.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            newcanton.setProvincia(provinciacapturada);
            CantonService.getInstance().add(newcanton);
            this.MensajeDeExito();
            this.LimpiarCampos();
        }
        if ("Distrito".equals(this.PalabraIdentificadora)) {
            DistritosDTO newdistrito = new DistritosDTO();
            for (int i = 0; i < this.Listcanton.size(); i++) {
                if (this.Listcanton.get(i).getNombre() == this.cbxEditable.getValue()) {
                    cantoncapturada = this.Listcanton.get(i);
                    System.out.println(cantoncapturada);
                }
            }
            newdistrito.setNombre(this.txtNombre.getText());
            newdistrito.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            DistritoService.getInstance().add(newdistrito);
            this.MensajeDeExito();
            this.LimpiarCampos();
        }
        if ("Unidad".equals(this.PalabraIdentificadora)) {
            UnidadesDTO newunidad = new UnidadesDTO();
            for (int i = 0; i < this.Listdistritos.size(); i++) {
                for (int j = 0; j < this.Listtipos.size(); j++) {
                    if (this.Listdistritos.get(i).getNombre() == this.cbxEditable.getValue()
                            && this.Listtipos.get(j).getNombre() == this.cbxEditable.getValue()) {
                        distritodcapturado = this.Listdistritos.get(i);
                        tipocapturado = this.Listtipos.get(j);
                        System.out.println(distritodcapturado);
                    }
                }
            }
            Double Areaconvert = Double.valueOf(this.txtarea.getText());
            Long Poblacionconvert = Long.parseLong(this.txtpoblacion.getText());
            newunidad.setNombre(this.txtNombre.getText());
            newunidad.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            newunidad.setDistrito(distritodcapturado);
            newunidad.setArea(BigDecimal.valueOf(Areaconvert));
            newunidad.setPoblacion(BigInteger.valueOf(Poblacionconvert));
            newunidad.setTipos(tipocapturado);
            UnidadService.getInstance().add(newunidad);
            this.MensajeDeExito();
            this.LimpiarCampos();

        }
        if ("Tipo".equals(this.PalabraIdentificadora)) {
            TiposDTO newtipo = new TiposDTO();
            newtipo.setNombre(this.txtNombre.getText());
            newtipo.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            TipoService.getInstance().add(newtipo);
            this.MensajeDeExito();
            this.LimpiarCampos();

        }

    }

    public void MensajeDeExito() {
        String menssage = "!SE HA CREADO EXITOSAMENTE!";
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(
                menssage);
        alert.show();
    }

    public void LimpiarCampos() {
        this.txtNombre.setText("");
        this.txtCodigo.setText("");

    }
}
