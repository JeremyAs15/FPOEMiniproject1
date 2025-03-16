module com.example.miniproject1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miniproject1 to javafx.fxml;
    opens com.example.miniproject1.controllers to javafx.fxml;
    exports com.example.miniproject1;
}