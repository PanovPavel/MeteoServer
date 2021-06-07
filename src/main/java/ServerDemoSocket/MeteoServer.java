package ServerDemoSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MeteoServer {
    private static Logger log = Logger.getLogger(MeteoServer.class.getName());

    HashMap<String, String> cityTemp = new HashMap<String, String>();
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    MeteoServer(int PortServerSocket){
        FileHandler xmlFile;
        cityTemp.put("Воронеж", "28");
        cityTemp.put("Липецк", "30");
        cityTemp.put("Москва", "32");
        cityTemp.put("Киев", "34");
        try {
            xmlFile = new FileHandler ("src/main/java/log/ogServer.xml", true);
            log.addHandler(xmlFile);
            FileHandler txtFile = new FileHandler ("src/main/java/log/logServer.%u.%g.txt", true);
            SimpleFormatter txtFormatter = new SimpleFormatter ();
            txtFile.setFormatter (txtFormatter);
            log.addHandler (txtFile);

            server = new ServerSocket(PortServerSocket);
            log.log(Level.INFO,"Server running + port" + PortServerSocket);
            System.out.println("The server is running!");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error", e);
            e.printStackTrace();
        }
    }
    public void run(){
        try {
            log.log(Level.INFO,"The server is waiting for the client to connect");
            clientSocket = server.accept();
            log.log(Level.INFO,"the client is connected");

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String CityNameIn = in.readLine();
            log.log(Level.INFO,"The server received the request " + CityNameIn);
            if(cityTemp.get(CityNameIn) == null){
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write("Город не найден");
                out.flush();
            }else {
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write(cityTemp.get(CityNameIn));
                out.flush();
            }
        }
        catch (IOException e) {
            log.log(Level.SEVERE, "Error", e);
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        try {
            clientSocket.close();
            log.log(Level.INFO, "server stopped");
            System.out.println("Сервер остановлен");
            out.close();
            in.close();
            log.log(Level.INFO, "BufferedReader stoped");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error", e);
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                log.log(Level.SEVERE, "Erroe", e);
                e.printStackTrace();
            }
        }

    }
}
