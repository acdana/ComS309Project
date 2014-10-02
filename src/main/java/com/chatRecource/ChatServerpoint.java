import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat")
public class ChatServerpoint {

	private static final Set<ChatServerpoint> connect = new CopyOnWriteArraySet<ChatServerpoint>();

	private final String name;
	private Session session;

	public ChatServerpoint() {
		Random ran = new Random();
		name = ((Integer) ran.nextInt(1000)).toString();
	}

	@OnOpen
	public void start(Session session) {
		this.session = session;
		connect.add(this);
		String message = name + " has joined.";
		broadcast(message);
	}

	@OnClose
	public void end() {
		connect.remove(this);
		String message = name + " has disconnected.";
		broadcast(message);
	}

	@OnMessage
	public void incoming(String message) {
		broadcast(name + ": " + message);
	}

	private static void broadcast(String msg) {
		for (ChatServerpoint client : connect) {
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				connect.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {
				}
				String message = client.name + " has been disconnected.";
				broadcast(message);
			}
		}
	}
}