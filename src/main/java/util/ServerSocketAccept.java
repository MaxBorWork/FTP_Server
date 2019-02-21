package util;

import com.sun.corba.se.spi.activation.Server;
import controller.CommandsController;
import model.Config;
import model.DataSocket;
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

    public ServerSocketAccept() {
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
        try (ServerSocket serverSocket = new ServerSocket(port, 50, addr)) {
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
