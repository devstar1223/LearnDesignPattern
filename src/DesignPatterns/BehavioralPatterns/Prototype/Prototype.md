>🤖 **객체를 복사하여 새로운 객체를 생성하는 패턴**

---

# 설명

- 기존 객체를 복제하여 새 객체를 생성하는 방식
- 객체 생성의 비용이 높거나, 복잡한 초기화 가정을 피하려는 경우 사용한다.
- 클래스 기반이 아닌, 이미 생성된 인스턴스를 기반으로 복제를 수행한다

## 특징

- 객체 복제
    - `clone` 메서드를 통해 객체를 복사
- 복제와 초기화 분리
    - 복잡한 초기화를 수행하지 않고도 새 객체를 얻을 수 있음.

## 실생활의 예제

- 기계에서 생산하는 힘 / 속도의 값을 설정해야 하는 로봇이 있다.
- 처음 로봇을 만들떄, 힘과 속도의 값을 설정하여 로봇을 만든다.
- 그 다음부터 그 로봇을 또 만들고 싶다면, 값을 설정할 필요 없이, **최근 제작 설정으로 복제** 버튼을 누르면, 그 로봇과 똑같은 설정을 가진 로봇이 복제된다.

---

# 코드

```java
package DesignPatterns.BehavioralPatterns.Prototype;

public class PrototypeExample {
    public static void main(String[] args) {
        Robot firstRobot = new Robot(100,6);
        firstRobot.setName("태초의 로봇");
        firstRobot.status();

        Robot secondRobot = (Robot) firstRobot.clone();
        secondRobot.setName("세컨드");
        secondRobot.status();

        Robot thirdRobot = (Robot) firstRobot.clone();
        thirdRobot.setName("서드");
        thirdRobot.status();

    }
}

interface RobotPrototype{
    RobotPrototype clone();
}

class Robot implements RobotPrototype{

    private int speed;
    private int strength;
    private String name;

    @Override
    public RobotPrototype clone() {
        return new Robot(this.speed, this.strength);
    }

    public Robot(int speed,int strength){
        this.speed = speed;
        this.strength = strength;
    }

    public void setName(String name){
        this.name = name;
    }

    public void status(){
        System.out.println("--- 로봇 정보 ---");
        System.out.println("이름 : "+name);
        System.out.println("속도 : "+speed);
        System.out.println("근력 : "+strength+"\n");
    }

}

```

- `RobotPrototype` 인터페이스
    - 로봇의 프로토타입은 복제 되야 한다는 복제 메서드 `clone` 을 정의하고 있다.
- `Robot` 클래스
    - `RobotPrototype` 의 인터페이스를 구현해, 복제기능을 구현한다.
    - `clone()` 메서드는 새로운 로봇 객체를 생성자로 만들어, 초기의 힘 `strength` 와 `speed` 의 속성을 유지한채로 `Robot` 객체를 반환한다.

---

# 장/단점

## 장점

- 객체 생성 비용 절감
    - 복제 작업이 새 객체를 생성하고 초기화하는 것 보다 효율적
- 코드 재사용성
    - 기존 객체를 재사용 하기 떄문에, 불필요한 코드 중복이 없다.

## 단점

### 얕은 복사 vs 깊은 복사

- 얕은 복사
    - 객체의 최상위 레벨 속성만 복사한다.
        - 복사된 객체 내부에 참조 타입이 포함되어 있다면, 원본 객체와 복사된 객체가 **같은 참조를 공유** 한다.
    - 문제점
        - 원본 객체의 내부 참조가 변경되면 복사한 객체에도 영향을 끼친다.
- 깊은 복사
    - 객체의 모든 속성 값을 새롭게 복사한다.
        - 복사된 객체는 내부에 포함된 참조 타입까지 새롭게 만들어지며, **원본과 복사본이 완전히 독립적이다.**
- 따라서, **내부 참조가 많거나 복잡한 객체에서는 독립성을 위해 깊은 복사를 구현해야 할 수 있다.**

---

# 사용 사례

- 게임 개발
    - 적 캐릭터나 아이템 같은 객체를 복제하여 대량으로 생성.
- 데이터베이스 레코드
    - 복잡한 초기화 과정을 줄이고 빠르게 엔티티 객체를 생성.