package ServerDemoSocket;

import javafx.event.ActionEvent;

public class ControllerServer {
    MeteoServer meteoServer;
    Thread thread;
    public void stopButton(ActionEvent actionEvent) {
        thread.stop();
        meteoServer.stop();
    }

    public void startButton(ActionEvent actionEvent) {
        thread = new Thread(runnableServer);
        thread.start();
    }
    Runnable runnableServer = new Runnable() {
        public void run() {
            meteoServer = new MeteoServer(4001);
            while (true) {
                meteoServer.run();
            }
        }
    };
}
