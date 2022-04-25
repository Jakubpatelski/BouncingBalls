module com.example.bouncingballs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.bouncingballs to javafx.fxml;
    exports com.example.bouncingballs;
}