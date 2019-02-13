package model;

import controller.CommandsController;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static controller.CommandsController.ROOT;

public class Messages {
    private PrintWriter writer;

    public Messages( PrintWriter writer){
        this.writer = writer;
    }

    public void printCommandList(){
        writer.println("Command list: ");
        writer.println("\tUSERNAME your_name - ");
        writer.println("\tPASSWORD your_password - ");
        writer.println("\tLIST - ");
        writer.println("\tSTORE file/directory_name - ");
        writer.println("\tRETR file/directory_name - ");
        writer.println("\tMKD directory_name - ");
        writer.println("\tDELE file_name - ");
        writer.println("\tRMD directory_name - ");
    }

    public void printDirectoryList(String dirName, Socket socket) throws IOException {
        StringBuilder response = new StringBuilder("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)\n");
//        StringBuilder response = new StringBuilder();
        String command = "ls -l " + dirName;
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                if (!line.contains("итого")) {
                    if (line.contains("Лют")) {
                        line = line.replace("Лют", "Feb");
                        response.append(line).append("\t\n");
                    }
                }
                line=reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writer.println(response);
//        socket.close();
    }

    public static String pasvMessage() {
        return "(" + Config.IP_ADDRESS_STRING_COMMAS + "," + Config.PORT_20_INT / Config.BIT_SHIFT + "," + Config.PORT_20_INT % Config.BIT_SHIFT + ")";
    }

    public void printRoot(String root) {
        writer.println("Root directory is " + root);
    }

    public void commandUnrecognized() {
       writer.println(ReplyCode.CODE_500);
    }

    public void errorInParameters() {
        writer.println(ReplyCode.CODE_501);
    }


}
