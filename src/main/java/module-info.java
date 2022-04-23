module com.example.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires org.jsoup;


    opens com.example.coursework to javafx.fxml;
    exports com.example.coursework;
}