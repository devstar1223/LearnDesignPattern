package DesignPatterns.StructuralPatterns.Decorator;

public class DecoratorExample {
    public static void main(String[] args) {
        Icecream myIcecream = new BasicIcecream();
        System.out.println(myIcecream.getDescription());
        System.out.println(myIcecream.getCost());

        IcecreamDecorator myChocoIcecream = new ChocoSyrupDecorator(myIcecream);
        System.out.println(myChocoIcecream.getDescription());
        System.out.println(myChocoIcecream.getCost());

        IcecreamDecorator myChocoFruitIcecream = new FruitDecorator(myChocoIcecream);
        System.out.println(myChocoFruitIcecream.getDescription());
        System.out.println(myChocoFruitIcecream.getCost());
    }
}

interface Icecream{
    String getDescription();
    int getCost();
}

class BasicIcecream implements Icecream{

    @Override
    public String getDescription() {
        return "기본 아이스크림";
    }

    @Override
    public int getCost() {
        return 4500;
    }
}

abstract class IcecreamDecorator implements Icecream{
    protected Icecream decoratedIcecream;

    public IcecreamDecorator(Icecream icecream){
        this.decoratedIcecream = icecream;
    }

    @Override
    public String getDescription(){
        return decoratedIcecream.getDescription();
    }

    @Override
    public int getCost(){
        return decoratedIcecream.getCost();
    }
}

class ChocoSyrupDecorator extends IcecreamDecorator{
    public ChocoSyrupDecorator(Icecream icecream){
        super(icecream);
    }

    @Override
    public String getDescription(){
        return decoratedIcecream.getDescription() + " + 초코 시럽";
    }

    @Override
    public int getCost(){
        return decoratedIcecream.getCost() + 2000;
    }
}

class FruitDecorator extends IcecreamDecorator{
    public FruitDecorator(Icecream icecream){
        super(icecream);
    }

    @Override
    public String getDescription(){
        return decoratedIcecream.getDescription() + " + 과일";
    }

    @Override
    public int getCost(){
        return decoratedIcecream.getCost() + 3500;
    }
}