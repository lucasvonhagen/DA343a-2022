package p2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Lucas
 * This class recives Message objects from the MessageManager and sends them to MessageClient.
 */
public class MessageServer implements PropertyChangeListener {

    private MessageManager manager;
    private int port;
    private ArrayList<ClientHandler> clients;
    private Thread server;

    /**
     * Constructor for MessageServer. Initiates the clients list, adds the server as a PropertyChangeListener and starts a Thread called server.
     * @param manager , Messagemanager object from where the server will recive Message-objects from.
     * @param port , The port from where the server will run.
     */
    public MessageServer(MessageManager manager, int port){
        this.manager = manager;
        this.port = port;
        clients = new ArrayList<>();
        manager.addMessageListener(this);
        server = new Connection(port);
    }

    /**
     * Puts the Message object last in the Buffer for all clients currently connected.
     * @param msg
     */
    public void sendMessage(Message msg){
        Iterator<ClientHandler> iter = clients.iterator();
        while (iter.hasNext()){
            iter.next().put(msg);
        }
    }

    /**
     * Listens to MessageManager for the event change and grabs the new message and puts it in the sendMessage method.
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("message")){
            Message msg = (Message)evt.getNewValue();
            sendMessage(msg);

        }
    }

    /**
     * Internal class that starts a new thread for all new connections.
     */
    private class Connection extends Thread{
        private int port;
        public Connection(int port){
            this.port = port;
            start();
        }
        public void run(){
            try (ServerSocket serverSocket = new ServerSocket(port)){
                System.out.println("MessageServer running.");
                while (true){
                    try {
                        Socket socket = serverSocket.accept();
                        ClientHandler client = new ClientHandler(socket);
                        clients.add(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Internal class that creates the connection and grabs the Message objects and delivers it to the connected clients.
     */
    private class ClientHandler extends Thread{
        private Socket socket;
        private Buffer<Message> messageBuffer;

        public ClientHandler(Socket socket){
            this.socket = socket;
            messageBuffer = new Buffer<>();
            start();
        }
        public void put(Message msg){
            messageBuffer.put(msg);
        }
        public void run(){

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                    while (true) {
                        try {
                            Message msg = messageBuffer.get();
                            oos.writeObject(msg);
                            oos.flush();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
