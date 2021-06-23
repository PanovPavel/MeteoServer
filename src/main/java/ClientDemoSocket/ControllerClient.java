package ClientDemoSocket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ControllerClient {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    @FXML
    TextField textFieldCity;
    @FXML
    TextField textFieldTemp;
    public void initialize(){
        try {
            clientSocket = new Socket("localhost", 4001);
            reader = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(ActionEvent actionEvent) {
        Thread thread = new Thread(runnableClient);
        thread.start();
    }
    Runnable runnableClient = new Runnable() {
        public void run() {
            try {
                System.out.println("0");
                out.write(textFieldCity.getText() + "\n");
                System.out.println("1");
                out.flush();
                System.out.println("2");
                String serverWord = in.readLine();
                System.out.println("3");
                textFieldTemp.setText(serverWord);
                System.out.println("4");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
