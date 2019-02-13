package util;

import com.sun.corba.se.spi.activation.Server;
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

    public ServerSocketAccept() {
        start();
    }

    private void start() {
//            InetAddress addr = InetAddress.getByName("192.168.43.234");

            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    createConnection(Config.PORT_21_INT);
                }
            };
            Thread thread1 = new Thread(r1);
            thread1.start();
    }

    public void createConnection(int port) {
        try {
            final InetAddress addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
            ServerSocket serverSocket = new ServerSocket(port, Config.MAX_CONNECTION_NUMBER, addr);
            int i = 1;

            while (true) {
                Socket inSocket = serverSocket.accept();
                Runnable r = new ThreadHandler(inSocket);
                Thread thread = new Thread(r);
                thread.run();
                i++;
            }
        } catch (IOException e ) {
            logger.info(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
            e.printStackTrace();
        }
    }
}
