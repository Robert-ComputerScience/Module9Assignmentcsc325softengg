module com.example.module9assignmentcsc325softeng {
    // JavaFX Dependencies
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Third-Party Dependencies
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.module9assignmentcsc325softeng to javafx.fxml, javafx.graphics;

    // Exports the main package to be used by the JavaFX launcher
    exports com.example.module9assignmentcsc325softeng;
}