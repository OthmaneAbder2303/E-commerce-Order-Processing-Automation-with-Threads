module ma.ensa.ordcusthreads {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires static lombok;


    opens ma.ensa.ordcusthreads to javafx.fxml;
    exports ma.ensa.ordcusthreads;
}