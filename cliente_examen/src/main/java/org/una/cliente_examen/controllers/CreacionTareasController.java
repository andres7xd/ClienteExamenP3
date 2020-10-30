/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.cliente_examen.App;
import org.una.cliente_examen.dto.ProyectosDTO;
import org.una.cliente_examen.dto.TareasDTO;
import org.una.cliente_examen.service.ProyectosService;
import org.una.cliente_examen.service.TareasService;
import org.una.cliente_examen.utils.AppContext;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class CreacionTareasController implements Initializable {

    @FXML
    private TextField txtDescripcion;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFinalizacion;
    @FXML
    private TextField txtImportancia;
    @FXML
    private TextField txtPrioridad;
    @FXML
    private TextField txtUrgencia;
    @FXML
    private TextField txtPorcentajeAvance;
    @FXML
    private ComboBox<ProyectosDTO> cbxProyectos;

    Date date = new Date();
    Date date2 = new Date();

    TareasDTO tareasDTO = new TareasDTO();
    TareasService tareasService = new TareasService();
    ProyectosDTO proyectosDTO = new ProyectosDTO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProyectosService proyectosService = new ProyectosService();
        List<ProyectosDTO> proyectolist = new ArrayList<>();
        try {
            proyectolist = (List<ProyectosDTO>) proyectosService.getAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(CreacionTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CreacionTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreacionTareasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbxProyectos.setItems(FXCollections.observableArrayList(proyectolist));

        if (AppContext.getInstance().get("ed").equals("edit")) {
            TareasDTO tareasDTO = new TareasDTO();
            tareasDTO = (TareasDTO) AppContext.getInstance().get("tareasDTO");
            txtDescripcion.setText(tareasDTO.getDescripcion());
            txtImportancia.setText(String.valueOf(tareasDTO.getImportancia()));
            txtUrgencia.setText(String.valueOf(tareasDTO.getUrgencia()));
            txtPrioridad.setText(String.valueOf(tareasDTO.getPrioridad()));
            txtPorcentajeAvance.setText(String.valueOf(tareasDTO.getPorcentaje_avance()));
            LocalDate fecha = parsearFecha(tareasDTO.getFecha_inicio().toString());
            dpFechaInicio.setValue(fecha);
            LocalDate fecha2 = parsearFecha(tareasDTO.getFecha_finalizacion().toString());
            dpFechaFinalizacion.setValue(fecha2);
            
            System.out.println("88888888888888888"+tareasDTO.getDescripcion());
        }

    }

    public static LocalDate parsearFecha(String fecha) {
        String mes = fecha.substring(4, 7);
        String mesNum = "";
        String dia = fecha.substring(8, 10);
        String anno = fecha.substring(24, 28);
        String fechaParseada = "";
        switch (mes) {
            case "Jan":
                mesNum = "01";
                break;
            case "Feb":
                mesNum = "02";
                break;
            case "Mar":
                mesNum = "03";
                break;
            case "Apr":
                mesNum = "04";
                break;
            case "May":
                mesNum = "05";
                break;
            case "Jun":
                mesNum = "06";
                break;
            case "Jul":
                mesNum = "07";
                break;
            case "Aug":
                mesNum = "08";
                break;
            case "Sep":
                mesNum = "09";
                break;
            case "Oct":
                mesNum = "10";
                break;
            case "Nov":
                mesNum = "11";
                break;
            case "Dec":
                mesNum = "12";
                break;

        }

        fechaParseada = anno + "-" + mesNum + "-" + dia;
        LocalDate localDate = LocalDate.parse(fechaParseada);
        return localDate;
    }

    @FXML
    private void actionCrearTarea(ActionEvent event) throws InterruptedException, ExecutionException, IOException {

        date = java.sql.Date.valueOf(dpFechaInicio.getValue());
        date2 = java.sql.Date.valueOf(dpFechaFinalizacion.getValue());

        if (!AppContext.getInstance().get("ed").equals("edit")) {
            tareasDTO.setDescripcion(txtDescripcion.getText());
            tareasDTO.setFecha_inicio(date);
            tareasDTO.setFecha_finalizacion(date2);
            tareasDTO.setImportancia(Double.parseDouble(txtImportancia.getText()));
            tareasDTO.setPrioridad(Double.parseDouble(txtPrioridad.getText()));
            tareasDTO.setUrgencia(Double.parseDouble(txtUrgencia.getText()));
            tareasDTO.setPorcentaje_avance(Double.parseDouble(txtPorcentajeAvance.getText()));
            tareasDTO.setProyectos(proyectosDTO);
            tareasService.add(tareasDTO);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setTitle("Mensaje");
            alert.setHeaderText("Tarea creada con éxito.");
            alert.show();
        }else{
            proyectosDTO = (ProyectosDTO) AppContext.getInstance().get("proyectosDTO");
            
            tareasDTO.setDescripcion(txtDescripcion.getText());
            tareasDTO.setFecha_inicio(date);
            tareasDTO.setFecha_finalizacion(date2);
            tareasDTO.setImportancia(Double.parseDouble(txtImportancia.getText()));
            tareasDTO.setPrioridad(Double.parseDouble(txtPrioridad.getText()));
            tareasDTO.setUrgencia(Double.parseDouble(txtUrgencia.getText()));
            tareasDTO.setPorcentaje_avance(Double.parseDouble(txtPorcentajeAvance.getText()));
            tareasDTO.setProyectos(proyectosDTO);
            tareasService.modify(tareasDTO.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert.setTitle("Mensaje");
            alert.setHeaderText("Tarea creada con éxito.");
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

    @FXML
    private void actionCbxProyecto(ActionEvent event) {
//        if (cbxProyectos.getSelectionModel().getSelectedItem() != null) {
//            proyectosDTO = (ProyectosDTO) cbxProyectos.getSelectionModel().getSelectedItem();
//        }
    }

}
