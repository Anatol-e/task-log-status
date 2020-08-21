package com.open.status.server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class MainServerController {

    @FXML
    private ProgressBar statusBar;

    @FXML
    private Label statusTag;

    private ServerLogger server;

    public void exit() {
        server.close();
    }

    public void init(Stage primaryStage) {
        statusBar.setProgress(0f);
        statusTag.setText("Loading Status Logger...");
        server = new ServerLogger(7778, primaryStage, statusBar, statusTag);
        server.waitConnection();
    }
}
