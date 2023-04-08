import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerClient {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8989); // ждем подключения
        ) {
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                out.write("{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
