package com.open.status;

import com.open.status.logger.ClientLogger;
import com.open.status.server.MainApp;
import com.open.status.trasfer.ProtoData;
import javafx.application.Application;

import java.io.IOException;

public class SomeLogger {

    private final ClientLogger logger;

    public SomeLogger() {
        new Thread(() -> {
            Application.launch(MainApp.class);
        }).start();
        logger = new ClientLogger(7778);
    }

    public void log(Integer taskStatusValue, String statusDescription) {
        try {
            logger.log(new ProtoData(taskStatusValue, statusDescription));
        } catch (IOException e) {
            end();
        }
    }

    public void end() {
        logger.end();
    }
}
