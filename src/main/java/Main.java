import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        MainHandler mainHandler = new MainHandler();
        try (ServerSocket serverSocket = new ServerSocket(8989)) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {

                    mainHandler.writeFile(in.readLine());
                    mainHandler.getCountMaxSum().parseTsvFile();
                    mainHandler.addProduct();
                    System.out.println(mainHandler.returnJsonAnswer());
                }
            }
        } catch (IOException e) {
            System.err.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
