package DesignPatterns.CreationalPatterns.AbstractFactory;

public class AbstractFactoryExample {
    public static void main(String[] args) {
        Cafeteria leftCafeteria = new KoreaCafeteria();
        Food myFood = leftCafeteria.createFood("김치라면");
        System.out.println(myFood.getTaste());
        myFood = leftCafeteria.createFood("비빔밥");
        System.out.println(myFood.getTaste());

        Cafeteria rightCafeteria = new WesternCafeteria();
        Food yourFood = rightCafeteria.createFood("스파게티");
        System.out.println(yourFood.getTaste());
    }
}

// 음식 인터페이스
interface Food {
    String getTaste();
}

// 한국 음식 클래스들
class KimchiRamen implements Food {
    String taste = "맵다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

class Bibimbap implements Food {
    String taste = "산뜻하다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

// 서양 음식 클래스들
class Spaghetti implements Food {
    String taste = "짭짤하다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

class Chicken implements Food {
    String taste = "바삭하다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

// 물 클래스
class Water implements Food {
    String taste = "없는 메뉴를 시키니까 물이 나왔다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

// 조리 코너
interface Cafeteria {
    Food createFood(String orderFood);
}

// 한식 코너
class KoreaCafeteria implements Cafeteria {
    @Override
    public Food createFood(String orderFood) {
        if (orderFood.equals("비빔밥")) {
            return new Bibimbap();
        } else if (orderFood.equals("김치라면")) {
            return new KimchiRamen();
        } else {
            return new Water();
        }
    }
}

// 양식 코너
class WesternCafeteria implements Cafeteria {
    @Override
    public Food createFood(String orderFood) {
        if (orderFood.equals("스파게티")) {
            return new Spaghetti();
        } else if (orderFood.equals("치킨")) {
            return new Chicken();
        } else {
            return new Water();
        }
    }
}