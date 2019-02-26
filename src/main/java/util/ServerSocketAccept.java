package util;

import controller.CommandsController;
import model.Config;
import model.LogMessages;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketAccept {

    private CommandsController controller;
    private Logger logger = Logger.getLogger(ServerSocketAccept.class);

    public ServerSocketAccept(String[] args) {
        if (args.length > 0) {
            Config.ROOT = args[0];
        } else {
            Config.ROOT = "/etc/ftRoot";
        }
        controller = new CommandsController();
        start();
    }

    private void start() {
        try {
            InetAddress addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
            createConnection(Config.PORT_21_INT, addr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void createConnection(int port, InetAddress addr) {
        try (ServerSocket serverSocket = new ServerSocket(port, Config.MAX_CONNECTION_NUMBER, addr)) {
            int clientIndex = 1;

            while (true) {
                Socket inSocket = serverSocket.accept();
                logger.info("Connected client " + clientIndex);
                Runnable r = new ThreadHandler(inSocket, controller);
                Thread thread = new Thread(r);
                thread.start();
                clientIndex++;
            }
        } catch (IOException e ) {
            logger.info(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
            e.printStackTrace();
        }
    }
}
