package model;

import controller.CommandsController;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

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

    public void printDirectoryList(List<String> directoryList, List<String> fileList) throws IOException {
        String response = "150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)\n";

        for(String dir: directoryList){
            response = response + dir + "\n";
        }
        for(String file: fileList){
            String fullPath = CommandsController.ROOT + "/" + file;
            Path path = Paths.get(fullPath);
            BasicFileAttributes attributes = Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
            String fileDate = attributes.creationTime().toString();
            String fileSize = String.valueOf(attributes.size());

            String fileInfo = file + " " + fileSize + " " + fileDate;
//            writer.println(resp);
            response = response + fileInfo + "\n";
        }
        writer.println(response);
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
