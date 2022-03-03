package laboration2;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    
    public Person( String id, String firstName, String lastName ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public boolean equals(Object o) {
        boolean res = false;
        if(o instanceof Person) {
            Person p = (Person)o;
            res = p.id.equals(id);
        }
        return res;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String toString() {
        return id + " " + firstName + " " + lastName;
    }    
}
