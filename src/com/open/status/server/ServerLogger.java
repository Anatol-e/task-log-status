package com.open.status.server;

import com.open.status.trasfer.ProtoData;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLogger {

    private Socket socket;

    private Thread connectionThread;

    private ServerSocket serverSocket;

    private final int port;

    private final Stage primaryStage;

    private final ProgressBar statusBar;

    private final Label statusTag;

    public ServerLogger(int port, Stage primaryStage, ProgressBar statusBar, Label statusTag) {
        this.port = port;
        this.primaryStage = primaryStage;
        this.statusBar = statusBar;
        this.statusTag = statusTag;
    }

    public void waitConnection() {
        try {
            serverSocket = new ServerSocket(port);
            connectionThread = getSocketThread(serverSocket);
            connectionThread.start();
        } catch (IOException ex) {
            close();
        }
    }

    private Thread getSocketThread(ServerSocket serverSocket) {
        return new Thread(() -> {
            Thread socketThread = new Thread(() -> {
                try {
                    socket = serverSocket.accept();
                    startLogging();
                } catch (IOException ignored) {
                    System.out.println("Logger Connection closed!");
                }
            });
            socketThread.start();
        });
    }


    private void startLogging() {
        boolean closeConnection = false;
        try {
            while (!closeConnection) {
                ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
                ProtoData protoData = (ProtoData) reader.readObject();
                if (protoData == null) {
                    closeConnection = true;
                } else {
                    String status = protoData.getStatusData();
                    Double progress = Double.valueOf(protoData.getStatusValue());
                    System.out.printf("Logger status : %s, Logger Progress Value %s\n", status, progress);
                    Platform.runLater(() -> {
                        statusBar.setProgress(progress / 100);
                        statusTag.setText(status);
                    });
                }
            }
        } catch (IOException | ClassNotFoundException ignored) {
        } finally {
            close();
        }
    }

    private void killSocket(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Connection socket closed!");
            close();
        }
    }

    private void sleep(int mils) {
        try {
            Thread.sleep(mils);
        } catch (InterruptedException ex) {
            close();
        }
    }

    public void close() {
        connectionThread.interrupt();
        killSocket(serverSocket);
        Platform.runLater(primaryStage::close);
    }
}
