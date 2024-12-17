>🥪 **객체 생성을 단계별로 나누어 복잡한 객체를 유연하게 생성할 수 있게 해주는 패턴**

---

# 설명

- 복잡한 객체를 생성할때 객체의 각 부분을 단계적으로 설정할 수 있게 해준다.
- 객체 생성 과정에서 필요한 다양한 구성 요소를 유연하게 조합할 수 있도록 해준다.
- 객체를 구성하는 여러 단계를 따로 나누고, 최종적으로 완성된 객체를 반환한다.

## 특징

- 복잡한 객체 생성을 단계적으로 처리
- 메서드 체이닝을 통한 가독성 향상
- 불변 객체를 쉽게 생성 가능

## 실생활의 예제

- 써브웨이 에서 고객은 다양하게 주문할 수 있다.
    - 메뉴는 로스트치킨
    - 빵은 플랫브레드
    - 치즈는 아메리칸 치즈
    - 야체에서 오이,토마토 빼주세요
    - 소스는 머스타드

---

# 코드

```java
package DesignPatterns.CreationalPatterns.Builder;

import java.util.*;

public class BuilderExample {
    public static void main(String[] args) {
        Sandwich mySandwich = new Sandwich.Builder()
                .menu("로스트 치킨")
                .bread("플랫 브래드")
                .cheese("아메리칸 치즈")
                .excludeVegetables("오이,토마토")
                .sauce("머스타드, 소금, 후추")
                .build();

        mySandwich.contains();
    }
}

class Sandwich {
    private final String menu;
    private final String bread;
    private final String cheese;
    private final String vegetable;
    private final String sauce;

    private Sandwich(Builder builder) {
        this.menu = builder.menu;
        this.bread = builder.bread;
        this.cheese = builder.cheese;
        this.vegetable = builder.vegetable;
        this.sauce = builder.sauce;
    }

    public void contains() {
        System.out.println("내 샌드위치의 구성을 보았다.");
        System.out.println("메뉴 : " + menu);
        System.out.println("빵 : " + bread);
        System.out.println("치즈 : " + cheese);
        System.out.println("야채 : " + vegetable);
        System.out.println("소스 : " + sauce);
    }
    
    public static class Builder {
        private String menu;
        private String bread;
        private String cheese;
        private String vegetable;
        private String sauce;

        private final List<String> vegetableList = new ArrayList<>(Arrays.asList("양상추", "토마토", "오이", "피클", "올리브"));

        public Builder menu(String menu) {
            this.menu = menu;
            return this;
        }

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder excludeVegetables(String excludedVegetable) {
            List<String> requestVegetables = new ArrayList<>(vegetableList);
            String[] excludedArray = excludedVegetable.split(",");
            for (String exclude : excludedArray) {
                requestVegetables.remove(exclude.trim());
            }
            this.vegetable = String.join(", ", requestVegetables);
            return this;
        }

        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Sandwich build() {
            return new Sandwich(this);
        }
    }
}

```

- `Sandwich` 클래스
    - 메뉴, 빵, 치즈와 같은 속성이 `final` 로 선언되어 **불변성**을 보장한다.
    - 생성자를 `private` 으로 설정하고, 파라미터로 `Builder` 를 받게 설계하여, `Builder` 내부 클래스만 객체를 생성 가능하게 한다.
- `Builder` 클래스
    - `menu()` , `bread()` , `cheese()` 등의 메서드 체이닝을 사용해 사용자가 객체의 구성을 원하는대로 설정할 수 있다.
    - `build()` 메서드를 호출하면, `Builder` 클래스가 가지고 있던 필드 값을 바탕으로 `Sandwich` 객체를 생성해 클라이언트에게 반환한다.

---

# 장/단점

## 장점

- 가독성 증가
    - 메서드 체이닝을 통해 코드를 간결하게 작성할 수 있어, 어떤 값을 설정했는지 파악하기 쉽다.
- 불변 객체 지원
    - 객채 생성 후 필드를 수정할 수 없게 설계해, 불변 객체를 만들기 용이하다.

## 단점

- 코드 복잡성 증가
    - 빌더 클래스와 메서드를 추가로 작성해야 하기 때문에, 코드가 길어지고 복잡해질 수 있다.
    - 특히, 필드가 적거나 단순한 객체에 쓰는것은 코드를 복잡하게 만들 수 있다.

---

# 사용 사례

- 복잡한 설정을 가진 객체.
    - HTTP 요청 객체, 데이터베이스 연결 객체 등등..
    - 많은 필드를 갖는 객체를 유연하게 생성할 때 사용한다.
- 불변 객체 생성이 필요할떄
    - 은행 계좌의 거래내역, 신용 카드 결제 요청 등등… 변경 가능할 경우 데이터 무결성을 해칠때.
    - 생성 후 절대 변경되지 않아야할 객체에 유용하다.