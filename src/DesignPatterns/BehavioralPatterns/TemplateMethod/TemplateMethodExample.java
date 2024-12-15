package BehavioralPatterns.TemplateMethod;

public class TemplateMethodExample {
    public static void main(String[] args) {
        Pizza myPizza1 = new HotPizza();
        myPizza1.order();
        Pizza myPizza2 = new PineApplePizza();
        myPizza2.order();
    }
}

abstract class Pizza {
    public final void order() {
        prepareDough();
        addSauce();
        addToppings();
        bake();
        cut();
        if (orderCoke()) {
            addCoke();
        }
        serve();
    }

    void prepareDough() {
        System.out.println("도우를 준비합니다.");
    }

    void bake() {
        System.out.println("피자를 굽습니다.");
    }

    void cut() {
        System.out.println("피자를 자릅니다.");
    }

    void addCoke() {
        System.out.println("콜라를 추가합니다.");
    }

    void serve() {
        System.out.println("피자를 손님에게 줍니다.");
    }

    boolean orderCoke() {
        return false;
    }

    abstract void addSauce();

    abstract void addToppings();
}

class HotPizza extends Pizza {
    public void addSauce() {
        System.out.println("매운 소스를 넣습니다.");
    }

    public void addToppings() {
        System.out.println("매운 재료를 넣습니다.");
    }

    @Override
    public boolean orderCoke() {
        System.out.println("매워서 음료 필요함.");
        return true;
    }
}

class PineApplePizza extends Pizza {
    public void addSauce() {
        System.out.println("파인애플 소스를 넣습니다.");
    }

    public void addToppings() {
        System.out.println("파인애플을 넣습니다.");
    }
}