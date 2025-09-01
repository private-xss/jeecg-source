package com.security.scanner;

import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("f.fxml")));
        primaryStage.setTitle("jeecgExploit v.4.3 项目重构版");
        primaryStage.setScene(new Scene(root, 1180.0, 900.0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
