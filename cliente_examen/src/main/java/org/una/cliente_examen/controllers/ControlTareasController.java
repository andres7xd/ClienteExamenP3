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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
public class ControlTareasController implements Initializable {

    @FXML
    private TreeView treeViewProyectos;

    private List<ProyectosDTO> proyectolist = new ArrayList<ProyectosDTO>();

    private List<TareasDTO> tareaslist = new ArrayList<TareasDTO>();

    ProyectosDTO proyectosDTO = new ProyectosDTO();

    TareasDTO tareasDTO = new TareasDTO();

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

    private Long capturarIdTarea;

    TareasService tareasService = new TareasService();

    private ImageView imgV;

    Date date = new Date();
    Date date2 = new Date();

    private Image imgRojo;
    private Image imgNaranja;
    private Image imgAmarillo;
    private Image imgVerde;

    TextField txtProyectos = new TextField();
    TextField txtTareas = new TextField();
    TextField txtPadre = new TextField();
    @FXML
    private ComboBox<String> cbxFiltroColores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbxFiltroColores.setValue("Todos");
        cbxFiltroColores.getItems().addAll("Rojo", "Naranja", "Amarillo", "Verde", "Todos");

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

        imgRojo = new Image("org/una/cliente_examen/iconos/cuadro rojo.jpg");
        imgNaranja = new Image("org/una/cliente_examen/iconos/cuadro naranja.jpg");
        imgAmarillo = new Image("org/una/cliente_examen/iconos/cuadro amarillo.jpg");
        imgVerde = new Image("org/una/cliente_examen/iconos/cuadro verde.jpg");

