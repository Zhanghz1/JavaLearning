package Polymorphism;

public class Cat extends Animal{
    public void catchMouse(){
        System.out.println("cat catch mouse");
    }

    public Cat() {
    }

    public Cat(int age, String color) {
        super(age, color);
    }
}
