package L6;

import javax.swing.*;
import java.awt.*;

public class Exercise3 extends Thread {
    private String[] messages;
    private JLabel lbltext;
    private long pause;
    private boolean OK;
    private int index = 0;
    public Exercise3(String[] messages, JLabel label, long pause) {
        this.messages = messages;
        this.lbltext = label;
        this.pause = pause;
        OK = (messages!=null) && (messages.length>0) &&
                (label!=null) && (pause>=0);

    }

    public void run(){
        while (OK && !interrupted()){
            SwingUtilities.invokeLater(new Write(messages[index]));
            index = (index+1) % messages.length;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e){}
        }
    }

    private class Write implements Runnable {
        //Den här klassen kan användas för att skapa objekt
        //som uppdaterar label-texten med hjälp av SwingUtilities.invokeLater
        private String message;
        public Write(String message){
            this.message = message;
        }
        @Override
        public void run() {
            lbltext.setText(message);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                JLabel lblText = new JLabel();
                String[] texter = {"Snart är det påsk",
                        "23 * 6735 = 154905",
                        "Den 28 oktober har Simone namnsdag"};
                lblText.setPreferredSize(new Dimension(400,40));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(lblText);
                frame.pack();
                frame.setVisible(true);
                
                Exercise3 ex3 = new Exercise3(texter, lblText, 3000);
                ex3.start();
            }
        });
    }

}
