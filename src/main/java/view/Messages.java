package view;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Messages {
    PrintWriter writer;

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

    public void printFileList(List<String> fileList) {
        writer.println("File list: ");

        for(String file: fileList){
            writer.println("      " + file);
        }

    }
    public void printDirectoryList(List<String> directoryList) {
        writer.println("Directory list: ");

        for(String file: directoryList){
            writer.println("<DIR>" + file);
        }
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
