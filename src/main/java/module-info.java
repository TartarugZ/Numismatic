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
    requires java.desktop;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires commons.collections4;
    requires com.fasterxml.jackson.datatype.guava;
    requires java.logging;


    opens com.coursework to javafx.fxml;
    exports com.coursework;
    exports com.coursework.controllers;
    opens com.coursework.controllers to javafx.fxml;
    exports com.coursework.server_connection;
    opens com.coursework.server_connection to javafx.fxml;
    exports com.coursework.functions;
    opens com.coursework.functions to javafx.fxml;
    exports com.coursework.objects;
    opens com.coursework.objects to javafx.fxml;
}