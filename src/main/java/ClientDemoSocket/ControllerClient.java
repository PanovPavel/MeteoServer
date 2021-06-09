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
            /**
             * Отправить название города на сервер
             */
            client.sendCityOnServer(textFieldCity.getText());
            /**
             * Получить ответ сервера
             */
            textFieldTemp.setText("Температура = " + client.receiveAnswerWithServer());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
           client.closeConnect();
        }
    }
}
