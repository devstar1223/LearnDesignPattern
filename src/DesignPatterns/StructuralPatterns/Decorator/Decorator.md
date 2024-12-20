>🍨 **기능을 동적으로 추가하거나 확장할 수 있도록 설계된 구조적인 패턴**

---

# 설명

- 객체에 추가적인 기능을 동적으로 부여할 수 있다.
- 상속을 사용하지 않고도, 객체의 책임을 확장 가능
- 기본 객체인 `Component` 와 추가 기능을 감싸는 래퍼 객체 `Decorator` 를 사용해 기능을 점진적으로 확장한다.

## 특징

- 객체를 실행 중에 감싸며 여러 데코레이터를 체인처럼 연결 가능하여 동적 확장이 가능하다.
- 기존 클래스를 변경하지 않고 새로운 기능을 추가할 수 있어 OCP를 준수한다.

## 실생활의 예제

- 요아정은 아이스크림을 판매하고, 토핑도 추가할 수 있다.
- 토핑을 추가할떈 완성된 기본 아이스크림 위에 준비된 재료를 하나씩 추가한다.
- 추가할때 마다 비용이 발생한다.

---

# 코드

```java
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
```

- `Icecream`
    - **컴포넌트** 역할을 한다.
    - 인터페이스로 구현되어 설명 및 비용을 정의해놓았다.
    - 모든 종류의 아이스크림과 데코레이터가 동일한 타입으로 처리될 수 있게 한다.
- `BasicIcecream`
    - 아이스크림의 기본 형태 정의
    - 데코레이터가 기능을 확장할 대상이 된다.
- `IcecreamDecorator`
    - **데코레이터** 역할을 한다.
    - 추상클래스로 구현되었으며,`Icecream` 인터페이스를 구현한다.
    - `protected` 필드로 데코레이터가 감쌀 아이스크림 객체를 참조한다.
    - 공통적인 데코레이터 들이 해야할 일(로직) 을 정의하여 서브클래스 들이 구체적인 기능을 추가할 수 있게 한다.
- `ChocoSyrupDecorator` , `FruitDecorator`
    - **구체적인 데코레이터** 역할을 한다.
    - 추상 클래스에서 정의한 `getDescription()` 과 `getCost()` 같은 해야할 일을 한다.
        - 아이스크림 객체를 받아서, 그 객체에 값을 더하는 일을 한다.
    - 아이스 크림 객체에 동적으로 새로운 기능 추가

---

# 장/단점

## 장점

- 유연성
    - 실행중에 객체의 동작을 동적으로 변경 가능하다.
- 조합의 자유
    - 다양한 조합을 통해 새로운 행동을 생성할 수 있다.
- 상속 대체
    - 상속 계층 구조의 복잡성을 줄인다.

## 단점

- 디버깅 어려움
    - 데코레이터 체인을 추적하기 어려울 수 있다.

---

# 사용 사례

- Java I/O Streams: `BufferedReader`, `InputStreamReader`
- UI 라이브러리
    - 버튼, 창과 같은 기본 UI 요소에 스크롤바, 테두리와 같은 장식을 추가한다.