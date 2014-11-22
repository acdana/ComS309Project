package com.chatRecource;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
//websocket sessions for instant chat -brittany fisher

//creates a new endpoint for each new connection to the chat page
//is to eventually be connected to specific users not just random numbers
@ServerEndpoint(value = "/chat/{saleID}/{username}")
public class ChatServerpoint {
		
	//list of unique sessions
	private static final Set<ChatServerpoint> connect = new CopyOnWriteArraySet<ChatServerpoint>();

	//holds the name of this connection
	private String name;
	private Session sess;

	//temp anon naming schem
	public ChatServerpoint() {
		name = null;
	}

	//adds new session to the connection and sends the new connection to all users
	@OnOpen
	public void start(Session session, @PathParam("username") String username, @PathParam("saleID") String saleID) {
		name = username;
		sess = session;
		sess.getUserProperties().put("saleID", saleID);
		connect.add(this);
		String msg = name + " has joined.";
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()
						&& saleID.equals(s.getUserProperties().get("saleID"))) {
					s.getBasicRemote().sendText(msg);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//removes the user from the connection and sends the disconnection to all users
	@OnClose
	public void end(Session session) {
		connect.remove(this);
		String msg = name + " has disconnected.";
		String saleID = (String) session.getUserProperties().get("saleID");
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()
						&& saleID.equals(s.getUserProperties().get("saleID"))) {
					s.getBasicRemote().sendText(msg);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//server gets a message and sends it to all connections
	@OnMessage
	public void incoming(final Session session, String msg) {
		
		String saleID = (String) session.getUserProperties().get("saleID");
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()
						&& saleID.equals(s.getUserProperties().get("saleID"))) {
					s.getBasicRemote().sendText(name + ": " + msg);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//checks and sends message to each connection
//	private static void transmit(String msg) {
//		for (ChatServerpoint client : connect) {
//			try {
//				synchronized (client) {
//					client.sess.getBasicRemote().sendText(msg);
//				}
//			} catch (IOException e) {
//				connect.remove(client);
//				try {
//					client.sess.close();
//				} catch (IOException e1) {
//				}
//				String message = client.name + " has been disconnected.";
//				transmit(message);
//			}
//		}
//	}
}