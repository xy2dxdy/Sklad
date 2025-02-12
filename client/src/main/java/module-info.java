module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kpclient to javafx.fxml;
    opens pojo to javafx.base;
    opens enums to javafx.base;
    exports kpclient;
    exports kpclient.controllers;
    opens kpclient.controllers to javafx.fxml;
    exports kpclient.controllers.order;
    opens kpclient.controllers.order to javafx.fxml;
}