package laboration3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Uppg1 {
    public void addera(String filename) throws IOException{
        try(DataInputStream data = new DataInputStream(new FileInputStream(filename))){
            int sum = 0;
            int n = data.readInt();
            System.out.println("Antal tal i filen: " + n);
            for (int i = 0; i<n; i++){
                sum += data.readInt();
            }
            System.out.println("Summan av alla tal: " + sum);
        }
    }

    public static void main(String[] args) throws IOException {
        Uppg1 upg = new Uppg1();
        upg.addera("files/heltal.dat");
    }
}
