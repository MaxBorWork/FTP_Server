package util;

import controller.CommandsController;
import model.Config;
import model.LogMessages;
import org.apache.log4j.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketAccept {

    private CommandsController controller;

    private Logger logger = Logger.getLogger(ServerSocketAccept.class);

    public ServerSocketAccept(String[] args) {
        loggerConfig(logger);
        new Config();
        if (args.length > 0) {
            Config.ROOT = args[0];
            logger.debug("ROOT directory changed, ROOT is " + Config.ROOT);
        }
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
                String clientInfo = "client " + inSocket.getInetAddress().toString() + ", connection " + clientIndex;
                logger.info("Connected " + clientInfo);
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

    public static void loggerConfig(Logger logger) {
        logger.setLevel(Level.ALL);
        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p (not logged in) %c{1}:%L - %m%n");
        try {
            FileAppender fileAppender = new FileAppender(layout, "log_info.log");
            fileAppender.setAppend(false);
            fileAppender.setImmediateFlush(true);
            logger.addAppender(fileAppender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
