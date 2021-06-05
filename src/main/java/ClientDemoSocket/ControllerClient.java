package ClientDemoSocket;
import java.io.*;
import java.net.Socket;

public class ControllerClient {
    public void initialize(){
        Thread thread = new Thread(runnable);
        thread.start();
    }
    Runnable runnable = new Runnable() {
        public void run() {
                Client client = new Client(4000);
        }
    };


}
