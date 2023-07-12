import com.esotericsoftware.kryo.kryo5.minlog.Log;
import com.intellij.ui.components.JBTextArea;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URI;

public class ProjectConnectionUtil {

    private final URI serverUrl = URI.create("wss://ws.postman-echo.com/socketio");
    IO.Options options = IO.Options.builder()
            .setForceNew(true)
            .setMultiplex(true)

            .setTransports(new String[]{"websocket"})
            .setUpgrade(true)
            //.setRememberUpgrade(false)
            .setPath("/socket.io")
            //.setQuery(null)
            //.setExtraHeaders(null)

            .setReconnection(false)
//            .setReconnectionAttempts(Integer.MAX_VALUE)
//            .setReconnectionDelay(1_000)
//            .setReconnectionDelayMax(5_000)
//            .setRandomizationFactor(0.5)
//            .setTimeout(20_000)

            //.setAuth(null)
            .build();
    private final Socket socket = IO.socket(serverUrl, options);


    public void createConnection(JBTextArea resultLabel) {

                socket.on(Socket.EVENT_CONNECT, args -> resultLabel.setText("Socket connected")).
                        on(Socket.EVENT_DISCONNECT, args -> resultLabel.setText("Socket disconnected")).
                        on(Socket.EVENT_CONNECT_ERROR, args -> resultLabel.setText("Error"));
        socket.connect();
    }
}
