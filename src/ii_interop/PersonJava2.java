package ii_interop;

public class PersonJava2 {

    private String firstName;
    private String lastName;

    public PersonJava2(String firstName,
                       String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}