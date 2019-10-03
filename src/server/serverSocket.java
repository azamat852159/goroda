package server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverSocket {
    public static void main(String[] args) {
        try {
            Socket socket = startServer.createSock();
            BufferedReader readKlientWord = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writeFromTheKlient = new PrintWriter(socket.getOutputStream());
            String str = readKlientWord.readLine();
            writeFromTheKlient.print(str+"s servera");
            System.out.println(str);

        } catch (IOException e) {
            System.out.println("Клиент не подключился");
        }
    }
}

class startServer {
    private ServerSocket serverSocket;

     static Socket createSock () throws IOException{
        return new ServerSocket(8189,2).accept();
     }
}