        TreeItem<String> root1 = new TreeItem<>("Proyectos");
        root1.setExpanded(false);
        for (int i = 0; i < proyectolist.size(); i++) {
            TreeItem<String> root = new TreeItem<>(proyectolist.get(i).getNombre());
            for (int j = 0; j < tareaslist.size(); j++) {
                if (tareaslist.get(j).getProyectos().getId() == proyectolist.get(i).getId()) {
                    if ((tareaslist.get(j).getPorcentaje_avance() >= 0 && tareaslist.get(j).getPorcentaje_avance() <= 20 && cbxFiltroColores.getValue() == "Rojo") || (tareaslist.get(j).getPorcentaje_avance() >= 0 && tareaslist.get(j).getPorcentaje_avance() <= 20 && cbxFiltroColores.getValue() == "Todos")) {
                        TreeItem<String> item = new TreeItem<>(tareaslist.get(j).getDescripcion(), new ImageView(imgRojo));
                        root.getChildren().addAll(item);
                    } else if ((tareaslist.get(j).getPorcentaje_avance() > 20 && tareaslist.get(j).getPorcentaje_avance() <= 50 && cbxFiltroColores.getValue() == "Naranja") || (tareaslist.get(j).getPorcentaje_avance() > 20 && tareaslist.get(j).getPorcentaje_avance() <= 50 && cbxFiltroColores.getValue() == "Todos")) {
                        TreeItem<String> item = new TreeItem<>(tareaslist.get(j).getDescripcion(), new ImageView(imgNaranja));
                        root.getChildren().addAll(item);
                    } else if ((tareaslist.get(j).getPorcentaje_avance() > 50 && tareaslist.get(j).getPorcentaje_avance() <= 80 && cbxFiltroColores.getValue() == "Amarillo") || (tareaslist.get(j).getPorcentaje_avance() > 50 && tareaslist.get(j).getPorcentaje_avance() <= 80 && cbxFiltroColores.getValue() == "Todos")) {
                        TreeItem<String> item = new TreeItem<>(tareaslist.get(j).getDescripcion(), new ImageView(imgAmarillo));
                        root.getChildren().addAll(item);
                    } else if ((tareaslist.get(j).getPorcentaje_avance() > 80 && tareaslist.get(j).getPorcentaje_avance() <= 100 && cbxFiltroColores.getValue() == "Verde") || (tareaslist.get(j).getPorcentaje_avance() > 80 && tareaslist.get(j).getPorcentaje_avance() <= 100 && cbxFiltroColores.getValue() == "Todos")) {
                        TreeItem<String> item = new TreeItem<>(tareaslist.get(j).getDescripcion(), new ImageView(imgVerde));
                        root.getChildren().addAll(item);
                    }
                }
            }
            root1.getChildren().add(root);
        }
        treeViewProyectos.setRoot(root1);
    }

    public void refrescarTreeView() {
        LlenarTreeView();
    }

    public void EventoTreeItem() {
        treeViewProyectos.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node node = e.getPickResult().getIntersectedNode();
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String description = (String) ((TreeItem) treeViewProyectos.getSelectionModel().getSelectedItem()).getValue();
                if (description != "Proyectos" && CompararNombresProyectos(description) == true) {
                    compDescripcion = description;
                }
                ObtenerIdTarea(description);
                obtenerInfoTareas();
            }
        });
    }

    public void ObtenerIdTarea(String descrip) {
        for (int i = 0; i < tareaslist.size(); i++) {
            if (descrip == tareaslist.get(i).getDescripcion()) {
                capturarIdTarea = tareaslist.get(i).getId();
            }
        }
    }

    public void obtenerInfoTareas() {
        for (int i = 0; i < tareaslist.size(); i++) {
            if (tareaslist.get(i).getDescripcion().equalsIgnoreCase(compDescripcion)) {

                tareasDTO = tareaslist.get(i);

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
    private void actionCrearProyecto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("CreacionProyectos.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }

    @FXML
    private void actionCrearTarea(ActionEvent event) throws IOException {
        AppContext.getInstance().set("tareasDTO", tareasDTO);
        AppContext.getInstance().set("ed", "insertar");

        Parent root = FXMLLoader.load(App.class.getResource("CreacionTareas.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }

    @FXML
    private void actionModificarProyecto(ActionEvent event) throws IOException {

    }

    @FXML
    private void actionEliminarProyecto(ActionEvent event) {
//        ProyectosService proyectosService = new ProyectosService();
//        System.out.println("aaaaaaaaaaaaaaaa" + proyectosDTO.getId());
//        if (proyectosDTO != null) {
//            try {
//                proyectosService.delete(proyectosDTO.getId());
//                Alert info = new Alert(Alert.AlertType.CONFIRMATION);
//                info.setTitle("Mensaje");
//                info.setContentText("El proyecto se eliminó correctamente");
//                info.showAndWait();
//                LlenarTreeView();
//            } catch (Exception e) {
//                Alert info = new Alert(Alert.AlertType.CONFIRMATION);
//                info.setTitle("Error");
//                info.setContentText("No ha seleccionado un proyecto");
//                info.showAndWait();
//                LlenarTreeView();
//            }
//        }
    }

    @FXML
    private void actionModificarTarea(ActionEvent event) throws IOException, InterruptedException, ExecutionException {

        date = java.sql.Date.valueOf(dpFechaInicio.getValue());
        date2 = java.sql.Date.valueOf(dpFechaFinalizacion.getValue());

        tareasDTO.setDescripcion(txtDescripcion.getText());
        tareasDTO.setFecha_inicio(date);
        tareasDTO.setFecha_finalizacion(date2);
        tareasDTO.setImportancia(Double.parseDouble(txtImportancia.getText()));
        tareasDTO.setPrioridad(Double.parseDouble(txtPrioridad.getText()));
        tareasDTO.setUrgencia(Double.parseDouble(txtUrgencia.getText()));
        tareasDTO.setPorcentaje_avance(Double.parseDouble(txtPorcentajeAvance.getText()));
        tareasDTO.setProyectos(proyectosDTO);
        tareasService.modify(tareasDTO.getId(), tareasDTO);

//        Parent root = FXMLLoader.load(App.class.getResource("CreacionTareas.fxml"));
//        Scene creacionDocs = new Scene(root);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(creacionDocs);
//        window.show();
    }

    @FXML
    private void actionEliminarTarea(ActionEvent event) {
        TareasService tareasService = new TareasService();
        if (tareasDTO != null) {
            try {
                tareasService.delete(capturarIdTarea);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Mensaje");
                info.setContentText("La tarea se eliminó correctamente");
                info.showAndWait();
                LlenarTreeView();
            } catch (Exception e) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Error");
                info.setContentText("La tarea no se eliminó");
                info.showAndWait();
            }
        }
    }

    @FXML
    private void actiontvProyectos(MouseEvent event) {

    }

    @FXML
    private void ActionCbxFiltroColores(ActionEvent event) {
        LlenarTreeView();
    }
}
