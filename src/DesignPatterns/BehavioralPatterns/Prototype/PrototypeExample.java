package DesignPatterns.BehavioralPatterns.Prototype;

public class PrototypeExample {
    public static void main(String[] args) {
        Robot firstRobot = new Robot(100,6);
        firstRobot.setName("태초의 로봇");
        firstRobot.status();

        Robot secondRobot = (Robot) firstRobot.clone();
        secondRobot.setName("세컨드");
        secondRobot.status();

        Robot thirdRobot = (Robot) firstRobot.clone();
        thirdRobot.setName("서드");
        thirdRobot.status();

    }
}

interface RobotPrototype{
    RobotPrototype clone();
}

class Robot implements RobotPrototype{

    private int speed;
    private int strength;
    private String name;

    @Override
    public RobotPrototype clone() {
        return new Robot(this.speed, this.strength);
    }


    public Robot(int speed,int strength){
        this.speed = speed;
        this.strength = strength;
    }

    public void setName(String name){
        this.name = name;
    }

    public void status(){
        System.out.println("--- 로봇 정보 ---");
        System.out.println("이름 : "+name);
        System.out.println("속도 : "+speed);
        System.out.println("근력 : "+strength+"\n");
    }

}
