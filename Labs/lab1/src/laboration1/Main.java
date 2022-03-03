package laboration1;

public class Main {
    public static void main(String[] args) {
        DString str1 = new DString("Laboration");
        str1.append(' ').append('1').append(new DString("\nUppgift3"));
        System.out.println(str1);
        str1.delete(2).delete(4,6).delete(8);
        System.out.println(str1);

    }
}
