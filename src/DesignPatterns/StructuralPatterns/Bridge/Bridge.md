>🐕 **구현부에서 추상층을 분리하여, 두 계층을 독립적으로 확장할 수 있게 만드는 구조적 디자인 패턴**

---

# 설명

- 추상화와 구현을 분리하여, 각각 독립적으로 변화할 수 있도록 만든다.
- 구현의 변경이 추상화 계층에 영향을 미치지 않도록 한다.
- 추상화 계층은 기능을 정의한다.
- 구현 계층은 해당 기능을 어떻게 수행할지 정의한다.

## 특징

- 구현과 추상화 분리
    - 추상화 계층과 구현 계층을 분리하여 독립적으로 변경 가능하다.
- 유연성
    - 추상화 계층과 구현 계층을 다양한 방식으로 확장 가능

## 실생활의 예제

- 동물과 동물들의 성격이 있다.
    - 동물 : 강아지, 고양이
    - 성격 : 소심한, 용감한
- 모든 성격과 동물을 전부 구현하면 다음과 같다.
    - 소심한 강아지
    - 소심한 고양이
    - 용감한 강아지
    - 용감한 고양이
- 동물과 성격이 1개씩 추가되면 3*3 = 9. 총 9가지가 생긴다.
- 따라서, 동물과 성격은 추상화하고, 세부적인 동물 이름과 성격만 구체화한다.
    - 동물 : 강아지 / 고양이
    - 성격 : 용감한 / 소심한
    - **즉, "어떤 동물"은 "성격"을 가지고 있다.**
- 동물과 성격이 추가되도 3+3 = 6. 총 6가지 이다.
- 추상화 계층 : 동물 (어떤 동물인지 정의)
- 구현 계층 : 성격 (동물이 구체적인 성격에 따라 다르게 행동함)

---

# 코드

```java
package StructuralPatterns;

public class Bridge {
    public static void main(String[] args) {
        // 성격을 동적으로 설정
        Personailty shyPersonality = new Shy();
        Personailty bravePersonality = new Brave();

        // 각 동물에 다른 성격 설정
        Animal myAnimal = new Cat(shyPersonality);
        Animal neighborAnimal = new Dog(bravePersonality);

        // 각 동물과 성격 설명
        myAnimal.describe();
        neighborAnimal.describe();
    }
}

// 동물에 대한 "추상"적인 개념 (추상 클래스)
abstract class Animal{
    protected Personailty personailty; // 동물은 성격을 가지고 있다.

		// 동물이 생성될때 성격을 설정해야 한다.
    public Animal(Personailty personailty){
        this.personailty = personailty; // 동물에 성격을 부여한다.
    }

    abstract void describe(); // 동물은 설명 되어야 한다.
}

// "구체"적인 동물 클래스
class Dog extends Animal {
    public Dog(Personailty personailty){
        super(personailty); // 부모 클래스인 Animal 에게 성격을 전달한다.
    }

    @Override
    void describe() {
        personailty.describe();
        System.out.println("강아지는 멍멍");
    }

}

class Cat extends Animal {
    public Cat(Personailty personailty){
        super(personailty);
    }

    @Override
    void describe() {
        personailty.describe();
        System.out.println("고양이는 야옹");
    }
}

// 성격에 대한 "추상"적인 개념
interface Personailty{
    void describe();
}

// "구체"적인 성격 클래스
class Shy implements Personailty{

    @Override
    public void describe() {
        System.out.println("소심한 성격을 가지고 있다.");
    }
}

class Brave implements Personailty{

    @Override
    public void describe() {
        System.out.println("용감한 성격을 가지고 있다.");
    }
}
```

- `Animal` 은 추상적인 개념이다.
    - 동물이라면 이런 속성(필드)를 가지고 있을거고
    - 동물이라면 이런 행동(메서드)을 할거고
- `Personailty` 는 추상적인 개념이다.
    - 모든 성격들은 당연히 설명 되어야 하고.
    - 성격을 설명하는 방식은 다를 수 있다.
- `Dog` `Cat` 은 구체적인 클래스이다.
    - `Animal` 클래스는 추상적인 개념이고, `Dog`와 `Cat`은 그 추상적인 동물을 구체화 한 것이다.
- `Shy` `Brave` 는 구체적인 클래스 이다.
    - `Personality`는 성격을 정의하는 추상적인 개념이고, `Shy`와 `Brave`는 그 추상적인 성격을 실제로 구현한 구체적인 성격들이다.
    - 따라서 동물들은 추상적인 성격을 가지고 있고, 그 성격에 맞춰 소심한 성격이나 용감한 성격을 적용할 수 있다.
- 결론
    - 성격이 추가될때는 `Personailty` 인터페이스만 구현한 새로운 클래스를 추가하면 된다.
    - 기존의 동물 클래스는 수정할 필요가 없기 떄문에, 동물과 성격을 독립적으로 확장할 수 있다.
    - 마찬가지로 새로운 동물이 추가될 때도 `Animal` 클래스를 상속받아 새로운 동물을 추가하면된다.

---

# 장/단점

## 장점

- 유연성
    - 추상화 계층과 구현 계층을 독립적으로 변경 가능해 시스템 확장에 용이하다.

## 단점

- 복잡성 증가
    - 클래수의 수가 증가하여 시스템이 복잡해 질 수 있다.

---

# 사용 사례

- 리모컨 시스템
    - 다양한 TV 브랜드에 대해 동일한 리모컨 인터페이스를 사용하되, 각 브랜드 별로 구현을 다르게 한다.