package DesignPatterns.BehavioralPatterns.Mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorExample {
    public static void main(String[] args) {
        ControlTower incheonControlTower = new IncheonControlTower();
        Airplane jeju123 = new JejuairAirplane(incheonControlTower, "제주항공 비행기");
        Airplane asiana456 = new AsianaAirlinesAirplane(incheonControlTower, "아시아나 비행기");

        jeju123.requestTakeoff("1번 비행로");
        asiana456.requestTakeoff("1번 비행로");
        asiana456.requestTakeoff("2번 비행로");
    }
}

interface ControlTower{
    boolean canTakeoff(String flightPath);
}

class IncheonControlTower implements ControlTower{

    List<String> unavailableFlightPathList = new ArrayList<>();

    @Override
    public boolean canTakeoff(String flightPath) {
        if(unavailableFlightPathList.contains(flightPath)){
            System.out.println("관제탑 : 사용중인 비행경로. 다른 곳을 요청하십시오.");
            return false;
        }
        System.out.println("관제탑 : 이륙 허가.");
        unavailableFlightPathList.add(flightPath);
        return true;
    }

}

abstract class Airplane{
    protected ControlTower controlTower;
    private String name;

    public Airplane(ControlTower controlTower,String name){
        this.controlTower = controlTower;
        this.name = name;
    }

    public void requestTakeoff(String flightPath){
        System.out.println(name+" : 이륙을 요청합니다. " + flightPath);
        if(controlTower.canTakeoff(flightPath)){
            System.out.println(name+" : 허가 완료. 이륙합니다.");
        }
        else{
            System.out.println(name+" : 확인했습니다.");
        }
    }
}

class JejuairAirplane extends Airplane{

    public JejuairAirplane(ControlTower controlTower,String name) {
        super(controlTower,name);
    }
}

class AsianaAirlinesAirplane extends Airplane{

    public AsianaAirlinesAirplane(ControlTower controlTower,String name) {
        super(controlTower,name);
    }
}