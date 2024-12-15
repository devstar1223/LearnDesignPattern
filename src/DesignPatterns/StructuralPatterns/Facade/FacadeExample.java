package StructuralPatterns.Facade;

public class FacadeExample {
    public static void main(String[] args) {
        HotelFront hotelFront = new HotelFront();
        hotelFront.checkin(); // 사용자는 이것 만으로도 모든 서브 시스템 동작 가능
    }
}

class HotelFront { // 파사드
    String manager = "Darko";

    // 서브 시스템들을 파사드에서 모두 관리한다.
    private final CleaningSystem cleaningSystem;
    private final RoomManagementSystem roomManagementSystem;
    private final CookingSystem cookingSystem;

    public HotelFront(){
        System.out.println("호텔에 어서오세요.");
        cleaningSystem = new CleaningSystem();
        roomManagementSystem = new RoomManagementSystem();
        cookingSystem = new CookingSystem();
    }
    public void checkin(){
        System.out.println(manager+"가 체크인을 환영합니다.");
        cleaningSystem.cleaningRoom();;
        roomManagementSystem.roomManaging();
        cookingSystem.cookingFood();
    }
}

class CleaningSystem {
    String manager = "Luke";

    public void cleaningRoom() {
        System.out.println(manager+"가 방을 청소합니다.");
    }
}

class RoomManagementSystem {
    String manager = "Alex";

    public void roomManaging() {
        System.out.println(manager+"가 객실을 관리합니다.");
    }
}

class CookingSystem {
    String manager = "Xiukai";

    public void cookingFood() {
        System.out.println(manager+"가 요리를 시작합니다.");
    }
}

