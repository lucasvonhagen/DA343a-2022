package laboration3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Uppg4 {
    public void stats(String filename) throws IOException{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            int n;
            int women = 0;
            int sum = 0;
            int sumWomen = 0;
            n = ois.readInt();
            Person[] p = new Person[n];
            for (int i = 0; i<n ; i++){
                try {
                    p[i] = (Person) ois.readObject();
                    sum += p[i].getAge();
                    if (p[i].getSex() == 'K'){
                        sumWomen += p[i].getAge();
                        women++;
                    }
                }catch (ClassNotFoundException e ){
                    System.out.println(e);
                }
            }
            System.out.println("Genomsnitts åldern: " + (double)sum/n);
            System.out.println("Kvinnors genomsnitts ålder: " + (double)sumWomen/women);
        }
    }

    public static void main(String[] args) throws IOException {
        Uppg4 uppg4 = new Uppg4();
        uppg4.stats("files/persons.dat");
    }
}
