package p2;

public class P2Viewer extends Viewer{
    private MessageClient msgClient;

    public P2Viewer(MessageClient msgClient, int width, int height){
        super(width,height);
        this.msgClient = msgClient;
        msgClient.addCallbackListener(new CallBackController());
    }
    private class CallBackController implements CallBackInterface{
        @Override
        public void getMessage(Message message) {
            setMessage(message);
        }
    }
}
