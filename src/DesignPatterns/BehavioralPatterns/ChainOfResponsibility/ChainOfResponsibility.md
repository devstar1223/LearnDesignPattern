>📞 **요청을 처리할 수 있는 객체들을 체인으로 연결해, 요청을 처리하거나 다음 객체로 전달하는 패턴**

---

# 설명

- 요청을 처리할 수 있는 여러 **핸들러**(handler) 객체들이 체인 형태로 연결된 구조를 제공한다.
- 요청은 체인의 각 핸들러를 따라 전달되어, 각 핸들러는 요청을 처리하거나 다음 핸들러로 전달한다.

## 특징

- 핸들러를 동적으로 추가하거나 순서를 바꿀 수 있음.
- 클라이언트는 어떤 핸들러가 요청을 처리하는지 알 필요가 없음
- 체인 안의 핸들러가 요청을 처리하지 못하면 자동으로 다음 핸들러로 전달

## 실생활의 예제

- 가게에 손님이 문의 전화를 하면, 알바생이 우선 받는다.
- 알바생이 처리할수 없는 요청이라 판단되면, 매니저에게 요청을 전달한다.
- 매니저도 요청을 처리할 수 없다면, 점장에게 요청을 전달한다.
- 점장이 요청을 처리할 수 있다면, 요청을 처리한다.

---

# 코드

```java
package DesignPatterns.BehavioralPatterns.ChainOfResponsibility;

public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        Handler minsu = new PartTimeWorker("알바 민수",10);
        Handler sujin = new Manager("매니저 수진",100);
        Handler Johann = new GeneralManager("점장 요한",1000);

        minsu.setNextHandler(sujin);
        sujin.setNextHandler(Johann);

        Request easyRequest = new Request("픽업 요청",5);
        Request normalRequest = new Request("단체 주문",90);
        Request hardRequest = new Request("창업 문의",999);

        minsu.requestProcess(easyRequest);
        minsu.requestProcess(normalRequest);
        minsu.requestProcess(hardRequest);
    }
}

class Handler{
    String name;
    Handler nextHandler;
    int capability;

    public Handler(String name, int capability) {
        this.name = name;
        this.capability = capability;
    }

    public void setNextHandler(Handler handler){
      this.nextHandler = handler;
    };

    public void requestProcess(Request request){
        if(request.getRequestLevel() > capability && nextHandler != null){
            System.out.println(nextHandler.getName()+"님. 요청 처리 부탁드립니다.");
            nextHandler.requestProcess(request);
        }else if(request.getRequestLevel() > capability && nextHandler == null){
            System.out.println("현재 상위 담당자가 부재중입니다. 나중에 요청 부탁드려요.");
        }else if(request.getRequestLevel() <= capability){
            System.out.println(this.name + " - 요청 처리 완료 : " +  request.getRequestContent());
        }
    }

    public String getName() {
        return name;
    }
}

class PartTimeWorker extends Handler{
    public PartTimeWorker(String name,int capability){
        super(name,capability);
    }
}

class Manager extends Handler{
    public Manager(String name,int capability){
        super(name,capability);
    }
}

class GeneralManager extends Handler{
    public GeneralManager(String name,int capability){
        super(name,capability);
    }
}

class Request{
    int requestLevel;
    String requestContent;

    public Request(String requestContent, int requestLevel) {
        this.requestContent = requestContent;
        this.requestLevel = requestLevel;
    }

    public int getRequestLevel() {
        return requestLevel;
    }

    public String getRequestContent() {
        return requestContent;
    }
}
```

- `Handler`
    - `Handler` 클래스가 추상적인 역할을 한다.
    - 구체적인 역할은 `PartTimeWorker` , `Manager` , `GeneralManager` 이 한다.
- `PartTimeWorker`,`Manager`,`GeneralManager`
    - `Handler` 의 자식 클래스로, 역할을 나눈다.
    - `setNextHandler` 를 통해 각각의 클래스들은 조건이 맞지 않아 요청을 처리할 수 없는경우. 요청을 넘길 다음 핸들러를 정할 수 있다.
    - `requestProcess` 를 통해 요청을 처리할 수 있다면 처리하고, 처리할 수 없다면 지정해 놓은 다음 핸들러에게 해당 요청을 떠넘긴다.

---

# 장/단점

## 장점

- **유연성**
    - 핸들러의 추가가 용이해진다.
- **단일 책임 원칙 준수**
    - 각 핸들러는 자신이 처리할 수 있는 요청만 처리한다.
- **결합도 감소**
    - 클라이언트는 체인의 구성에 대해 알 필요가 없다.

## 단점

- 비효율성
    - 요청이 지나치게 많은 핸들러를 거쳐야 할 경우, 비효율적임
- 취약성
    - 핸들러의 누락이나, 설정 오류로 요청이 전달되지 않을 수 있음.

---

# 사용 사례

- **UI 이벤트 처리**
    - 버튼 클릭, 드래그 등 이벤트를 위에서 아래로 전달.
- **권한 인증**
    - 사용자 요청을 여러 계층(사용자, 관리자, 시스템 관리자)에서 확인.