package com.chatRecource;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;



import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//websocket sessions for instant Map -brittany fisher

//creates a new endpoint for each new connection to the Map page
@ServerEndpoint(value = "/map")
public class MapServerpoint {

	// list of unique sessions
	private static final Set<MapServerpoint> connect = new CopyOnWriteArraySet<MapServerpoint>();

	//holds the name of this connection
	private final String name;
	
	
	private Session sess;

	public MapServerpoint() {
		Random ran = new Random();
		name = ((Integer) ran.nextInt(1000)).toString();
	}

	// adds new session to the connection and sends the new connection to all
	// users
	@OnOpen
	public void start(Session session) {
		sess = session;
		connect.add(this);
	}

	// removes the user from the connection and sends the disconnection to all
	// users
	@OnClose
	public void end() {
		connect.remove(this);
	}

	// server gets a message and sends it to all connections
	@OnMessage
	public void incoming(String msg) {
		for (MapServerpoint client : connect) {
			try {
				synchronized (client) {
					if (!client.name.equals(name)) {
						client.sess.getBasicRemote().sendText(msg);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				connect.remove(client);
				try {
					client.sess.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}