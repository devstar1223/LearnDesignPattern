>🔌 **호환되지 않는 인터페이스를 연결해주는 디자인 패턴**
---

# 설명

- 서로 다른 인터페이슬를 가진 클래스를 연결할때 사용된다.
- 클라이언트는 어댑터를 통해 다른 인터페이스를 가진 객체와 통신 가능하다.
- 기존 코드를 변경하지 않고 새로운 기능 추가가 가능하다.

## 특징

- 클라이언트 코드 변경 없이 서로 다른 인터페이스를 연결
- 변환할 인터페이스를 하나로 통합할 수 있음
- 어댑터 클래스를 통해 인터페이스 변환

## 실생활의 예제

- 110V를 사용하는 나라와, 220V를 사용하는 나라가 있다.
    - ex) 일본은 110V / 한국은 220V
- 각각의 나라는 자신이 사용하는 전압을 기준으로 가전을 개발한다.
- 어댑터가 있다면 전압이 맞지 않아도 사용 가능하다.

---

# 코드

```java
package CreationalPatterns;

public class Adapter {
    public static void main(String[] args) {
        JapanProduct japanFan = new JapanFan();

        KoreaProduct adapter = new VoltAdapter(japanFan);
        adapter.use220V();

        KoreaProduct koreaAircon = new KoreaAircon();
        koreaAircon.use220V();
    }
}

interface JapanProduct {
    void use110V();
}

class JapanFan implements JapanProduct{

    @Override
    public void use110V() {
        System.out.println("일본 선풍기 가동중");
    }
}

interface KoreaProduct {
    void use220V();
}

class KoreaAircon implements KoreaProduct{
    
    @Override
    public void use220V() {
        System.out.println("한국 에어컨 가동중");
    }
}

class VoltAdapter implements KoreaProduct{
    private JapanProduct japanProduct;
    
    public VoltAdapter(JapanProduct japanProduct) {
        this.japanProduct = japanProduct;
    }

    @Override
    public void use220V() {
        System.out.println("한국 어댑터 : 110V의 제품을 220V에 맞게 변환합니다.");
        japanProduct.use110V();
    }
}
```

- `JapanProduct` 인터페이스와 `KoreaProduct` 인터페이스
    - `JapanProduct` 는 110V를 사용하는 일본 제품을 위한 인터페이스
    - `KoreaProduct` 는 220V를 사용하는 한국 제품을 위한 인터페이스
- `VoldAdapter` 클래스
    - `KoreanProduct` 인터페이스를 구현한다.
    - 실제로는 `JapanProduct` 객체를 내부에서 사용한다.
    - 이러한 과정으로 110V 제품을 220V 시스템에 맞게 변환한다.
- `VoltAdapter` 가 다른 인터페이스간 변환을 책임지며, 기존 시스템`JapanProduct`을 변경하지 않고, 새로운 시스템 `KoreaProduct` 에 맞게 변환한다.

---

# 장/단점

## 장점

- 기존 시스템을 변경하지 않고 새로운 시스템 통합
- 인터페이스 차이가 있어도. 클라이언트가 변환된 인터페이스로 작업 가능.
- 구현된 시스템을 변경하지 않고 새로운 요구사항 쉽게 수용 가능.
    - 기존 시스템과 새로운 시스템을 동시 운영 가능

## 단점

- 불필요한 어댑터 클래스가 추가되어 코드가 길어진다.
- 다양한 어댑터 클래스가 필요해질경우, 새로운 기능을 추가할 때마다 계속해서 어댑터를 추가해야 하므로 확장성에 문제가 생긴다.
- 어댑터가 지나치게 많아지면, 시스템이 과도하게 분리되어 전체적인 설계가 직관적이지 않게 된다.

---

# 사용 사례

- 레거시 시스템에 새로운 기능을 추가할때
    - 기존의 결제 시스템이 카드 결제를 처리하고 있었다.
    - 모바일 결제 시스템을 추가해야 한다면, 새로운 결제 방식이 기존 시스템에서 사용될 수 있도록 해야한다.
    - 어댑터가 모바일 결제 시스템을 카드 결제 API와 동일하게 사용할 수 있도록 변환할 수 있다.
    - 다시 생각해보면, **애초에 확장성과 유지보수 면에서 처음부터 잘 만들었거나, 아예 리팩토링 하는게 장기적으로 나을지도 모른다.**