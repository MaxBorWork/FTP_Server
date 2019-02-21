package util;

import controller.CommandsController;
import org.apache.log4j.Logger;
import model.ReplyCode;
import model.*;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    private Socket inSocket;
    private CommandsController controller;

    private static Logger log = Logger.getLogger(ServerSocketAccept.class);

    public ThreadHandler(Socket inSocket, CommandsController controller) {
        this.inSocket = inSocket;
        this.controller = controller;
    }

    public void run() {

        log.info(LogMessages.CREATE_CONNECTION_MESSAGE);

        try(InputStream inputStream = inSocket.getInputStream();
            OutputStream outputStream = inSocket.getOutputStream()) {

            Scanner in = new Scanner(inputStream,  Config.UTF_8);

            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),true);

            writer.println(ReplyCode.CODE_220);

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();

               MapOfCommand map = new MapOfCommand();


                System.out.println(line);
                log.info("Get command: " + line);
                String[] lineSlpit = line.split(" ");

                if (lineSlpit.length >= 1) {
                    String command = lineSlpit[0];
                    CommandProccess cP = (CommandProccess) map.commands.get(command);
                    if(cP == null){
                        writer.println(ReplyCode.CODE_500);
                    } else {
                        writer.println(cP.process(line, controller));
                    }

                } else {
                    writer.println(ReplyCode.CODE_500);
                   done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
