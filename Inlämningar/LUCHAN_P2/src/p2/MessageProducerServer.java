package p2;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageProducerServer {
    private Thread server;
    private MessageProducerInput mpi;

    public MessageProducerServer(MessageProducerInput mpi, int port){
        this.mpi = mpi;
        server = new ServerHandler(port);
    }

    public void startServer(){
        server.start();
    }

    public class ServerHandler extends Thread{
        private int port;
        public ServerHandler(int port){
            this.port = port;
        }
        public void run(){
            try(ServerSocket serverSocket = new ServerSocket(port)){
                System.out.println("MPServer running.");
                while (true){
                    Socket socket = serverSocket.accept();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ArrayProducer arrayprod = (ArrayProducer)ois.readObject();
                    mpi.addMessageProducer(arrayprod);

                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
