package Polymorphism;

public class test {
    public static void main(String[] args) {
        Person wang = new Person("wang",30);
        Person li = new Person("laoli", 25);
        Animal dog = new Dog(2, "black");
        Cat cat = new Cat(3, "gray");
        wang.keepPet(dog, "gutou");
        li.keepPet(cat, "fish");
    }
}
