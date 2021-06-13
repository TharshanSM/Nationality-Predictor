package exammtit.sam.lk;

public class Person {
    private String Name;
    private int Age;
    private String Gender;

    public Person(String name, int age, String gender) {
        Name = name;
        Age = age;
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
