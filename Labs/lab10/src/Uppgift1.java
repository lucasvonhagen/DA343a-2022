
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Uppgift1 {

    public Uppgift1() {
        JFrame frame = new JFrame("IP-Checker");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new UI());
        frame.pack();
        frame.setVisible(true);
    }

    private class UI extends JPanel implements ActionListener {
        private JLabel lblLocalIp = new JLabel(" ");
        private JTextField tfHostname = new JTextField();
        private JLabel lblIp = new JLabel(" ");

        public UI() {
            JPanel pnlHostname = new JPanel(new BorderLayout());
            pnlHostname.add(new JLabel("Host name:"),BorderLayout.WEST);
            pnlHostname.add(tfHostname);

            try{
                InetAddress inet = InetAddress.getLocalHost();
                lblLocalIp.setText("IP: " + inet.getHostAddress() + ", Namn: " + inet.getHostName());
            } catch (UnknownHostException e) {
                lblLocalIp.setText("IP: -- , Namn: --");
            }


            setLayout(new GridLayout(3,1));
            setPreferredSize(new Dimension(300,100));
            add(lblLocalIp);
            add(pnlHostname);
            add(lblIp);

            tfHostname.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            //TODO: Användaren kan mata in en webbadress (t.ex. mau.se eller google.se) och sedan ska webbadressens ip-adress visas.
            // Webbadressen står i JTextField tfHostname ( tfHostname.getText() )
            // Använd klassen InetAddress i java.net (https://docs.oracle.com/javase/7/docs/api/java/net/InetAddress.html)
            // Tänk på att anropen till metoderna som behövs kan kasta en UnknownHostException!
            try{
                InetAddress adress = InetAddress.getByName(tfHostname.getText());
                lblIp.setText("Host IP: " + adress.getHostAddress());
            }catch (UnknownHostException e1){
                lblIp.setText("Host IP: Unknown");
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Uppgift1();
            }
        });
    }
}