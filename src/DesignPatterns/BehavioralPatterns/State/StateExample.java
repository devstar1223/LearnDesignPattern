package BehavioralPatterns.State;

public class StateExample {
    public static void main(String[] args) {
        Order order = new Order();
        order.infoMessageOut();

        order.sendSign();
        order.infoMessageOut();

        order.deliverySign();
        order.infoMessageOut();

        order.deliveryCompleteSign();
        order.infoMessageOut();
    }
}

class Order{
    OrderState orderState;

    public Order(){
        setOrderState(new OrderComplete());
    }

    private void setOrderState(OrderState orderState){
        this.orderState = orderState;
    }

    public void infoMessageOut(){
        System.out.print("현재 상태 : ");
        orderState.infoMessage();
    }

    public void sendSign(){
        setOrderState(new Send());
    }

    public void deliverySign(){
        setOrderState(new Delivery());
    }

    public void deliveryCompleteSign(){
        setOrderState(new DeliveryComplete());
    }
}

interface OrderState{
    void infoMessage();
}

class OrderComplete implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("주문이 완료되었습니다.");
    }
}

class Send implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("판매자가 물건을 발송했습니다.");
    }
}

class Delivery implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("택배기사님이 물건을 배송중입니다.");
    }
}

class DeliveryComplete implements OrderState{
    @Override
    public void infoMessage() {
        System.out.println("배송이 완료되었습니다.");
    }
}