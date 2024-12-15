package BehavioralPatterns.Observer;

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