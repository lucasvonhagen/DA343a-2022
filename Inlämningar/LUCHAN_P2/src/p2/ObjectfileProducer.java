package p2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectfileProducer implements MessageProducer {
    private int delay;
    private int times;
    private int size;
    private String filename;
    private int currentIndex = -1;
    private Message message;

    public ObjectfileProducer(String filename){
        this.filename = filename;
    }

    @Override
    public int delay() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ois.readInt();
            delay = ois.readInt();

        } catch (IOException e) {
        }
        return delay;
    }

    @Override
    public int times() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            times = ois.readInt();

        } catch (IOException e) {
        }
        return times;
    }

    @Override
    public int size() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            ois.readInt();
            ois.readInt();
            size = ois.readInt();

        } catch (IOException e) {
        }
        return size;
    }

    @Override
    public Message nextMessage() {
        Message[] messages = new Message[size];
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(filename))) {
           os.readInt();
           os.readInt();
           os.readInt();
           for (int i = 0; i<size; i++){
               messages[i] = (Message)os.readObject();
           }
        } catch (IOException e) {} catch (ClassNotFoundException e) {}
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}
