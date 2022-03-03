package p2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Lucas
 * Recieves Message objects from MessageServer and delivers them to listeners using Callback.
 */
public class MessageClient {

    private Buffer<Message> messageBuffer;
    private ArrayList<CallBackInterface> cblist;

    /**
     * Constructor for MessageClient. Creates a list for Callback listeners and creates a buffer for the recieved Message objects.
     * @param ip , The address for the server.
     * @param port , The port from where the runs.
     */
    public MessageClient(String ip, int port){
        new Connection(ip, port);
        cblist = new ArrayList<>();
        messageBuffer = new Buffer<>();
    }

    /**
     * Adds a Callback listener.
     * @param cbi , Class ,that implements the CallBackInterface, to become listener.
     */
    public void addCallbackListener(CallBackInterface cbi){
        cblist.add(cbi);
    }

    /**
     * Method used to send the Message object to all the listeners.
     * @param msg , Message object to be sent to listeners.
     */
    public void sendList(Message msg) {
        Iterator<CallBackInterface> iter = cblist.iterator();
        while(iter.hasNext()) {
            iter.next().getMessage(msg);
        }
    }

    /**
     * Internal class that receives Message objects from the connected server and the forwards the objects to the clients.
     */
    private class Connection extends Thread{
        private String ip;
        private int port;

        public Connection(String ip, int port){
            this.ip = ip;
            this.port = port;
            start();
        }
        public void run(){
            try(Socket socket = new Socket(ip, port)){
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                while (true){
                    try {
                        Message msg = (Message)ois.readObject();
                        messageBuffer.put(msg);
                        sendList(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
