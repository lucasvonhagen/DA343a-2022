package p1;

import javax.swing.*;
import java.io.*;


/**
 * @author Lucas
 * Denna klassen används för att läsa av den datan som anges i filen och implementerar denna data på olika sätt
 */
public class TextfileProducer implements MessageProducer{

    private String filename;
    private int delay;
    private int times;
    private int size;
    private int currentIndex = -1;

    public TextfileProducer(String filename){
        this.filename = filename;
    }

    /**
     * Ignorerar den första raden och tilldelar delay till integern som finns på den andra raden i textfilen
     * @return delay, en integer som avlästs från filen
     */
    @Override
    public int delay() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")) ) {
            br.readLine();
            String d = br.readLine();
            this.delay = Integer.parseInt(d);
        } catch (IOException e) {
        }
        return delay;
    }

    /**
     * Läser av första raden i textfilen som en String, som sedan görs till en Integer som times tilldelas
     * @return times,en integer som avlästs från filen
     */
    @Override
    public int times() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")) ) {
            String t = br.readLine();
            this.times = Integer.parseInt(t);
        } catch (IOException e) {
        }
        return times;
    }

    /**
     * Ignorerar de första 2 raderna och tilldelar size till integern som finns på den tredje raden i textfilen.
     * @return size, en integer som avlästs från filen.
     */
    @Override
    public int size() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")) ) {
            br.readLine();
            br.readLine();
            String s = br.readLine();
            this.size = Integer.parseInt(s);
        } catch (IOException e) {}
        return size;
    }

    /**
     * Skapar ett BufferedReader objekt br
     * br.readLine() kallas 3 gånger så att de första 3 raderna i texfilen ignoreras
     * En array av Message med storlek size() skapas, då det representerar antalet par från textfilen
     * För antalet par så skapas ett Message objekt som sparas i messages arrayen
     * @return messages[currentIndex], vilket är ett message objekt
     * @return null, ifall size() == 0
     */
    @Override
    public Message nextMessage() {
        Message[] messages = new Message[size()];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            br.readLine();
            br.readLine();
            br.readLine();
            for (int i = 0; i < size(); i++) {
                String text = br.readLine();
                Icon icon = new ImageIcon(br.readLine());
                messages[i] = new Message(text, icon);
            }

        } catch (IOException e) {
        }
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
    }
