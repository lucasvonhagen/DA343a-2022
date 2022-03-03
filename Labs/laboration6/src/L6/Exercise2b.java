package L6;

public class Exercise2b implements Runnable  {
    private String[] strings;
    private long pause;
    private int index=0;

    public Exercise2b(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2b(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    public void run() {
        while (index < strings.length){
            System.out.println(strings[index]);
            index++;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e ){}
        }
    }

    public static void main(String[] args) {
        //Uppgift 2b del 1
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Runnable
        Exercise2b ex2b = new Exercise2b(strings);
        Thread t1 = new Thread(ex2b);
        //t1.start();
        //Din kod för att exekvera Runnable-objektet


        //Uppgift 2a del 2
        String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Runnables
        //Exercise2b ex2b1 = new Exercise2b(strings1);
        //Exercise2b ex2b2 = new Exercise2b(strings2);

        //Runnables
        Exercise2b ex2b1 = new Exercise2b(strings1, 1500);
        Exercise2b ex2b2 = new Exercise2b(strings2, 750);
        Thread t2 = new Thread(ex2b1);
        Thread t3 = new Thread(ex2b2);
        t2.start();
        t3.start();

        //Din kod för att exekvera Runnable-objekten

    }
}

