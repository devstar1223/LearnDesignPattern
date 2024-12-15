> **🔫 행위를 캡슐화하여 동적으로 교체할 수 있도록 만드는 패턴**
---
# 설명

- 객체의 행위를 클래스로 캡슐화한다.
- 이를 동적으로 바꿀 수 있게 하는 디자인 패턴
- 알고리즘을 런타임에 교체할 수 있어 유연성과 확장성이 높아진다.

## 특징

- 행위를 캡슐화하여 독립적으로 정의 및 변경 가능
- 객체의 행위를 런타임에 선택 가능
- 클래스의 확장성을 높이고 코드 중복을 줄임

## 실생활의 예제

- 총을 한자루 가지고 있다.
- 상황에 따라 공포탄 / 실탄 / 가스탄 을 장전 하려고 한다
- 발사 하는 방법은 동일하지만, 탄환의 종류에 따라 결과는 달라진다.
---
# 코드

```java
package BehavioralPatterns;

public class Strategy {
    public static void main(String[] args) {
        Gun gun = new Gun();

        gun.setBullet(new BlankBullet());
        gun.fire();

        gun.setBullet(new RealBullet());
        gun.fire();

        gun.setBullet(new GasBullet());
        gun.fire();
    }
}

class Gun {

    Bullet bullet;

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
        System.out.println("장전 완료.");
    }

    public void fire() {
        System.out.println("격발!");
        bullet.action();
    }
}

interface Bullet{
    void action();
}

class BlankBullet implements Bullet{

    @Override
    public void action() {
        System.out.println("공포탄 - 상태이상 부여");
    }
}
class GasBullet implements Bullet{

    @Override
    public void action() {
        System.out.println("가스탄 - 지속 데미지 10");
    }
}

class RealBullet implements Bullet{

    @Override
    public void action() {
        System.out.println("실탄 - 단발 데미지 100");
    }
}
```

- `Gun` 클래스는 `Bullet` 인터페이스에 의존하여, 새로운 탄환이 추가되더라도 `Gun` 클래스의 수정 없이 동작한다.
- `setBullet()` 메서드를 통해, 실행중에 탄환 전략을 교체 할 수 있다. → **알고리즘의 동적 교체**
---
# 장/단점

## 장점

- 상황에 따라 적합한 동작(알고리즘)을 선택할 수 있다.
- 새로운 전략을 추가하거나, 교체해도 기존 코드를 수정할 필요가 없다.

## 단점

- 전략의 개수가 많아질경우, 관리가 어려울 수 있음
---
# 사용 사례

- 게임 개발
    - 무기를 상황에 따라 교체하여 사용
- 결제 시스템
    - 다양한 결제 방법을 선택적으로 적용
- 로그 처리
    - 애플리케이션의 실행 환경에 따라 로그 출력 방식 변경(콘솔, 파일, 원격 서버)