> 🍝 **관련 객체들을 생성하는 인터페이스를 제공하여 구체적인 클래스의 인스턴스를 생성하는 패턴**

---

# 설명

- 구체적인 클래스를 지정하지 않고 관련된 객체를 생성하는 인터페이스
- 클라이언트는 구체적인 객체 생성 방법을 알 필요 없이, 어떤 계열의 객체들을 생성할지 결정할 수 있다.
- 다양한 객체 계열을 생성할 수 있는 클래스를 제공하지만, 클라이언트는 추상적인 인터페이스를 통해 객체를 생성하고 조작한다.

## 특징

- 객체 생성시 구체적인 클래스 지정하지 않음
- 관련 객체를 그룹으로 묶어 생성할 수 있음
- 클라이언트는 객체 생성 방식을 모르고, 인터페이스만 안다.

## 실생활의 예제

- 학생식당에는 **한식** / **중식** / **양식** 등등 코너 별로 나누어져 있다.
- **양식**의 **스파게티** 가 먹고싶다면, 양식 코너에 가서 주문을 하면 된다.
    - 양식은 **스파게티, 치킨** 등등이 있다.
- 손님은 내 음식이 **양식 코너** 에서 만들어지는것은 알고있지만, 내 음식이 어떻게 만들어지는지는 모른다.
    - 단순히 양식 코너에서 주문을 할 뿐이다.
    - **스파게티**가 나올지, **치킨**이나올지는 양식 코너의 역할에 달렸다.

---

# 코드

```java
package DesignPatterns.CreationalPatterns.AbstractFactory;

public class AbstractFactoryExample {
    public static void main(String[] args) {
        Cafeteria leftCafeteria = new KoreaCafeteria();
        Food myFood = leftCafeteria.createFood("김치라면");
        System.out.println(myFood.getTaste());
        myFood = leftCafeteria.createFood("비빔밥");
        System.out.println(myFood.getTaste());

        Cafeteria rightCafeteria = new WesternCafeteria();
        Food yourFood = rightCafeteria.createFood("스파게티");
        System.out.println(yourFood.getTaste());
    }
}

// 음식 인터페이스
interface Food {
    String getTaste();
}

// 한국 음식 클래스들
class KimchiRamen implements Food {
    String taste = "맵다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

class Bibimbap implements Food {
    String taste = "산뜻하다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

// 서양 음식 클래스들
class Spaghetti implements Food {
    String taste = "짭짤하다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

class Chicken implements Food {
    String taste = "바삭하다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

// 물 클래스
class Water implements Food {
    String taste = "없는 메뉴를 시키니까 물이 나왔다.";
    @Override
    public String getTaste() {
        return this.taste;
    }
}

// 조리 코너
interface Cafeteria {
    Food createFood(String orderFood);
}

// 한식 코너
class KoreaCafeteria implements Cafeteria {
    @Override
    public Food createFood(String orderFood) {
        if (orderFood.equals("비빔밥")) {
            return new Bibimbap();
        } else if (orderFood.equals("김치라면")) {
            return new KimchiRamen();
        } else {
            return new Water();
        }
    }
}

// 양식 코너
class WesternCafeteria implements Cafeteria {
    @Override
    public Food createFood(String orderFood) {
        if (orderFood.equals("스파게티")) {
            return new Spaghetti();
        } else if (orderFood.equals("치킨")) {
            return new Chicken();
        } else {
            return new Water();
        }
    }
}
```

- `Food` 인터페이스
    - 모든 음식들은 `Food` 인터페이스의 구현체이다.
    - 양식도 한식도 모두 `Food` 의 구현체이다.
- `Cafeteria` 인터페이스
    - 모든 식당(코너)들은 음식을 만들어야 하므로 `createFood()` 메서드를 정의해놓았다.
- `KoreaCafeteria`, `WesternCafeteria` 클래스
    - 음식 주문에 맞는 `Food` 객체를 생성해서 반환한다.
    - 한식코너는 한식만, 양식코너는 양식만 반환한다.
        - 물론, 모두 `Food` 의 구현체이다.

---

# 장/단점

## 장점

- 확장성
    - 새로운 객체 계열이 추가되어도 기존 코드를 수정할 필요 없이 확장 가능
- 유지보수 용이
    - 객체 생성에 대한 책임이 팩토리 클래스에 있어, 객체 생성 방식 변경 및 수정시 팩토리 클래스만 수정한다.
- 객체 생성의 일관성
    - 팩토리 인터페이스를 통해 객체를 생성하므로, 일관된 객체 생성 방법을 가지고 있어 코드의 중복을 줄인다.

## 단점

- 클래스 수 증가
    - 새 제품 계열이 추가될떄 마다 팩토리 클래스가 추가되어, 시스템이 복잡해질 수 있다.
- 유연성 부족
    - 팩토리 패턴이 고정된 계열을 생성하도록 설계되므로, 다양한 종류의 객체를 동적으로 생성하려는 경우엔 적합하지 않다.

---

# 사용 사례

- 다양한 유형의 제품을 카테고리별로 관리해야 할때.
    - 자동차 제조업체의 차종별 팩토리
- 데이터베이스 연결 관리
    - 데이터베이스 종류 별로 다른 커넥터 객체를 생성하는 경우

---

# 기타 : 팩토리 메서드와의 차이

- 팩토리 메서드
    - 하나의 **팩토리 클래스**가 **한 종류의 객체만** 생성한다.
    - 라떼 만드는 라떼팩토리
    - 아메리카노 만드는 아메리카노 팩토리
- 추상 팩토리 패턴
    - 하나의 **팩토리 클래스**가 **여러 종류의 객체를 생성** 가능하다.
    - 치킨, 스파게티, 피자 다 만드는 양식팩토리
    - 비빔밥, 냉면, 김치라면 다 만드는 한식팩토리