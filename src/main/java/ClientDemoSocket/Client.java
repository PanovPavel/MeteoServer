package ClientDemoSocket;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public String changeMessageWithServer(String city) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        String word = city;
        out.write(word + "\n");
        out.flush();
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
