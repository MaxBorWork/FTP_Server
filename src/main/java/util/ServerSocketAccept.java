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

    private Logger logger = Logger.getLogger(ServerSocketAccept.class);
    private InetAddress addr;
    private CommandsController controller;

    public ServerSocketAccept() {

        controller = new CommandsController();
        start();
    }

    private void start() {
        try {
            addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
            createConnection(Config.PORT_21_INT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//        Runnable r1 = new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            };
//            Thread thread1 = new Thread(r1);
//            thread1.start();
    }

    public void createConnection(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
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
