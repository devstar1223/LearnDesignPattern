>🛫 **여러 객체 간의 복잡한 상호작용을 중재하는 객체를 도입하여, 객체 간의 의존성을 낮추는 패턴**
---

# 설명

- 객체들 간의 복잡한 상호작용을 중앙 집중식으로 처리한다.
- 여러 객체가 서로 직접 통신하지 않고, 중재자 객체를 통해 상호작용하게 만든다.

## 특징

- 객체들은 중재자를 통해서만 상호작용
- 중재자가 모든 통신을 관리하여, 결합도가 낮다.

## 실생활의 예제

- 비행기들은 비어있는 비행경로를 통해 이륙한다.
    - 아주 많은 비행기가 있기때문에, 비행경로가 잘못되면 충돌할 수 있다.
- 비행기들은 너무 많아서, 비행기들 끼리 서로 비행경로를 조정하려면 많은 시간이 걸리고 복잡하다.
- 따라서, 비행기들은 관제탑이랑만 통신하며, 각자의 비행경로를 찾는다.

---

# 코드

```java
package DesignPatterns.BehavioralPatterns.Mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorExample {
    public static void main(String[] args) {
        ControlTower incheonControlTower = new IncheonControlTower();
        Airplane jeju123 = new JejuairAirplane(incheonControlTower, "제주항공 비행기");
        Airplane asiana456 = new AsianaAirlinesAirplane(incheonControlTower, "아시아나 비행기");

        jeju123.requestTakeoff("1번 비행로");
        asiana456.requestTakeoff("1번 비행로");
        asiana456.requestTakeoff("2번 비행로");
    }
}

interface ControlTower{
    boolean canTakeoff(String flightPath);
}

class IncheonControlTower implements ControlTower{

    List<String> unavailableFlightPathList = new ArrayList<>();

    @Override
    public boolean canTakeoff(String flightPath) {
        if(unavailableFlightPathList.contains(flightPath)){
            System.out.println("관제탑 : 사용중인 비행경로. 다른 곳을 요청하십시오.");
            return false;
        }
        System.out.println("관제탑 : 이륙 허가.");
        unavailableFlightPathList.add(flightPath);
        return true;
    }

}

abstract class Airplane{
    protected ControlTower controlTower;
    private String name;

    public Airplane(ControlTower controlTower,String name){
        this.controlTower = controlTower;
        this.name = name;
    }

    public void requestTakeoff(String flightPath){
        System.out.println(name+" : 이륙을 요청합니다. " + flightPath);
        if(controlTower.canTakeoff(flightPath)){
            System.out.println(name+" : 허가 완료. 이륙합니다.");
        }
        else{
            System.out.println(name+" : 확인했습니다.");
        }
    }
}

class JejuairAirplane extends Airplane{

    public JejuairAirplane(ControlTower controlTower,String name) {
        super(controlTower,name);
    }
}

class AsianaAirlinesAirplane extends Airplane{

    public AsianaAirlinesAirplane(ControlTower controlTower,String name) {
        super(controlTower,name);
    }
}
```

- `ControlTower` 인터페이스
    - 관제탑들은 비행기들이 이륙할 수 있는지 확인해야 한다.
        - 구현체들은 `canTakeoff` 메서드를 구현해야함
- `IncheonControlTower` 클래스
    - **중재자 역할**
    - `unavailableFlightPathList` 로 현재 사용중인 비행경로를 저장하여, 비행기들의 중복 이륙을 방지한다.
- `Airplane` 클래스
    - 비행기들은 관제탑을 통해 이륙 허가를 받아야 한다.
    - 상속받는 클래스들은 `requestTakeoff` 로 허가 요청을 받아야함.
- `JejuAirplane`, `AsianaAirlinesAirplane` 클래스
    - 이륙 요청시 관제탑이랑만 상호작용 한다.

---

# 장/단점

## 장점

- 결합도 감소
    - 객체들 간의 직접적인 상호작용을 줄여 중재자를 통한 상호작용 관리
    - 시스템의 유연성을 높이고 독립성을 강화한다.
- 변경 용이
    - 객체간의 상호작용을 중재자가 관리하므로, 하나의 객체를 변경해도 다른 객체에 영향이 가지 않음
- 독립성 증가
    - 각 객체가 서로를 알 필요가 없어, 서로 독립적인 동작 가능
    - 유지보수를 높이고 의존성을 줄여줌

## 단점

- 중재자의 과중한 책임
    - 하나의 객체에 너무 많은 책임이 부여될 수 있다.
    - 이로 인해 중재자 객체가 비대해지고 복잡해질 위험이 있다.

---

# 사용 사례

- GUI 시스템
    - 여러 버튼과 텍스트 박스를 중앙에서 상호작용 관리
- 채팅 시스템
    - 여러 사용자가 주고 받는 메시지를 중재자가 관리.
- 그 외 다양한 컴포넌트들이 상호작용하는 시스템에서 중앙 집중식 관리가 필요한 경우