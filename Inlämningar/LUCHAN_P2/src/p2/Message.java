package p2;

import javax.swing.*;
import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private Icon icon;

    public Message(String message, Icon icon){
        this.text = message;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }
}
