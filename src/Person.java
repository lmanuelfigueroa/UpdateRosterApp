public class Person{

private String firstName;
private String lastName;
private String email;

public Person(String firstName, String lastName, String email){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
}

public void changeName(String newFirstName, String newLastName){
    this.firstName = newFirstName;
    this.lastName = newLastName;
}

public void printInfo(){
    System.out.println("Name: "+ this.firstName + " " + this.lastName + " Email: " + this.email);
}




}