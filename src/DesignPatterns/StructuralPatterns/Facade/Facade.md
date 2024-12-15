>🏨 **복잡한 서브시스템을 단순한 인터페이스로 감싸서 사용자가 쉽게 접근하게 하는 패턴**
---

# 설명

- 복잡한 시스템을 간단한 인터페이스를 통해 사용자에게 제공하는 패턴
- 사용자는 복잡한 내부 로직을 몰라도 필요한 기능을 파사드 객체를 통해 쉽게 사용할 수 있다.
- 시스템의 복잡성을 숨기고, 서브시스템 간의 의존성을 낮추기 위해 사용한다.

## 특징

- 복잡한 서브시스템을 간단한 인터페이스로 감싼다.
- 클라이언트는 서브시스템의 세부 구현을 알 필요 없이 파사드 인터페이스만 사용한다.
- 클라이언트와 서브시스템 간의 의존성을 줄인다.

## 실생활의 예제

- 호텔에 체크인 해야하는 상황.
- 청소, 요리, 객실관리에 전부 연락하여 체크인을 도와달라고 하지 않는다.
- 단순히 프론트 데스크를 통해 모든 서비스를 요청한다.
- 사용자는 프론트 데스크랑만 상호작용 하면 모든 서비스를 누릴 수 있다.

---

# 코드

```java
package StructuralPatterns;

public class Facade {
    public static void main(String[] args) {
        HotelFront hotelFront = new HotelFront();
        hotelFront.checkin(); // 사용자는 이것 만으로도 모든 서브 시스템 동작 가능
    }
}

class HotelFront { // 파사드
    String manager = "Darko";

    // 서브 시스템들을 파사드에서 모두 관리한다.
    private final CleaningSystem cleaningSystem;
    private final RoomManagementSystem roomManagementSystem;
    private final CookingSystem cookingSystem;

    public HotelFront(){
        System.out.println("호텔에 어서오세요.");
        cleaningSystem = new CleaningSystem();
        roomManagementSystem = new RoomManagementSystem();
        cookingSystem = new CookingSystem();
    }
    public void checkin(){
        System.out.println(manager+"가 체크인을 환영합니다.");
        cleaningSystem.cleaningRoom();;
        roomManagementSystem.roomManaging();
        cookingSystem.cookingFood();
    }
}

class CleaningSystem {
    String manager = "Luke";

    public void cleaningRoom() {
        System.out.println(manager+"가 방을 청소합니다.");
    }
}

class RoomManagementSystem {
    String manager = "Alex";

    public void roomManaging() {
        System.out.println(manager+"가 객실을 관리합니다.");
    }
}

class CookingSystem {
    String manager = "Xiukai";

    public void cookingFood() {
        System.out.println(manager+"가 요리를 시작합니다.");
    }
}

```

- `HotelFront` 클래스가 파사드 역할을 하여, 내부에 세 가지 서브시스템을 포함한다.
- 사용자는 `HotelFront` 의 `checkin()` 메서드를 호출 함으로써, 객실 청소, 객실 관리, 요리 준비를 차례대로 실행할 수 있다.
- 클라이언트는 `HotelFront` 만 알면 되며, 서브시스템을 직접 사용할 필요가 없다.

---

# 장/단점

## 장점

- 사용 편리성
    - 복잡한 서브시스템을 단순화하여 사용자가 쉽게 접근 가능
- 유지보수 용이
    - 클라이언트가 서브시스템의 세부 구현을 몰라도 되므로, 서브시스템을 변경해도 클라이언트에 영향을 미치지 않는다.
- 결합도 감소
    - 클라이언트는 파사드만 알고, 서브시스템은 알지 않기 때문에 시스템 간 결합도가 낮아진다.

## 단점

- 파사드가 너무 커질 수 있다.
    - 너무 많은 기능을 다루는 파사드를 만들면, 파사드 클래스가 지나치케 커지고 복잡해진다.
- 유연성 감소
    - 서브시스템의 특정 기능을 직접 사용할 수 없다.
    - 복잡한 작업시 유연성 떨어짐.

---

# 사용 사례

- UI 라이브러리
    - 복잡한 그래픽 시스템을 간단한 API로 감싸는 경우
- 홈 오토메이션 시스템
    - 여러 장비를 통합하여 하나의 인터페이스로 제어하는 시스템