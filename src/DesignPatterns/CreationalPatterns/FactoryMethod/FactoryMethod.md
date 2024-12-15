>☕ **객체 생성의 책임을 서브클래스에 위임하는 디자인 패턴.**
---

# 설명

- 객체를 생성하는 인터페이스를 정의하나, 실제 객체 생성은 서브클래스가 한다.
- 객체 생성 과정의 구체적인 클래스를 클라이언트 코드에서 분리 가능하다.

## 특징

- 클라이언트 코드에서 객체 생성에 대한 구체적인 클래스에 의존하지 않음
- 객체 생성을 담당하는 클래스를 변경하지 않고, 서브클래스를 통해 객체 생성 방식을 변경 가능

## 실생활의 예제

- 아메리카노와 라떼를 모두 만들 수 있는 **커피 머신**이 있다.
- 사용자 입장에서는 **커피 머신**을 사용하여 아메리카노 또는 라떼 를 선택할 수 있다.
- 실제로는 두 커피를 만드는 방식이 달라서, 커피머신의 **서브 시스템**인 **아메리카노 메이커** 와 **라떼 메이커**가 커피를 만들어서 제공한다.

---

# 코드

```java
package CreationalPatterns;

public class FactoryMethod {
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
```

- 고객은 “어떤 커피를 주문할지” 는 결정하지만, “커피를 어떻게 만들지”는 각각의 머신(서브클래스)가 결정한다.
- 고객은 커피머신의 세부 구현을 몰라도, 커피 머신을 통해 내가 원하는 커피를 만들 수 있다.
- 커피를 만드는 과정을 추상화해 클라이언트와 제조 세부사항을 분리한다.

---

# 장/단점

## 장점

- 높은 응집도
    - 객체 생성 코드와 실제 사용코드를 분리하여 응집도를 높인다.
- 확장성
    - 객체 생성 방식을 쉽게 확장하여, 새로운 종류의 객체를 추가하는데 용이하다.

## 단점

- 복잡성 증가
    - 서브클래스가 객체 생성 방식을 구체적으로 결정해야 하므로, 클래스의 수가 늘어나고 복잡할 수 있다.
- 유연성 제한
    - 객체 생성 방식 변경시. 서브클래스를 수정해야 하므로 코드 변경이 필요할 수 있다.

---

# 사용 사례

- UI 프레임워크에서 버튼, 체크박스 등의 UI 요소를 만드는데 사용됨.
    - 플랫폼별로 다른 UI 요소를 생성하는 방식