package ClientDemoSocket;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public Client(int ClientSocket){
        try {
            clientSocket = new Socket("localhost", ClientSocket);
            reader = new BufferedReader(new InputStreamReader(System.in));/*Запрос соединения*/
            // читать соообщения с сервера
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // писать туда же
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println("Вы что-то хотели сказать? Введите это здесь:");

            String word = reader.readLine(); // ждём пока клиент что-нибудь
            // не напишет в консоль
            out.write(word + "\n"); // отправляем сообщение на сервер
            out.flush();
            String serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
                System.out.println("Клиент был закрыт...");
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
