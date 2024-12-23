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