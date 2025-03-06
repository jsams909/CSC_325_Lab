module com.example.csc_325_lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc_325_lab to javafx.fxml;
    exports com.example.csc_325_lab;
}