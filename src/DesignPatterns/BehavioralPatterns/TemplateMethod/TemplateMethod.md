> **🍕 알고리즘의 구조를 정의하고, 하위 크래스에서 세부 단계를 구현하도록 유도하는 패턴.**
---

# 설명

- 특정 알고리즘의 골격을 슈퍼클래스에 정의한다.
- 그 알고리즘의 세부 단계를 하위 클래스에서 구현하도록 허용한다.
- 중복을 줄이고 공통 동작을 한곳에 통합하여 코드의 재사용성을 높인다.

## 특징

- 알고리즘의 구조를 정의하는 추상 클래스와 세부 사항을 구현하는 구체 클래스가 필요 (상속)
- 일부 메서드는 공통으로 구현하고, 일부는 하위 클래스가 오버라이드 할 수 있도록 추상화.
- 동작의 일관성과 유연성 제공

## 실생활의 예제

- 피자를 주문받아 만들어서 서빙하는 과정은 공통적으로 비슷하나 부분적으로 다르다.
- 도우를 만들고, 굽고, 자르고, 서빙하는 과정은 공통적으로 똑같지만
- 토핑 종류 / 소스 종류 / 콜라 추가 여부 정도만 다르다.

---

# 코드

```java
package BehavioralPatterns;

public class TemplateMethod {
    public static void main(String[] args) {
        Pizza myPizza1 = new HotPizza();
        myPizza1.order();
        Pizza myPizza2 = new PineApplePizza();
        myPizza2.order();
    }
}

abstract class Pizza{
    public final void order() {
        prepareDough();
        addSauce();
        addToppings();
        bake();
        cut();
        if (orderCoke()){
            addCoke();
        }
        serve();
    }

    void prepareDough(){
        System.out.println("도우를 준비합니다.");
    }
    void bake(){
        System.out.println("피자를 굽습니다.");
    }
    void cut(){
        System.out.println("피자를 자릅니다.");
    }
    void addCoke(){
        System.out.println("콜라를 추가합니다.");
    }
    void serve(){
        System.out.println("피자를 손님에게 줍니다.");
    }

    boolean orderCoke(){
        return false;
    }

    abstract void addSauce();
    abstract void addToppings();
}

class HotPizza extends Pizza{
    public void addSauce(){
        System.out.println("매운 소스를 넣습니다.");
    }
    public void addToppings(){
        System.out.println("매운 재료를 넣습니다.");
    }

    @Override
    public boolean orderCoke(){
        System.out.println("매워서 음료 필요함.");
        return true;
    }
}

class PineApplePizza extends Pizza{
    public void addSauce(){
        System.out.println("파인애플 소스를 넣습니다.");
    }
    public void addToppings(){
        System.out.println("파인애플을 넣습니다.");
    }
}
```

- 템플릿 메서드 `order()`
    - 피자를 만드는 기본 흐름인 `prepareDough()`, `addSauce` 등등.. `Pizza` 클래스에서 잘 정의가 되어있다.
    - 피자마다 다르게 세부구현해야 하는 부분은 추상 메서드 `addSauce()` 와 `addToppings()` 로 잘 처리되었다.
    - 기본적인 흐름을 수정하지 않고 세부 구현만 서브클래스에서 처리하는 구조 → **알고리즘의 골격을 상속받아 사용**
- 후크 `orderCoke()`
    - 음료가 필요한지 여부를 결정하는 후크로 구현
    - 기본값이 `false` 이고, 필요한 피자인 경우 하위 클래스에서 오버라이드 하여 `true` 를 반환해 `addCokes()` 가 실행될 수 있게 한다.

---

# 장/단점

## 장점

- 알고리즘의 공통 코드를 한곳에 정의해 **중복 제거**
- 알고리즘 구조를 변경하지 않고 세부 동작을 확장하는 **유연성 제공**
- 코드의 **재사용성** 증가

## 단점

- 하위 클래스가 많아질수록 코드 복잡도 증가.
- 템플릿 메서드가 너무 많은 세부 단계를 정의하면 이해하기 어려워진다.

---

# 사용 사례

- 알고리즘 구조는 변경되지 않지만 세부 단계는 자주 변경될떄.
- 공통 알고리즘을 기반으로 다양한 변형 동작을 구현해야 할떄.