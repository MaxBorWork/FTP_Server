package util;

import controller.CommandsController;
import org.apache.log4j.Logger;
import model.ReplyCode;
import model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    private Socket inSocket;
    private CommandsController controller;
    private Logger log = Logger.getLogger(ThreadHandler.class);

    public ThreadHandler(Socket inSocket, CommandsController controller) {
        this.inSocket = inSocket;
        this.controller = controller;
        ServerSocketAccept.loggerConfig(log);
    }

    public void run() {
        getLocalIP();
        ReplyCode code = new ReplyCode();
        log.debug(LogMessages.CREATE_CONNECTION_MESSAGE);
        try(InputStream inputStream = inSocket.getInputStream();
            OutputStream outputStream = inSocket.getOutputStream()) {

            Scanner in = new Scanner(inputStream,  Config.UTF_8);

            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),true);

            writer.println(CommandsController.reply.codeToMessage.get(220).toString());
            System.out.println("\t" + CommandsController.reply.codeToMessage.get(220).toString());
            log.info(CommandsController.reply.codeToMessage.get(220).toString());
            boolean done = false;

            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                done = controller.getCommand(line, writer, code);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLocalIP() {
        String address = inSocket.getLocalAddress().toString();
        if (address.contains("/")) {
            address = address.replace("/", "");
        }
        address = address.replace(".", ",");
        Config.IP_ADDRESS_STRING_COMMAS = address;
        log.info("Client connected to IP " + Config.IP_ADDRESS_STRING_COMMAS);
    }
}
