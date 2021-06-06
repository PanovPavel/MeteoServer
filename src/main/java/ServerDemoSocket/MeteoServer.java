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

public class MeteoServer {
    HashMap<String, String> cityTemp = new HashMap<String, String>();
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    MeteoServer(int PortServerSocket){
        cityTemp.put("Воронеж", "28");
        cityTemp.put("Липецк", "30");
        cityTemp.put("Москва", "32");
        cityTemp.put("Киев", "34");
        try {
            server = new ServerSocket(PortServerSocket);
            System.out.println("Сервер запущен!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        try {
            clientSocket = server.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String CityNameIn = in.readLine();
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
            System.out.println("Сервер остановлен");
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
