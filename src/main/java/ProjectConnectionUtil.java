import com.intellij.ui.components.JBTextArea;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;

public class ProjectConnectionUtil {

    String serverUrl = "wss://ws.postman-echo.com";
    IO.Options options = IO.Options.builder()
            .setForceNew(true)
            .setReconnection(false)
            .build();

    public void createConnection(JBTextArea resultLabel) {

        try {
            Socket socket = IO.socket(serverUrl, options);
            socket.on(Socket.EVENT_CONNECT, args -> resultLabel.setText("Socket connected")).
                    on(Socket.EVENT_DISCONNECT, args -> resultLabel.setText("Socket disconnected"));
            socket.connect();
        } catch (URISyntaxException ex) {
            resultLabel.setText("Socket connection error");
        }
    }
}
