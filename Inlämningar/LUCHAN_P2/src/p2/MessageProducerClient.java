package p2;


import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageProducerClient {

    private String ip;
    private int port;

    public MessageProducerClient(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void send(MessageProducer producer) {
        int times = producer.times();
        int delay = producer.delay();
        int size = producer.size();

        Message[] messages = new Message[size];
        for (int i = 0; i<size; i++){
            messages[i] = producer.nextMessage();
        }

        ArrayProducer arrayprod = new ArrayProducer(messages, times, delay);

        try {
            Socket socket = new Socket(ip, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(arrayprod);
            oos.flush();
            oos.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
