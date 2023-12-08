package com.example.chattext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;

public class ChatTextApp extends Application {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 650;
    @Override
    public void start(Stage primaryStage) throws IOException {
//        System.out.println(ChatTextApp.class.getResource("abc"));
        FXMLLoader fxmlLoader = new FXMLLoader(System.getProperties().r);
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChatText");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
