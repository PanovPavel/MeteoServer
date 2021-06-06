package ServerDemoSocket;

import javafx.event.ActionEvent;

public class ControllerServer {
    MeteoServer meteoServer;
    public void stopButton(ActionEvent actionEvent) {
        meteoServer.stop();
    }
    public void startButton(ActionEvent actionEvent) {
            meteoServer = new MeteoServer(4001);
        while (true) {
            meteoServer.run();
        }
    }
}
