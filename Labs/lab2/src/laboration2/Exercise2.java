package laboration2;
import java.util.*;
import java.io.*;

public class Exercise2 {
    private ArrayList<Person> list = new ArrayList<Person>();

    public Exercise2(String filename) {
    	list = Exercise1.readPersons(filename);
    }

    public String toString() {
        return "Lagrade Person-objekt: " + list.toString();
    }

    public void printPersons() {
        // Skriv kod
        for (Person p : list){
            System.out.println("Personnummerr: " + p.getId() + ", Namn: " + p.getFirstName() + " "+ p.getLastName());
        }
    }

    public int position( String id ) {
        Person p;
        for (int i = 0; i<list.size(); i++){
            p = list.get(i);
            if (id.equals(p.getId())){
                return i;
            }
        }
        return -1;
    }

    public void printName( String id ) {
        Person p;
        boolean match = false;
        for (int i = 0; i<list.size(); i++){
            p = list.get(i);
            if (id.equals(p.getId())){
                System.out.println(p.getFirstName() + " " + p.getLastName());
                match = true;
            }
        }
        if (!match){
            System.out.println(id + " okänd.");
        }
    }

    public boolean existsFirstName( String firstName ) {
        Person p;
        boolean match = false;
        for (int i = 0; i<list.size(); i++){
            p = list.get(i);
            if (firstName.equals(p.getFirstName())){
                System.out.println(p.getFirstName());
                match = true;
            }
        }
        if (match){
            return true;
        }
        else
            return false;
    }

    public boolean changeLastName( String id, String lastName ) {
        /*Person p;
        boolean match = false;
        for (int i = 0; i<list.size(); i++){
            p = list.get(i);
            if (id.equals(p.getId())){
                System.out.println("Efternamn: " + p.getLastName());
                p.setLastName(lastName);
                System.out.println("Ändrat till: " + lastName);
                match = true;
            }
        }
        if (match){
            return true;
        }
        else
            return false;*/
        int pos = position( id );
        if( pos >= 0 ) {
            Person p = list.get( pos );
            p.setLastName( lastName );
        }
        return ( pos >= 0 );
    }


    // Uppgift 3
    public int position2(String id) {
    	Person p = new Person(id,"","");
    	return list.indexOf(p);
    }


    public static void main( String[] args ) {
        Exercise2 ex2 = new Exercise2( "C:\\Users\\lucas\\OneDrive\\Skrivbord\\Kurser\\DA343a-2022\\Labs\\lab2\\src\\laboration2\\files\\personer.txt" );
        System.out.println( ex2.toString() );
        ex2.printPersons();
        System.out.println( ex2.position( "840102-4567" ) );
        System.out.println( ex2.position( "111111-2222" ) );
        ex2.printName( "840102-4567" );
        ex2.printName( "111111-2222" );
        System.out.println( ex2.existsFirstName( "Edit" ) );
        System.out.println( ex2.existsFirstName( "Anna" ) );
        ex2.changeLastName( "660503-8901", "Trädrot" );
        System.out.println( ex2.toString() );

        // Uppgift 3a
        //System.out.println( ex2.position2("840102-4567") );

    }
}
