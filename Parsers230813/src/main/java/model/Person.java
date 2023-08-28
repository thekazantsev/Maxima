package model;

public class Person {
    private String firstname;
    private String lastname;
    private int age;
    private boolean coder;
    private Document document;

    public Person() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCoder() {
        return coder;
    }

    public void setCoder(boolean coder) {
        this.coder = coder;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", coder=" + coder +
                ", document=" + document +
                '}';
    }
}
