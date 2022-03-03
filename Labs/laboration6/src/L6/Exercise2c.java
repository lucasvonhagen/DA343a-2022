package L6;

public class Exercise2c {
    private Activity thread;
    private String[] strings;
    private long pause;

    public Exercise2c(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2c(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    public void start() {
        if (thread == null){
            thread = new Activity();
            thread.start();
        }
    }

    private class Activity extends Thread {
        private int index;
        public void run() {
            while (index<strings.length){
                System.out.println(strings[index]);
                index++;
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e ){}
            }
        }
    }
    public class Exercise4a{
        private Activity thread;
        private String[] strings;
        private long pause;
        private int index = 0;
        public Exercise4a(String[] strings) {
            this(strings, 1000);
        }

        public Exercise4a(String[] strings, long pause) {
            this.strings = strings.clone();
            this.pause = pause;
        }

        public void start() {
            if( thread == null ) {
                thread = new Activity();
                thread.start();
            }
        }

        public void stop() {
            if(thread!=null) {
                thread.interrupt();
            }
        }
        private class Activity extends Thread {
            public void run() {
                while( index < strings.length && !Thread.interrupted() ) {
                    System.out.println(strings[index]);
                    index++;
                    try {
                        Thread.sleep(pause);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                thread=null;
            }
        }
    }


    public static void main(String[] args) {
        //Uppgift 2c del 1
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Exercise2c ex2c = new Exercise2c(strings);

        //ex2c.start();

        //Uppgift 2c del 2
        //String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        //String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Exercise2c ex2c1 = new Exercise2c(strings1);
        //Exercise2c ex2c2 = new Exercise2c(strings2);

        //Exercise2c ex2c1 = new Exercise2c(strings1, 1500);
        //Exercise2c ex2c2 = new Exercise2c(strings2, 750);

        //Din kod för att starta tråden


        //Uppgift
        Exercise4a ex2c = new Exercise4a(strings);
              try {
            Thread.sleep(2000);
                 } catch(InterruptedException e) {}

        ex2c.stop();

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}

        ex2c.start();
        ex2c.stop();

       try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}

        ex2c.start();

    }
}

