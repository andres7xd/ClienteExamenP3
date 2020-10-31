/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cliente_examen.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.una.cliente_examen.App;
import org.una.cliente_examen.dto.ClienteConMembresiaDTO;
import org.una.cliente_examen.dto.ClienteDTO;
import org.una.cliente_examen.dto.CobroPendienteDTO;
import org.una.cliente_examen.dto.MembresiaDTO;
import org.una.cliente_examen.service.ClienteConMembresiaService;
import org.una.cliente_examen.service.ClienteService;
import org.una.cliente_examen.service.CobroPendienteService;
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
    private List<CobroPendienteDTO> cobropendientelist = new ArrayList<CobroPendienteDTO>();
    private CobroPendienteDTO cobroDTO = new CobroPendienteDTO();
    private CobroPendienteService cobroPendienteService = new CobroPendienteService();
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
        
      
        cargarTreeView();
        
        eventoTreeItem();

    }

    public void cargarTreeView()  {
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
        
        try {
            clientemembresialist = ClienteConMembresiaService.getInstance().getAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        try {
            cobropendientelist = CobroPendienteService.getInstance().getAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        TreeItem<String> root = new TreeItem<>("Clientes con membresia");
        root.setExpanded(false);
        for (int i = 0; i < clientemembresialist.size(); i++) {
            TreeItem<String> root2 = new TreeItem<>(clientemembresialist.get(i).getCliente().getCedula());
           
               
                    TreeItem<String> root3 = new TreeItem<>("Membresia");
                    for (int k = 0; k < cobropendientelist.size(); k++) {
                        if(clientemembresialist.get(i).getMembresia().getId() == cobropendientelist.get(k).getMembresia_id().getId()){
                            TreeItem<String> item = new TreeItem<>("Cobro periodo: "+String.valueOf(cobropendientelist.get(k).getPeriodo()));
                            root3.getChildren().addAll(item);
                        }
                       
                    }
                    root2.getChildren().addAll(root3);
                
            
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
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
        alert.setTitle("Mensaje");
        alert.setHeaderText("¡Bienvenido al sistema de AYUDA!\n" +
          "\n"+
"       1-En la esquina izquierda de la pantalla aparece una serie de opciones\n"
        + "Clientes con membresia: Despliega los clientes con membresias.\n" +
         "Membresia despliega todos los cobros pendientes correspondientes a dicha membresia.\n" +
          "\n"+
           "2-Presionando doble click sobre la cedula del cliente y luego sobre membresia\n"
          + "se obtendra la informacion de la membresia.\n" +
          "\n"+
"          3-Al presionar el boton de de Generar cobros, estos se realizaran de manera automatica desde la base de datos\n"
                + "y se observaran por medio de un arbol jerarquico en la parte izquierda de la pantalla.");
        alert.show();
        cargarTreeView();
    }
    
    @FXML
    private void GenerarCobros(ActionEvent event) {
       MembresiaDTO membresiaACobrar = new MembresiaDTO();
        try {
            membresialist = MembresiaService.getInstance().getAll();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int mesesporcobrar;
        Date fecha_inscripcion;
        double monto;
        
        for (int i = 0; i <membresialist.size() ; i++) {
            if("Bimensual".equals(membresialist.get(i).getPeriosidad())){

                mesesporcobrar = 6;
                fecha_inscripcion = membresialist.get(i).getFecha_inscripcion();
                monto = membresialist.get(i).getMonto()/6;
                membresiaACobrar = membresialist.get(i);
                GenerarCobrosFase2(2, 6, monto,fecha_inscripcion,membresiaACobrar,membresialist.get(i).getDescripcion());
                
            }
            
            if("Mensual".equals(membresialist.get(i).getPeriosidad())){

                mesesporcobrar = 12;
                fecha_inscripcion = membresialist.get(i).getFecha_inscripcion();
                monto = membresialist.get(i).getMonto()/12;
                membresiaACobrar = membresialist.get(i);
                GenerarCobrosFase2(1, 12, monto,fecha_inscripcion,membresiaACobrar,membresialist.get(i).getDescripcion());
                
            }
            
            if("Trimestral".equals(membresialist.get(i).getPeriosidad())){

                mesesporcobrar = 4;
                fecha_inscripcion = membresialist.get(i).getFecha_inscripcion();
                monto = membresialist.get(i).getMonto()/4;
                membresiaACobrar = membresialist.get(i);
                GenerarCobrosFase2(3, 4, monto,fecha_inscripcion,membresiaACobrar,membresialist.get(i).getDescripcion());
                
            }
            if("Cuatrimensual".equals(membresialist.get(i).getPeriosidad())){

                mesesporcobrar = 3;
                fecha_inscripcion = membresialist.get(i).getFecha_inscripcion();
                monto = membresialist.get(i).getMonto()/3;
                membresiaACobrar = membresialist.get(i);
                GenerarCobrosFase2(4, 3, monto,fecha_inscripcion,membresiaACobrar,membresialist.get(i).getDescripcion());
                
            }
            
            if("Semestral".equals(membresialist.get(i).getPeriosidad())){

                mesesporcobrar = 2;
                fecha_inscripcion = membresialist.get(i).getFecha_inscripcion();
                monto = membresialist.get(i).getMonto()/2;
                membresiaACobrar = membresialist.get(i);
                GenerarCobrosFase2(6, 2, monto,fecha_inscripcion,membresiaACobrar,membresialist.get(i).getDescripcion());
                
            }
            
             if("Anual".equals(membresialist.get(i).getPeriosidad())){

                mesesporcobrar = 1;
                fecha_inscripcion = membresialist.get(i).getFecha_inscripcion();
                monto = membresialist.get(i).getMonto();
                membresiaACobrar = membresialist.get(i);
                GenerarCobrosFase2(1, 1, monto,fecha_inscripcion,membresiaACobrar,membresialist.get(i).getDescripcion());
                
            }
            
        }
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
        alert.setTitle("Mensaje");
        alert.setHeaderText("¡Se ha generado los COBROS PENDIENTES de manera EXITOSA!");
        alert.show();
        cargarTreeView();
        
    }
    
    public void GenerarCobrosFase2(int frecuenciaPago, int cantidadMeses, double monto, Date fechaInscripcion,MembresiaDTO membresiaPorCobrar ,String tipoServicio){
        int año;
        int periodo = 1;
        Date fechaActual;
        fechaActual= Date.from(Instant.now());
       
        int numero = 0;
        
        
        
        for (int i = 0; i < cantidadMeses; i++) {
            numero = numero + frecuenciaPago;
            cobroDTO.setAño(fechaActual.getYear());
            cobroDTO.setFecha_vencimiento(sumarMesesAFecha(fechaInscripcion, numero));
            cobroDTO.setMonto(monto); 
            cobroDTO.setMembresia_id(membresiaPorCobrar);          
            cobroDTO.setPeriodo(periodo);
            cobroDTO.setTipo_de_servicio(tipoServicio);
            
            try {
                cobroPendienteService.add(cobroDTO);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControlCobrosController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         periodo++;
        }
        
       
    }
    
    public static Date sumarMesesAFecha(Date fecha, int mes){
      if (mes==0) return fecha;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); 
      calendar.add(Calendar.MONTH, mes);  
        System.err.println(calendar.getTime());
      return calendar.getTime(); 
}

 

}
