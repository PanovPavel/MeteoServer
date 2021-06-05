package ServerDemoSocket;

import javafx.event.ActionEvent;

public class ControllerServer {
    /*public void initialize(){
        try {
            try  {
                try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к созданию потоков ввода/вывода.
                    // теперь мы можем принимать сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    System.out.println(word);
                    // не долго думая отвечает клиенту
                    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    out.flush(); // выталкиваем все из буфера

                } finally { // в любом случае сокет будет закрыт
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }*/
    MeteoServer meteoServer;
    public void stopButton(ActionEvent actionEvent) {
        meteoServer.stop();
    }
    public void startButton(ActionEvent actionEvent) {
        meteoServer = new MeteoServer(4000);
        meteoServer.run();
    }
}
