package p2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class P1Viewer extends Viewer implements PropertyChangeListener{




    public P1Viewer(MessageManager messageManager, int width, int height) {
        super(width, height);
        messageManager.addMessageListener(this);

    }
    public void setMessage(Message msg){
        super.setMessage(msg);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("message")){
            Message msg = (Message)evt.getNewValue();
            setMessage(msg);
        }

    }
}
