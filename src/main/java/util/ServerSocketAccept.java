package util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketAccept {

    private static final int PORT = 21;
    private static final int DATA_PORT = 20;

    private static Logger logger = Logger.getLogger(ServerSocketAccept.class);

    public ServerSocketAccept() {
        start();
    }

    private void start() {
        try {
//            InetAddress addr = InetAddress.getByName("192.168.43.234");
            final InetAddress addr = InetAddress.getByName("127.0.0.1");

            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    createConnection(PORT, addr);
                }
            };
            Thread thread1 = new Thread(r1);
            thread1.start();

            Runnable r2 = new Runnable() {
                @Override
                public void run() {
                    createConnection(DATA_PORT, addr);
                }
            };
            Thread thread2 = new Thread(r2);
            thread2.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void createConnection(int port, InetAddress addr) {
        try (ServerSocket socket = new ServerSocket(port, 50, addr))
        {
            int i = 1;

            while (true) {
                Socket inSocket = socket.accept();
                Runnable r = new ThreadHandler(inSocket);
                Thread thread = new Thread(r);
                thread.run();
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
