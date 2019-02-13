package model;

import org.apache.log4j.Logger;
import util.ServerSocketAccept;
import util.ThreadHandler;

import java.io.IOException;
import java.net.*;

public class DataSocket {
    private static Logger logger = Logger.getLogger(DataSocket.class);
    private ServerSocket serverSocket;
    private Socket inSocket;

    public void createDataConnection(int port) {
        try {
            final InetAddress addr = InetAddress.getByName(Config.IP_ADDRESS_STRING_POINTS);
            serverSocket = new ServerSocket(port, Config.MAX_CONNECTION_NUMBER, addr);
            int i = 1;

            while (true) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            inSocket = serverSocket.accept();
                            inSocket.setSoTimeout(1000);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                inSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread thread = new Thread(r);
                thread.run();
                i++;
            }
        } catch (IOException e ) {
            logger.info(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
            e.printStackTrace();
        }
    }

    public void startThread(final int port) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                createDataConnection(port);
            }
        };
        Thread thread1 = new Thread(r1);
        thread1.start();
    }

    public void closeSocket() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getInSocket() {
        return inSocket;
    }

    public void setInSocket(Socket inSocket) {
        this.inSocket = inSocket;
    }
}
