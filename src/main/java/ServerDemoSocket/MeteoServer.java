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
    HashMap<String, Integer> cityTemp = new HashMap<String, Integer>();
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    MeteoServer(int PortServerSocket){
        cityTemp.put("Воронеж", 28);
        cityTemp.put("Липецк", 30);
        cityTemp.put("Москва", 32);
        cityTemp.put("Киев", 34);
        try {
            server = new ServerSocket(PortServerSocket);
            System.out.println("Сервер запущен!"); // хорошо бы серверу
            clientSocket = server.accept(); // accept() будет ждать пока кто-нибудь не захочет подключиться
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* finally {
            try {
                clientSocket.close();
                System.out.println("Сервер остановлен");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String CityNameIn = in.readLine();//сообщение от клиента
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write("Сервер ответ" + CityNameIn);//ответ сервера
            out.flush(); // выталкиваем все из буфера

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
                System.out.println("Сервер остановлен");
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

    }
}
