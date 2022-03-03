package laboration3;

import java.io.*;

public class Uppg2 {
    public void medlemmar(String filename) throws IOException{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))){
            int count = 0;
            int women = 0;
            String name = br.readLine();
            while (name != null){
                count++;
                if (name.endsWith("K")){
                    women++;
                }
                name = br.readLine();
                if (name != null){
                    System.out.print(name + ", ");
                }
            }
            System.out.print("\nAntal medlemmar: " + count + ", därav " + women + " är kvinnor.\n");
        }

    }

    public static void main(String[] args) throws IOException {
        Uppg2 uppg2 = new Uppg2();
        uppg2.medlemmar("files/medlemmar.txt");
    }
}
