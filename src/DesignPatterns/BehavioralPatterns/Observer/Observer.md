> **📺 한 객체의 상태 변화에 따라 관련된 다른 객체들에게 자동으로 알림을 보내는 패턴**
---
# 설명

- **발행자**(Publisher)와 **구독자**(Subscriber) 의 관계를 정의하는 디자인 패턴
- 객체간 의존성을 느슨하게 유지하면서, 하나의 객체 상태가 변경 되었을때. 의존 객체(관찰자)들 에게 자동으로 알리는 메커니즘

## 특징

- 주제(Subject)와 관찰자(Observer)라는 두 가지 주요 역할 존재
- 관찰자는 주제에 등록하고, 상태가 변경되면 알림을 받음
- 느슨한 결합(loose coupling) : 주체와 관찰자는 서로에 대해 최소한의 정보만 알면 된다.
- 하나의 주제에 여러 관찰자를 등록 가능하다.

## 실생활의 예제

- 유튜브 구독 시스템
- 유튜버(주제)가 새로운 동영상을 올리면, 구독자(관찰자)들에게 알림을 보낸다.
- 하나의 유튜버에 많은 구독자들이 있다.
---
# 코드

```java
package BehavioralPatterns;

import java.util.*;

public class ObserverExample {
    public static void main(String[] args) {
        Youtuber javaYoutuber = new Youtuber();

        Subscriber minsu = new Subscriber("민수");
        Subscriber sujin = new Subscriber("수진");

        javaYoutuber.subscribe(minsu);
        javaYoutuber.subscribe(sujin);

        javaYoutuber.uploadNewVideo("자바 기초");
    }
}

interface Subject{
    void subscribe(Observer observer);
    void subscribeCancel(Observer observer);
    void uploadNewVideo(String videoName);
}

interface Observer{
    void newVideo(String videoName);
}

class Youtuber implements Subject{

    private List<Observer> subscriberList = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        subscriberList.add(observer);
    }

    @Override
    public void subscribeCancel(Observer observer) {
        subscriberList.remove(observer);
    }

    @Override
    public void uploadNewVideo(String videoName) {
        for(Observer subscriber : subscriberList){
            subscriber.newVideo(videoName);
        }
    }
}

class Subscriber implements Observer{

    private String name;

    public Subscriber(String name){
        this.name = name;
    }

    @Override
    public void newVideo(String videoName) {
        System.out.println(name+"님의 구독 채널 새 영상 : "+videoName);
    }
}
```

- **`Subject`**
    - 상태 변화(새 영상 업로드)를 주도하는 객체
    - 코드에서는 **유튜버**가 주제에 해당한다.
    - `subscribe()` 메소드와 `subscribe` 메소드를 통해 관찰자(구독자)를 등록하고 취소할 수 있다.
- **`Observer`**
    - 상태 변화를 통지받고 반응하는 객체
    - 코드에서는 **구독자**가 주제에 해당한다.
- 과정
    - `Youtuber` 객체에 여러 `Subscriber` 객체가 구독자로 등록된다.
    - `Youtuber` 가 새 영상을 업로드 하면 `uploadNewVideo()` 가 호출되면서, 등록된 구독자들에게 `newVideo()` 메소드를 호출한다.
    - `newVideo()` 메서드는 구독자에게 알림 메세지를 보낸다.
---
# 장/단점

## 장점

- 느슨한 결합
    - 주제와 관찰자간의 결합도가 낮아 독립적인 수정이 가능하다.
- 확장성
    - 새로운 관찰자를 쉽게 추가 가능하다.

## 단점

- 성능 저하
    - 관찰자가 많아지면 알림 전달 비용이 증가
- 순환 의존성 문제
    - 잘못 구현할 경우, 주제와 관찰자 간의 무한 호출 문제가 발생할 수 있다.
- 복잡성 증가
    - 주제와 관찰자 간의 관계를 추적하기 어려울 수 있다.
---
# 사용 사례

- 이벤트 리스너
    - GUI 프로그래밍에서의 버튼 클릭, 마우스 이벤트
- 모니터링 시스템
    - 애플리케이션 상태 변경을 모니터링 → 변경시 알림
- 주식 가격 변동 알림 시스템