/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.una.cliente_examen.App;
import org.una.cliente_examen.dto.ClienteConMembresiaDTO;
import org.una.cliente_examen.dto.ClienteDTO;
import org.una.cliente_examen.dto.MembresiaDTO;
import org.una.cliente_examen.service.ClienteConMembresiaService;
import org.una.cliente_examen.service.ClienteService;
import org.una.cliente_examen.service.MembresiaService;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class ControlCobrosController implements Initializable {

    private List<ClienteDTO> clientelist = new ArrayList<ClienteDTO>();
    private List<ClienteConMembresiaDTO> clientemembresialist = new ArrayList<ClienteConMembresiaDTO>();
    private List<MembresiaDTO> membresialist = new ArrayList<MembresiaDTO>();
    
    @FXML
    private TreeView<String> tviewControlCobros;
    @FXML
    private Button btnAtras;

    String cedulaactual;
    @FXML
    private TextField txtPeriosidad;
    @FXML
    private TextField txtMonto;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtFechaInscripcion;
    @FXML
    private Button btnAyuda;
    @FXML
    private Button btnGenerarCobros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            cargarTreeView();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eventoTreeItem();

    }

    public void cargarTreeView() throws InterruptedException, ExecutionException, IOException {
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
        
        clientemembresialist = ClienteConMembresiaService.getInstance().getAll();
      

//        TreeItem<String> root = new TreeItem<>("Clientes con membresia");
////          root.setGraphic(imgroot);
//        root.setExpanded(false);
//        tviewControlCobros.setRoot(root);
//        for (int i = 0; i < clientelist.size(); i++) {
//
//            TreeItem<String> item = new TreeItem<>(clientelist.get(i).getCedula());
//            root.getChildren().add(item);
//
//        }
        TreeItem<String> root = new TreeItem<>("Clientes con membresia");
        root.setExpanded(false);
        for (int i = 0; i < clientemembresialist.size(); i++) {
            TreeItem<String> root2 = new TreeItem<>(clientemembresialist.get(i).getCliente().getCedula());
           
               
                    TreeItem<String> item = new TreeItem<>("Membresia");
                    
                    root2.getChildren().addAll(item);
                
            
            root.getChildren().add(root2);
        }
        tviewControlCobros.setRoot(root);

    }

    public void eventoTreeItem() {
        tviewControlCobros.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node node = e.getPickResult().getIntersectedNode();
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String name = (String) ((TreeItem) tviewControlCobros.getSelectionModel().getSelectedItem()).getValue();
                
                if (name != "Membresia" && name != "Clientes con membresia") {
                    cedulaactual = name;
                }
                else if(name =="Membresia") {
                    System.out.println(cedulaactual);
                       try {
                        AccionEvento();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                   }
                   

                

            }

        });
    }

    public void AccionEvento() throws InterruptedException, ExecutionException, IOException {

        for (int i = 0; i < clientelist.size(); i++) {

            if (clientelist.get(i).getCedula().equalsIgnoreCase(cedulaactual)) {
//                System.out.println("dddddddddddd" + clientelist.get(i).getId());
                CargarInfoMembresia();

            }
        }

    }

    public void CargarInfoMembresia() throws InterruptedException, ExecutionException, IOException {
        boolean bandera = true;
        clientemembresialist = ClienteConMembresiaService.getInstance().getAll();

        for (int i = 0; i < clientemembresialist.size() && bandera == true; i++) {
            if (cedulaactual.equalsIgnoreCase(clientemembresialist.get(i).getCliente().getCedula())) {
                txtPeriosidad.setText(clientemembresialist.get(i).getMembresia().getPeriosidad());
                txtMonto.setText(String.valueOf(clientemembresialist.get(i).getMembresia().getMonto()));
                txtDescripcion.setText(clientemembresialist.get(i).getMembresia().getDescripcion());
                asignarFechaTexfield(i);

                bandera = false;
            }

        }
        bandera = true;


    }

    @FXML
    private void Atras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("Dashboard.fxml"));
        Scene creacionDocs = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }
    
    public void asignarFechaTexfield(int posicion){
        Date myDate = clientemembresialist.get(posicion).getMembresia().getFecha_inscripcion();
        txtFechaInscripcion.setText(String.valueOf(new SimpleDateFormat("dd-MM-yyyy").format(myDate)));

    }

    @FXML
    private void Ayuda(ActionEvent event) {
    }

    @FXML
    private void GenerarCobros(ActionEvent event) {
        
//        for (int i = 0; i <mem ; i++) {
//            Object object = arr[i];
//            
//        }
        
    }
    
    public static Date sumarDiasAFecha(Date fecha, int mes){
      if (mes==0) return fecha;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); 
      calendar.add(Calendar.MONTH, mes);  
        System.err.println(calendar.getTime());
      return calendar.getTime(); 
}

}
