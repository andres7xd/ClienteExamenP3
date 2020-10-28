module org.una.cliente_examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;
    
    opens org.una.cliente_examen to javafx.fxml;
    opens org.una.cliente_examen.controllers to javafx.fxml;
    exports org.una.cliente_examen;
    exports org.una.cliente_examen.controllers;
    opens org.una.cliente_examen.dto;
    exports org.una.cliente_examen.dto;
  
}
