package util;

import controller.CommandsController;
import controller.DataTransferringController;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class DataThreadHandler implements Runnable {

    private Socket inSocket;
    private CommandsController controller;
    private Logger log = Logger.getLogger(ServerSocketAccept.class);
    private String processingString;
    private String flag;

    public DataThreadHandler(Socket inSocket, String processingString, String flag, CommandsController controller) {
        this.inSocket = inSocket;
        this.processingString = processingString;
        this.flag = flag;
        this.controller = controller;
    }

    @Override
    public void run() {
        try (InputStream inputStream = inSocket.getInputStream();
             OutputStream outputStream = inSocket.getOutputStream()) {

            DataTransferringController dataController = new DataTransferringController(inputStream, outputStream);
            if (flag.equals("LIST")) {
                dataController.printDirectoryList(processingString);
            } else if (flag.equals("RETR")) {
                dataController.retrieveFile(processingString, controller);
            } else if (flag.equals("STOR")) {
                dataController.storeFile(processingString, controller);
            }

            inSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
