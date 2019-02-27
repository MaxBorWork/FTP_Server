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

class ServerSocketAccept {

    private CommandsController controller;

    private Logger logger = Logger.getLogger(ServerSocketAccept.class);

    ServerSocketAccept(String[] args) {
        if (args.length > 0) {
            Config.ROOT = args[0];
            logger.debug("ROOT directory changed, ROOT is " + Config.ROOT);
        }
        new Config();
        controller = new CommandsController();
        start();
    }

    private void start() {
        try {
            InetAddress addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
            createConnection(addr);
        } catch (UnknownHostException e) {
            logger.debug(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createConnection(InetAddress addr) {
        try (ServerSocket serverSocket = new ServerSocket(Config.PORT_21_INT, Config.MAX_CONNECTION_NUMBER, addr)) {
            int clientIndex = 1;

            while (true) {
                Socket inSocket = serverSocket.accept();
                logger.debug("Connected client " + clientIndex);
                Runnable r = new ThreadHandler(inSocket, controller);
                Thread thread = new Thread(r);
                thread.start();
                clientIndex++;
            }
        } catch (IOException e ) {
            logger.debug(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
            e.printStackTrace();
        }
    }
}
