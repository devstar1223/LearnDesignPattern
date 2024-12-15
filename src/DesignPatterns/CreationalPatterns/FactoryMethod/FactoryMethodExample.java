package CreationalPatterns.FactoryMethod;

public class FactoryMethodExample {
    public static void main(String[] args) {
        CoffeeMachine homeCoffeeMachine = new AmericanoMachine();
        Coffee myCoffee = homeCoffeeMachine.createCoffee();
        myCoffee.taste();

        CoffeeMachine companyCoffeeMachine = new LatteMachine();
        Coffee workingCoffee = companyCoffeeMachine.createCoffee();
        workingCoffee.taste();
    }
}

//커피 인터페이스
interface Coffee {
    void taste();
}

class Americano implements Coffee{

    @Override
    public void taste() {
        System.out.println("씁쓸한 맛이 난다.");
    }
}

class Latte implements Coffee{

    @Override
    public void taste() {
        System.out.println("고소한 맛이 좋다.");
    }
}

abstract class CoffeeMachine{
    abstract Coffee createCoffee();
}

class AmericanoMachine extends CoffeeMachine{

    @Override
    Coffee createCoffee() {
        return new Americano();
    }
}

class LatteMachine extends CoffeeMachine{

    @Override
    Coffee createCoffee() {
        return new Latte();
    }
}