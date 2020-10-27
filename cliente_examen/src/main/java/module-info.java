module org.una.cliente_examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.una.cliente_examen to javafx.fxml;
    exports org.una.cliente_examen;
}
