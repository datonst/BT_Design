package com.example.chattext;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class ChatTextController {
    @FXML
    public TextArea chatArea = new TextArea();
    public TextField messageField = new TextField();
    public Button sendButton = new Button();
    public ScrollPane scrollPane = new ScrollPane();
    public VBox layout = new VBox();
    public VBox chatPane = new VBox();


    public void initialize(){
        messageField.setPromptText("Type your message...");
    }

    private void animateMessage(HBox chat) {
        // Create a translation animation from bottom to top
        Bounds boundOfSend = chat.getBoundsInLocal();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), chat);
        Bounds boundOfType = messageField.localToScreen(messageField.getBoundsInLocal());
        transition.setFromY(boundOfType.getMaxY());
        transition.setToY(0);
        transition.setByY(-5); // Adjust the distance to your liking
        transition.play();
    }


    private HBox createChat(String nameChat, TextArea chat) {
        HBox hBox = new HBox();
        Label name = new Label(nameChat);
        name.setMaxWidth(50);
        name.getStyleClass().add("name");
        hBox.getChildren().addAll(name, chat);
        hBox.setAlignment(Pos.CENTER_LEFT);  // Align to the left
        HBox.setHgrow(chat, Priority.ALWAYS); // Allow the HBox to grow horizontally
        hBox.setSpacing(10);

        return hBox;
    }
    private String handleChat(String message) {
        message = message.toLowerCase();
       return "REPLY";
    }

    public void resizeTextBox(HBox box,TextArea textArea) {


    }

    private void sendMessage(TextField messageField) {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            TextArea userTextArea = addText(message,2);
            TextArea chatBoxTextArea = addText(handleChat(message),2);
            chatBoxTextArea.getStyleClass().add("AI Chat");
            userTextArea.getStyleClass().add("user");
            HBox userChatHBox = createChat("You :", userTextArea);
            HBox chatBoxAutoHBox = createChatAuto("AI Chat", chatBoxTextArea);

            layout.getChildren()
                    .add(userChatHBox);
            animateMessage(userChatHBox);
            layout.getChildren()
                    .add(chatBoxAutoHBox);
            // Clear the message field
            messageField.clear();

            // Scroll to the bottom of the VBox (if you want to always show the latest messages)
            chatPane.layout();
            chatPane.setPrefHeight(chatPane.getHeight() + userChatHBox.getHeight() + chatBoxAutoHBox.getHeight());
            scrollPane.setVvalue(1.0);
        }
    }
    private TextArea addText(String text, int numberOfLines) {
        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(numberOfLines);
        textArea.setMaxWidth(layout.getPrefWidth() - 100);
        textArea.setMinHeight(Region.USE_PREF_SIZE);  // This allows the TextArea to shrink if the content is smaller
        return textArea;
    }



    private HBox createChatAuto(String nameChat, TextArea chat) {
        HBox hBox = new HBox();
        Label name = new Label(nameChat);
        hBox.setSpacing(10);
        hBox.setVisible(false);
        name.setMaxWidth(100);
        name.getStyleClass().add("name");
        hBox.setAlignment(Pos.CENTER_RIGHT);  // Align to the right
        hBox.getChildren().addAll(chat, name);
        HBox.setHgrow(chat, Priority.ALWAYS); // Allow the HBox to grow horizontally


        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            hBox.setVisible(true);

            // Scroll to the bottom of the VBox (if you want to always show the latest messages)
            chatPane.layout();
            chatPane.setPrefHeight(chatPane.getHeight() + hBox.getHeight());
        });
        pause.play();
        return hBox;
    }

    public void sendAction(MouseEvent event){
        sendMessage( messageField);
    }
    public void sendEnterKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            sendMessage( messageField);
        }
    }

}
