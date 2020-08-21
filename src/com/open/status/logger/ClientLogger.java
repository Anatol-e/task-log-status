package com.open.status.logger;

import com.open.status.trasfer.ProtoData;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientLogger {

    private Socket socket;

    public ClientLogger(int port) {
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(ProtoData statusData) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(statusData);
        out.flush();
    }

    public void end() {
        close();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
