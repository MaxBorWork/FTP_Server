package util;

import controller.CommandsController;
import model.Config;
import model.LogMessages;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.*;

public class DataSocket {
    private static Logger log = Logger.getLogger(DataSocket.class);
    private ServerSocket serverSocket;
    private Socket inSocket;

    public DataSocket() {
        ServerSocketAccept.loggerConfig(log);
        startThread(Config.PORT_20_INT);
    }

    public void createDataConnection(String processingString, String flag, CommandsController controller) {
        try {
            inSocket = serverSocket.accept();
            log.info("Data connection established");
            Runnable r = new DataThreadHandler(inSocket, processingString, flag, controller);
            Thread thread = new Thread(r);
            thread.start();
            thread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startThread(final int port) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    final InetAddress addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
                    serverSocket = new ServerSocket(port, Config.MAX_CONNECTION_NUMBER, addr);
                } catch (IOException e) {
                    log.debug(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(r1);
        thread1.start();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
