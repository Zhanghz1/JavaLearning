package Polymorphism;

public class Dog extends Animal{
    public void lookHome(){
        System.out.println("Dog lookHome");
    }

    public Dog() {
    }

    public Dog(int age, String color) {
        super(age, color);
    }
}
