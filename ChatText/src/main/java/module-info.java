module com.example.chattext {
    requires javafx.fxml;
    requires  javafx.controls;
    requires javafx.web;

    opens com.example.chattext to javafx.fxml;
    exports com.example.chattext;
}