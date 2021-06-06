package ClientDemoSocket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import javafx.scene.control.TextField;
public class ControllerClient {
    @FXML
    TextField textFieldCity, textFieldTemp;

    public void send(ActionEvent actionEvent) {
        Client client = new Client(4001);
        try {
            client.changeMessageWithServer(textFieldCity.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            client.closeConnect();
        }
    }
}
