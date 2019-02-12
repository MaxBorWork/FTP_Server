package util;

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

    private static Logger logger = Logger.getLogger(ServerSocketAccept.class);

    private static InetAddress addr;

    public ServerSocketAccept() {
        start();
    }

    private void start() {
        try {
//            InetAddress addr = InetAddress.getByName("192.168.43.234");
            addr = InetAddress.getByName("127.0.0.1");

            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    startThread(Config.PORT_21_INT);
                }
            };
            Thread thread1 = new Thread(r1);
            thread1.start();
        }
    }

    static void startThread(final int port) {
        try {
            final InetAddress addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    DataSocket dataSocket = new DataSocket();
                    dataSocket.createConnection(port,  addr);
                }
            };
            Thread thread1 = new Thread(r1);
            thread1.start();
        } catch (UnknownHostException e) {
            logger.info(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
            e.printStackTrace();
        }
    }
}
