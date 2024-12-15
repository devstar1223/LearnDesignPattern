>🏧 **객체에 대한 접근을 제어하기 위해 대리인을 제공하는 패턴**
---
# 설명

- 어떤 객체에 직접 접근하는 대신, 그 객체에 대한 대리인 역할을 하는 프록시 객체를 통해 접근하도록 만드는 구조
- 프록시 객체는 실제 객체에 대한 제어, 접근 보호, 지연 초기화 등의 다양한 기능을 추가 가능하다.

## 특징

- 대리인이 실제 객체처럼 동작
- 클라이언트는 프록시 객체를 사용해도 실제 객체와 차이를 느끼지 못함.

## 실생활의 예제

- 현금이 필요하면 ATM 에 계좌를 넣고 현금을 뽑는다.
- 하지만, ATM 은 실제 계좌에 직접 접근하지 않는다.
    - 실제로 처리하는 곳은 **은행 서버**
        - 은행 서버는 사용자가 직접 접근할수 없어야 한다.
    - 따라서 ATM은 프록시 역할을 해, 사용자와 은행 서버 사이의 인터페이스로 작동한다.
---
# 코드

```java
package StructuralPatterns;

public class Proxy {
    public static void main(String[] args) {
        Account myAccount = new Account(100000,"1234");

        Bank storeATM = new ATM();
        storeATM.deposit(myAccount,50000);
        storeATM.withdraw(myAccount,1000,"1000");
        storeATM.withdraw(myAccount,77777,"1234");
    }
}

class Account{
    private int money;
    private String PIN;

    public Account(int money, String PIN){
        this.money = money;
        this.PIN = PIN;
    }
    public int getMoney() {
        return money;
    }
    public void deposit(int money){
        this.money += money;
    }
    public void withdraw(int money){
        this.money -= money;
    }
    public boolean validPIN(String inputPin){
        return (this.PIN).equals(inputPin);
    }
}

interface Bank{
    void deposit(Account account, int requestMoney);
    int withdraw(Account account, int requestMoney, String PIN);
}

class BankServer implements Bank{

    @Override
    public void deposit(Account account, int requestMoney) {
        account.deposit(requestMoney);
        System.out.println("입금이 완료되었습니다.");
        System.out.println("현재 금액 : " + account.getMoney());
    }

    @Override
    public int withdraw(Account account, int requestMoney, String PIN) {
        account.withdraw(requestMoney);
        System.out.println("출금이 완료되었습니다.");
        System.out.println("현재 금액 : " + account.getMoney());
        return requestMoney;
    }
}

class ATM implements Bank{

    private final BankServer bankServer = new BankServer();

    @Override
    public int withdraw(Account account, int requestMoney, String PIN) {
        if(account.validPIN(PIN)){
            return bankServer.withdraw(account, requestMoney, PIN);
        }
        System.out.println("비밀번호가 일치하지 않습니다.");
        return 0;
    }

    @Override
    public void deposit(Account account, int requestMoney) {
        bankServer.deposit(account, requestMoney);
    }
}
```

- 실제 객체(Real Subject) → **은행 서버**
    - 실제로 모든 은행 작업(입금, 출금)을 처리하는 주체
    - 이 서버는 클라이언트가 직접 접근해서는 안된다.
    - 오직 프록시를 통해서만 접근 가능하다.
- 대리 객체(Proxy) → **ATM**
    - ATM은 클라이언트와 실제 객체 사이의 중개 역할을 수행한다.
    - ATM은 은행 서버에 요청을 전달하기 전에, PIN 검증과 같은 부가작업을 수행하여 요청을 제한하거나 보호한다.
- 클라이언트(Client) → **사용자**
    - 사용자는 ATM 을 통해 입금과 출금을 요청한다.
    - 사용자 입장에서는 ATM은 단순히 입출금을 처리해줄 뿐이고, 은행 서버의 존재는 알 수 없다.
---
# 장/단점

## 장점

- 지연 초기화
    - 실제 객체를 필요한 시점에 생성하여 초기화 비용을 절감.
- 접근 제어
    - 객체에 대한 접근을 제어하거나 권한을 검사할 수 있음

## 단점

- 복잡성 증가
    - 프록시 클래스를 추가로 작성해야 함.
- 성능 저하
    - 프록시를 통해 요청이 전달되므로, 직접 접근보다 성능이 떨어질 수 있다.
- 동기화 문제

  여러 스레드가 프록시를 사용할 경우, 동기화를 적절히 처리해야함.

---
# 사용 사례

- 보안
    - 데이터베이스나 리소스에 대한 접근 권한을 제어할때 사용한다.
- 캐싱
    - 반복적인 요청에 대해 결과를 저장해 두고 성능을 향상