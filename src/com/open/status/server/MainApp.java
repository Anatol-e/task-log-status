package com.open.status.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final String UI_PATH = "view/status-ui.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        FXMLLoader loader = new FXMLLoader(classLoader.getResource(UI_PATH));
        Pane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainServerController controller = loader.getController();
        controller.init(primaryStage);

        primaryStage.setOnCloseRequest(we -> {
            controller.exit();
        });
    }
}
