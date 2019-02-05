package util;

import controller.CommandsController;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    private Socket inSocket;
    private CommandsController controller;

    public ThreadHandler(Socket inSocket) {
        this.inSocket = inSocket;
        controller = new CommandsController();
    }

    public void run() {
        try(InputStream inputStream = inSocket.getInputStream();
            OutputStream outputStream = inSocket.getOutputStream()) {

            Scanner in = new Scanner(inputStream, "UTF-8");

            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),true);

            writer.println("220 Service ready for new user");
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
                String[] lineSlpit = line.split(" ");

                if (lineSlpit.length > 1) {
                    String command = lineSlpit[0];
                    switch (command) {
                        case "USERNAME": {
                            String response = controller.userCommand(line);
                            writer.println(response);
                            break;
                        }
                        case "PASSWORD": {
                            String response = controller.passwordCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        default: {
                            done = true;
                        }
                    }
                } else {
                    done = true;
                }
            }

//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

//            String line = reader.readLine();
//            if(line != null) {
//                System.out.println(line);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
