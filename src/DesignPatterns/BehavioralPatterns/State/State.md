>**🛒 객체의 상태에 따라 동작을 다르게 처리하여, 상태 변화에 따른 코드 중복을 줄이고 유연한 상태 관리를 가능하게 하는 디자인패턴.**
---
# 설명

- 객체가 내부 상태에 따라 행동을 다르게 할 수 있도록 하는 패턴
- 객체의 상태는 변경될 수 있다.
- 변경된 객체의 상태에 따라 다른 행동을 상태 객체가 정의한다.

## 특징

- 상태에 따른 동작 변경
    - 객체의 내부에서 상태를 변경해 동작 변경
        - 외부에서도 가능하나, 객체의 상태를 캡슐하하여 변경하는 것이 바람직하다.
- 상태 객체 클래스
    - 각 상태를 나타내는 클래스를 만들고, 그 안에 상태별 행동을 정의한다.
- 상태 전환 관리
    - 상태 객체는 내부 상태가 변경되면 새로운 상태 객체로 교체될 수 있다.

## 실생활의 예제

- 쇼핑몰에서 물건을 주문할 수 있다.
- 물건을 **주문**하면, **주문 상태**를 확인할 수 있다.
- 각각의 주문 상태는, 현재 어떤 상황인지 알려준다. (주문이 완료되었습니다., 상품이 도착했습니다.)

---

# 코드

```java
package BehavioralPatterns;

public class State {
    public static void main(String[] args) {
        Order order = new Order();
        order.infoMessageOut();

        order.sendSign();
        order.infoMessageOut();

        order.deliverySign();
        order.infoMessageOut();

        order.deliveryCompleteSign();
        order.infoMessageOut();
    }
}

class Order{
    OrderState orderState;

    public Order(){
        setOrderState(new OrderComplete());
    }

    private void setOrderState(OrderState orderState){
        this.orderState = orderState;
    }

    public void infoMessageOut(){
        System.out.print("현재 상태 : ");
        orderState.infoMessage();
    }

    public void sendSign(){
        setOrderState(new Send());
    }

    public void deliverySign(){
        setOrderState(new Delivery());
    }

    public void deliveryCompleteSign(){
        setOrderState(new DeliveryComplete());
    }
}

interface OrderState{
    void infoMessage();
}

class OrderComplete implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("주문이 완료되었습니다.");
    }
}

class Send implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("판매자가 물건을 발송했습니다.");
    }
}

class Delivery implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("택배기사님이 물건을 배송중입니다.");
    }
}

class DeliveryComplete implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("배송이 완료되었습니다.");
    }
}
```

- 상태 객체 : `OrderState` 인터페이스를 통해 구현되었다.
    - 주문 완료/발송/배송중/배송완료
    - 각각의 상태에 `infoMessage()` 메서드를 구현하여 각 상태에 맞는 메세지를 출력한다.
- `Order` 객체
    - 상태 객체를 변경하는 메소드를 제공한다.
    - 이 메소드들이 호출될때마다 주문의 상태가 변경되고, 현재 상태가 해당 상태를 나타내는 객체로 교체된다.
- 상태 변화에 따른 행동 변경
    - `infoMessageOut()` 메소드가 상태에 맞는 메세지를 출력한다.
    - 상태의 `infoMessage()` 메서드를 호출해, 각각의 상태에 맞는 메세지를 출력하게 한다.

---

# 장/단점

## 장점

- 유연성 증가
    - 상태를 별도의 객체로 분리하여 상태 전환이 간단하고 유연해진다.
- 상태 관리 용이
    - 상태별 동작을 각 클래스에서 처리해 코드가 깔끔해진다.
    - 각 상태의 책임이 명확해진다.
- 조건문 감소
    - 상태에 따라 다르게 행동해야 하는 복잡한 조건문을 줄일 수 있다.

## 단점

- 클래스 수 증가
    - 상태마다 클래스를 정의하여 클래스가 많아질 수 있다.
- 상태 변경 로직 증가
    - 상태 객체를 전환하는 로직이 추가로 필요하다.
    - 잘못된 상태 전환을 조심해야 한다.

---

# 사용 사례

- 트랜잭션 상태
    - 금융 시스템에서 거래가 진행되는 동안의 상태 (요청됨 / 승인됨 / 완료됨) 에 따라 다른 동작을 취해야 할때 사용된다.
- 게임 상태 관리
    - 게임의 상태 (대기 / 플레이 / 일시 정지)에 따라 행동이 달라지는 경우, 게임의 상태 전환과 해야할 행동을 관리할 수 있다.