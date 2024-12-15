package StructuralPatterns.Bridge;

public class BridgeExample {
    public static void main(String[] args) {
        // 성격을 동적으로 설정
        Personailty shyPersonality = new Shy();
        Personailty bravePersonality = new Brave();

        // 각 동물에 다른 성격 설정
        Animal myAnimal = new Cat(shyPersonality);
        Animal neighborAnimal = new Dog(bravePersonality);

        // 각 동물과 성격 설명
        myAnimal.describe();
        neighborAnimal.describe();
    }
}

// 추상화 클래스
abstract class Animal{
    protected Personailty personailty;

    public Animal(Personailty personailty){
        this.personailty = personailty;
    }

    abstract void describe();
}

// 구체적인 동물 클래스
class Dog extends Animal {
    public Dog(Personailty personailty){
        super(personailty);
    }

    @Override
    void describe() {
        personailty.describe();
        System.out.println("강아지는 멍멍");
    }

}

class Cat extends Animal {

    public Cat(Personailty personailty) {
        super(personailty);
    }

    @Override
    void describe() {
        personailty.describe();
        System.out.println("고양이는 야옹");
    }
}

// 성격 인터페이스 (구현 계층)
interface Personailty{
    void describe();
}

// 구체적인 성격 클래스
class Shy implements Personailty{

    @Override
    public void describe() {
        System.out.println("소심한 성격을 가지고 있다.");
    }
}

class Brave implements Personailty{

    @Override
    public void describe() {
        System.out.println("용감한 성격을 가지고 있다.");
    }
}