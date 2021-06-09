package ClientDemoSocket;

import java.io.*;
import java.net.Socket;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    /**
     * Отправка названия города на сервер
     * @param city название города
     */
    public void sendCityOnServer(String city) throws IOException{
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        out.write(city + "\n");
        out.flush();
    }

    /**
     * Получить ответ с сервера
     * @return ответ сервера на запрос
     * @throws IOException
     */
    public  String receiveAnswerWithServer() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String serverWord = in.readLine();
        System.out.println(serverWord);
        return serverWord;
    }
    public void closeConnect(){
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Client(int ClientSocket){
        try {
            clientSocket = new Socket("localhost", ClientSocket);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
