module com.example.employeeregistration {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.employeeregistration to javafx.fxml;
    exports com.example.employeeregistration;
}