package DesignPatterns.BehavioralPatterns.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandExample {
    public static void main(String[] args) {
        Chef minsu = new Chef();
        Waiter sujin = new Waiter();

        Order mySteakOrder = new StakeOrder(minsu);
        sujin.acceptOrder(mySteakOrder);

        Order mySideOrder = new SideOrder(minsu);
        sujin.acceptOrder(mySideOrder);

        Order myBeverageOrder = new BeverageOrder(minsu);
        sujin.acceptOrder(myBeverageOrder);

        sujin.orderFinish();

        sujin.orderUndo(mySideOrder);
    }
}

// Command - 주문 인터페이스
interface Order{
    void execute();
    void undo();
}

// ConcreteCommand - 상세 메뉴 주문
class StakeOrder implements Order{
    private Chef chef;

    public StakeOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookSteak();
    }

    @Override
    public void undo() {
        chef.cookSteakUndo();
    }
}

class SideOrder implements Order{
    private Chef chef;

    public SideOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookSide();
    }

    @Override
    public void undo() {
        chef.cookSideUndo();
    }
}

class BeverageOrder implements Order{
    private Chef chef;

    public BeverageOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeLemonade();
    }

    @Override
    public void undo() {
        chef.makeLemonadeUndo();
    }
}

// Receiver (수신자) - 셰프

class Chef {
    public void cookSteak(){
        System.out.println("셰프가 스테이크를 굽습니다.");
    }

    public void cookSteakUndo(){
        System.out.println("스테이크 조리를 취소합니다.");
    }

    public void cookSide(){
        System.out.println("셰프가 감자튀김을 튀깁니다.");
    }

    public void cookSideUndo(){
        System.out.println("감자튀김 조리를 취소합니다.");
    }

    public void makeLemonade(){
        System.out.println("셰프가 레몬에이드를 만듭니다.");
    }

    public void makeLemonadeUndo(){
        System.out.println("레몬에이드 만들기를 취소합니다.");
    }


}

// Invoker (호출자) - 웨이터

class Waiter{
    List<Order> orderList = new ArrayList<>();

    public void acceptOrder(Order newOrder){
        System.out.println("주문 받았습니다.");
        orderList.add(newOrder);
    }

    public void orderFinish(){
        System.out.println("총 " + orderList.size() + "개 주문하셨습니다.");
        for(Order order : orderList){
            order.execute();
        }
        System.out.println("모든 주문을 전달했습니다. 감사합니다.");
        orderList.clear();
    }

    public void orderUndo(Order order){
        System.out.println("요청하신 주문을 취소하겠습니다.");
        order.undo();
    }

}

