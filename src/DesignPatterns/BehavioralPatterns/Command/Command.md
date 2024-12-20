>🛎️ **작업을 캡슐화하여 요청과 실행을 분리하는 패턴**

---

# 설명

- 작업(Command)을 객체로 캡슐화하여 호출자(Invoker)와 작업 실행자(Receiver)를 분리하는 디자인 패턴

---

### 구성 요소

- Command (명령 인터페이스)
    - 실행할 작업에 대한 메서드 정의
- ConcreteCommand (구체적인 명령)
    - Command 인터페이스를 구현한다.
    - 작업을 수행할 Receiver 를 참조한다.
- Receiver (수신자)
    - 명령이 수행할 실제 작업을 처리한다.
- Invoker (호출자)
    - Command 객체를 호출해 작업을 요청한다.

## 특징

- 캡슐화
    - 요청(명령)을 객체로 캡슐화해 호출자와 수신자 분리
- 실행 취소/재실행 지원
    - 명령을 추적하고 되돌리는 기능이 용이
- 조합 가능
    - 복함 명령을 만들어 여러 작업을 묶어서 처리 가능

## 실생활의 예제

- 손님은 레스토랑에서 주문 할 수 있다.
    - 내 음식을 만들어줄 지정 셰프를 지정할 수 있다.
- 주문을 받은 웨이터가 주방에 주문을 전달한다.
    - 웨이터는 요리법을 알 필요가 없다.
- 주방장은 주문을 확인하고 음식을 만든다.

---

# 코드

```java
package DesignPatterns.BehavioralPatterns.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandExample {
    public static void main(String[] args) {
        Chef minsu = new Chef();
        Waiter sujin = new Waiter();

        Order mySteakOrder = new StakeOrder(minsu);
        sujin.acceptOrder(mySteakOrder);

        Order mySideOrder = new SideOrder(minsu);
        sujin.acceptOrder(mySideOrder);

        Order myBeverageOrder = new BeverageOrder(minsu);
        sujin.acceptOrder(myBeverageOrder);

        sujin.orderFinish();

        sujin.orderUndo(mySideOrder);
    }
}

// Command - 주문 인터페이스
interface Order{
    void execute();
    void undo();
}

// ConcreteCommand - 상세 메뉴 주문
class StakeOrder implements Order{
    private Chef chef;

    public StakeOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookSteak();
    }

    @Override
    public void undo() {
        chef.cookSteakUndo();
    }
}

class SideOrder implements Order{
    private Chef chef;

    public SideOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookSide();
    }

    @Override
    public void undo() {
        chef.cookSideUndo();
    }
}

class BeverageOrder implements Order{
    private Chef chef;

    public BeverageOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeLemonade();
    }

    @Override
    public void undo() {
        chef.makeLemonadeUndo();
    }
}

// Receiver (수신자) - 셰프

class Chef {
    public void cookSteak(){
        System.out.println("셰프가 스테이크를 굽습니다.");
    }

    public void cookSteakUndo(){
        System.out.println("스테이크 조리를 취소합니다.");
    }

    public void cookSide(){
        System.out.println("셰프가 감자튀김을 튀깁니다.");
    }

    public void cookSideUndo(){
        System.out.println("감자튀김 조리를 취소합니다.");
    }

    public void makeLemonade(){
        System.out.println("셰프가 레몬에이드를 만듭니다.");
    }

    public void makeLemonadeUndo(){
        System.out.println("레몬에이드 만들기를 취소합니다.");
    }

}

// Invoker (호출자) - 웨이터

class Waiter{
    List<Order> orderList = new ArrayList<>();

    public void acceptOrder(Order newOrder){
        System.out.println("주문 받았습니다.");
        orderList.add(newOrder);
    }

    public void orderFinish(){
        System.out.println("총 " + orderList.size() + "개 주문하셨습니다.");
        for(Order order : orderList){
            order.execute();
        }
        System.out.println("모든 주문을 전달했습니다. 감사합니다.");
        orderList.clear();
    }

    public void orderUndo(Order order){
        System.out.println("요청하신 주문을 취소하겠습니다.");
        order.undo();
    }

}

```

- `Order`
    - **Command 인터페이스**
    - 각 주문은
        - 실행 되어야 하고 → `execute()`
        - 취소 될 수 있어야 한다 → `undo()`
- `StakeOrder` , `SideOrder` , `BeverageOrder`
    - **ConcreteCommand**
    - 각각의 명령 클래스가 `Chef` 의 조리/조리취소 메서드를 실행시킨다
- `Chef`
    - **Receiver**
    - 셰프 객체는 ConcreteCommand 에게 메세지를 받아, 조리/조리취소 작업을 한다.
- `Waiter`
    - **Invoker**
    - `acceptOrder()` 를 통해 주문을 저장한다.
    - `orderFinish()` 로 모든 주문을 실행한다.
    - `orderUndo()` 로 특정 주문을 취소한다.

---

# 장/단점

## 장점

- 확장성
    - 새로운 명령을 추가해도 기존 코드에 영향을 미치지 않는다.
- SRP 준수
    - 호출자와 수신자의 역할을 분리해 코드의 책임을 명확히 함

## 단점

- 간단한 작업에 불필요
    - 명령이 단순하다면 불필요하게 복잡한 설계가 된다.(복잡성 증가)

---

# 사용 사례

- GUI 애플리케이션
    - 버틐 클릭 이벤트를 캡슐화하여 전달한다.
- 트랜잭션 처리
    - 복합 명령을 트랜잭션처럼 실행한다.