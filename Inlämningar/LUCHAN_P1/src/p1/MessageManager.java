package p1;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MessageManager implements Runnable {
    private Buffer<Message> messageBuffer;
    private Thread thread;
    private PropertyChangeSupport change = new PropertyChangeSupport(this);


    public void addNessageListener(PropertyChangeListener listener){
        change.addPropertyChangeListener(listener);
    }

    public MessageManager(Buffer<Message> messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Message msg = messageBuffer.get();
                System.out.println(msg.getText());
                change.firePropertyChange("message", false, msg);

            } catch (InterruptedException e) {}
        }
    }
}

