>🦒 **객체의 구조를 변경하지 않고, 객체에 새로운 동작을 추가할 수 있는 패턴**


---

# 설명

- 객체 구조를 변경하지 않으면서도 객체에 새로운 동작을 추가할 수 있는 패턴이다.
- 각 객체마다 방문자가 방문하여 특정작업을 수행한다.
    - 방문자(Visitor) : 다양한 동작들을 정의한다.
    - 원소(Element) : 방문을 받을 객체로, `accept()` 메서드를 통해 방문자를 받아들인다.
- 객체의 클래스를 그대로 두고, 방문자만 추가로 수정.

## 특징

- 객체 구조 동작의 분리를 통해, 변경하지 않고 새로운 동작 추가 가능 → **OCP**
- 방문자가 객체 종류에 따라 다른 작업을 수행

## 실생활의 예제

- 동물원에는 기린, 사자, 펭귄등 다양한 동물이 있다.
- 동물원의 직원들이 있다.
    - 먹이를 주는 직원과 건강 체크를 하는 직원이 다르다.
    - 각 직원은 모든 동물에게 같은 작업을 반복적으로 해야한다.
    - 매번 보건에 따라 직원에게 다른일을 시키는것은 복잡하다.
- 또한 동물은 어떤 직원이 나에게 어떤 작업을 하는지 구체적으로 알지 못한다.
    - 동물이 자신의 몸을 내어주면 직원이 자신만의 작업을 처리한다.
- 따라서, 동물에게 추가 작업을 더 하고 싶다면, 추가 직원만 늘리면 된다.

---

# 코드

```java
package DesignPatterns.BehavioralPatterns.Visitor;

public class VisitorExample {
    public static void main(String[] args) {
        Animal giraffe = new Giraffe("나뭇잎");
        Animal lion = new Lion("고기");
        Animal penguin = new Penguin("새우");

        Visitor minsu = new FeedVisitor();
        giraffe.accept(minsu);
        lion.accept(minsu);
        penguin.accept(minsu);

        Visitor sujin = new HealthCheckVisitor();
        giraffe.accept(sujin);
        lion.accept(sujin);
        penguin.accept(sujin);
    }
}

abstract class Animal {
    protected String favoriteFood;

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public abstract void accept(Visitor visitor);
}

class Giraffe extends Animal {
    public Giraffe(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Lion extends Animal {
    public Lion(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Penguin extends Animal {
    public Penguin(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(Giraffe giraffe);
    void visit(Lion lion);
    void visit(Penguin penguin);
}

class FeedVisitor implements Visitor {
    @Override
    public void visit(Giraffe giraffe) {
        System.out.println("기린에게 " + giraffe.getFavoriteFood() + " 먹이기 완료.");
    }

    @Override
    public void visit(Lion lion) {
        System.out.println("사자에게 " + lion.getFavoriteFood() + " 먹이기 완료.");
    }

    @Override
    public void visit(Penguin penguin) {
        System.out.println("펭귄에게 " + penguin.getFavoriteFood() + " 먹이기 완료.");
    }
}

class HealthCheckVisitor implements Visitor {
    @Override
    public void visit(Giraffe giraffe) {
        System.out.println("기린의 목을 체크합니다.");
    }

    @Override
    public void visit(Lion lion) {
        System.out.println("사자의 발톱을 체크합니다.");
    }

    @Override
    public void visit(Penguin penguin) {
        System.out.println("펭귄의 부리를 체크합니다.");
    }
}
```

- `Animal` 클래스
    - 선호하는 먹이인 `favoriteFood` 속성을 가지고 있다.
    - `accept` 메서드를 통해 방문자의 행동을 받아들인다.
        - 실제 행동은 방문자가 처리한다.
- `Giraaffe`, `Lion`, `Penguin`
    - 각각 `accept` 메서드를 오버라이드해, 요청을 수락한다.
        - 해당 동물에 맞는 동작을 수행할 수 있게 `visitor.visit(this)` 를 호출한다.
- `Visitor` 인터페이스
    - 방문하는 동물에 대해 특정 동작 `visit`을 정의한다.
- `FeedVisitor, HealthCheckVisitor` 클래스
    - `FeedVisitor` 가 각 동물에게 먹이를 주는 동작을 정의한다.
    - `HealthCheckVisitor` 가 건강 상태를 체크하는 동작을 정의한다.

---

# 장/단점

## 장점

- OCP 만족 (개방-폐쇄 원칙)
    - 새로운 동작을 추가할 때 기존 객체 클래스를 수정할 필요가 없다.
- 유지보수 용이
    - 객체 구조를 수정하지 않고, 새로운 동작을 추가 가능하여 확장성이 뛰어나다.

## 단점

- 새로운 객체 추가시 수정 필요
    - 객체 구조가 변경될 때마다 새로운 방문자 클래스를 추가해야 한다.

---

# 사용 사례

- 파일 시스템 탐색기
    - 파일 시스템에서 다양한 파일과 폴더를 탐색하고, 각기 다른 작업을 수행할때 사용한다.
    - 각 타입별로 방문자가 다르게 동작하며, 파일에 따라 다른 작업을 수행한다.