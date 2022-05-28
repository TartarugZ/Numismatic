module com.example.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires org.jsoup;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient.fluent;
    requires org.json;
    requires com.fasterxml.jackson.databind;


    opens com.coursework to javafx.fxml;
    exports com.coursework;
    exports com.coursework.Controllers;
    opens com.coursework.Controllers to javafx.fxml;
    exports com.coursework.Serialization;
    opens com.coursework.Serialization to javafx.fxml;
    exports com.coursework.ServerConnection;
    opens com.coursework.ServerConnection to javafx.fxml;
}