module com.example.matuskaalex_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.matuskaalex_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports com.example.matuskaalex_javafxrestclientdolgozat;

}