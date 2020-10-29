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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.una.cliente_examen.dto.ClienteDTO;
import org.una.cliente_examen.service.ClienteService;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class ControlCobrosController implements Initializable {

    private List<ClienteDTO> clientelist = new ArrayList<ClienteDTO>();
    @FXML
    private TreeView<String> tviewControlCobros;
    @FXML
    private Button btnAtras;

    String cedulaactual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarTreeView();
        eventoTreeItem();

    }

    public void cargarTreeView() {
        try {
            clientelist = ClienteService.getInstance().getAll();
            System.out.println(clientelist.toString());
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        TreeItem<String> root = new TreeItem<>("Clientes con membresia");
//          root.setGraphic(imgroot);
        root.setExpanded(false);
        tviewControlCobros.setRoot(root);
        for (int i = 0; i < clientelist.size(); i++) {

            TreeItem<String> item = new TreeItem<>(clientelist.get(i).getCedula());
            root.getChildren().add(item);

        }

    }

    public void eventoTreeItem() {
        tviewControlCobros.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node node = e.getPickResult().getIntersectedNode();
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String name = (String) ((TreeItem) tviewControlCobros.getSelectionModel().getSelectedItem()).getValue();
                if (name != "Clientes con membresia") {
                    cedulaactual = name;
                    obtenerIdCLiente();

                }

            }

        });
    }

    public void obtenerIdCLiente() {

        for (int i = 0; i < clientelist.size(); i++) {

            if (clientelist.get(i).getCedula().equalsIgnoreCase(cedulaactual)) {
                System.out.println("dddddddddddd" + clientelist.get(i).getId());
            }
        }

    }

    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            String name = (String) ((TreeItem) tviewControlCobros.getSelectionModel().getSelectedItem()).getValue();
            System.out.println("Node click: " + name);
        }
    }

    @FXML
    private void Atras(ActionEvent event) {
    }

    private void Evento(ActionEvent e) {
        System.err.println("TOmechichi");
    }

}
