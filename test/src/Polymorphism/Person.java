package Polymorphism;

public class Person {
    private String name;
    private int age;
    public void keepPet(Animal animal, String sth){

        if(animal instanceof Dog dog){
            System.out.println("feed dog" + sth);
        }

    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
