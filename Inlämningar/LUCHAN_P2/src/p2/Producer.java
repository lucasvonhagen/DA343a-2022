package p2;


/**
 * @author Lucas
 *Denna klassen används för att ta MessageProducer implementeringar och placera
 *Message instanser i BufferMessage.
 */
public class Producer extends Thread {
    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;

    /**
     *Konstruktor för klassen Producer.
     * @param prodBuffer, ett BufferMessageProducer objekt.
     * @param messageBuffer, ett BufferMessage objekt.
     */
    public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> messageBuffer){
        this.producerBuffer = prodBuffer;
        this.messageBuffer = messageBuffer;
    }

    /**
     * start:
     * Används för att starta run() funktionen som sätter igång
     * proceduren med att mata message objekt till Buffern.
     */
    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     *
     * Hämtar en MessageProducer ur BufferMessageProducer, använder sig sedan av
     * times() och size() för att skapa en for loop för att sedan ta ett message objekt med hjälp av
     * nextMessage() funktionen och lägga till i BufferMessage med Buffer metoden put(T obj);
     * Sedan används delay() i Thread.sleep() för att skapa en paus mellan loopen, vilket ger en delay
     * mellan uppdateringarna på skärmarna.
     */
    public void run(){
        MessageProducer mp;
        while (!Thread.interrupted()){
            try {
                mp = producerBuffer.get();
                Message message;
                for (int times = 0; times<mp.times(); times++){
                    for(int index = 0; index<mp.size(); index++) {
                        message = mp.nextMessage();
                        messageBuffer.put(message);
                        Thread.sleep(mp.delay());
                    }
                }
            } catch (InterruptedException e) {}
        }
    }
}
