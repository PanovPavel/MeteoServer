package ServerDemoSocket;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ControllerServer {
    HashMap<String, String> cityTemp = new HashMap<String, String>();
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public void initialize(){
        cityTemp.put("Воронеж", "28");
        cityTemp.put("Липецк", "30");
        cityTemp.put("Москва", "32");
        cityTemp.put("Киев", "34");
    }


    public void startButton(ActionEvent actionEvent) {
       Thread thread = new Thread(runnableServer);
       thread.start();
    }

    public void stopButton(ActionEvent actionEvent) {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Runnable runnableServer = new Runnable() {
        public void run() {
            try {
                server = new ServerSocket(4001);
                clientSocket = server.accept();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("0");
                String CityNameIn = in.readLine();
                System.out.println("1");
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println("2");
                out.write(cityTemp.get(CityNameIn));
                System.out.println("3");
                out.flush();
                System.out.println("4");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
