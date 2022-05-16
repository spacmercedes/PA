import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class Client {
    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;

        try (
                Socket socket = new Socket(serverAddress, PORT);

                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {

            String request ="continue";

            while (!Objects.equals(request,"stop")) {

                // Send a request to the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Type here...");
                try{
                    request = reader.readLine();}
                catch (IOException exception){
                    exception.printStackTrace();
                }

                if (Objects.equals(request, "exit")) {
                    request = "stop";
                }

                out.println(request);
                // Wait the response from the server ("Hello World!")
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}