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
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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

    private String compDescripcion;
    @FXML
    private TextField txtId;
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
    private TextField txtProyectoPerteneciente;
    @FXML
    private TextField txtPorcentajeAvance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LlenarTreeView();
        EventoTreeItem();
    }

    public void LlenarTreeView() {

        try {
            proyectolist = ProyectosService.getInstance().getAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            tareaslist = TareasService.getInstance().getAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlTareasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        TreeItem<String> root1 = new TreeItem<>("Proyectos");
        root1.setExpanded(false);
        for (int i = 0; i < proyectolist.size(); i++) {
            TreeItem<String> root = new TreeItem<>(proyectolist.get(i).getNombre());
            for (int j = 0; j < tareaslist.size(); j++) {
                if (tareaslist.get(j).getProyectos().getId() == proyectolist.get(i).getId()) {
                    TreeItem<String> item = new TreeItem<>(tareaslist.get(j).getDescripcion());
                    root.getChildren().addAll(item);
                }
            }
            root1.getChildren().add(root);
        }
        treeViewProyectos.setRoot(root1);
    }

    public void EventoTreeItem() {
        treeViewProyectos.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node node = e.getPickResult().getIntersectedNode();
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String description = (String) ((TreeItem) treeViewProyectos.getSelectionModel().getSelectedItem()).getValue();
                if (description != "Proyectos" && CompararNombresProyectos(description) == true) {
                    compDescripcion = description;
                }
                obtenerIdTareas();
            }
        });
    }

    public void obtenerIdTareas() {
        for (int i = 0; i < tareaslist.size(); i++) {
            if (tareaslist.get(i).getDescripcion().equalsIgnoreCase(compDescripcion)) {
                txtId.setText(tareaslist.get(i).getId().toString());
                LocalDate fechaInicio = parsearFecha(tareaslist.get(i).getFecha_inicio().toString());
                dpFechaInicio.setValue(fechaInicio);
                LocalDate fechaFinalizacion = parsearFecha(tareaslist.get(i).getFecha_finalizacion().toString());
                dpFechaFinalizacion.setValue(fechaFinalizacion);
                txtDescripcion.setText(tareaslist.get(i).getDescripcion());
                txtImportancia.setText(String.valueOf(tareaslist.get(i).getImportancia()));
                txtPrioridad.setText(String.valueOf(tareaslist.get(i).getPrioridad()));
                txtUrgencia.setText(String.valueOf(tareaslist.get(i).getUrgencia()));
                txtPorcentajeAvance.setText(String.valueOf(tareaslist.get(i).getPorcentaje_avance()));
                txtProyectoPerteneciente.setText(tareaslist.get(i).getProyectos().getNombre());
            }
        }
    }

    public boolean CompararNombresProyectos(String nombreSeleccionado) {
        for (int i = 0; i < proyectolist.size(); i++) {
            if (nombreSeleccionado != proyectolist.get(i).getNombre()) {
                return true;
            }
        }
        return false;
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
    private void actionCrearProyecto(ActionEvent event) {
    }

    @FXML
    private void actionCrearTarea(ActionEvent event) {
    }

    @FXML
    private void actionModificarProyecto(ActionEvent event) {
    }

    @FXML
    private void actionEliminarProyecto(ActionEvent event) {
    }

    @FXML
    private void actionModificarTarea(ActionEvent event) {
    }

    @FXML
    private void actionEliminarTarea(ActionEvent event) {
    }

}
