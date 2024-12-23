>🎮 **객체의 상태를 캡슐화해 외부에서 접근하지 못하게 하고, 해당 상태를 복원할 수 있도록 돕는 패턴**

---

# 설명

- 객체 내부의 상태를 저장하고, 특정 시점으로 돌릴수 있는 기능을 제공한다.
- 객체의 상태 변경시 복구(Undo) 기능을 구현할 수 있다.

### 구성 요소

- 오리진 (Originator)
    - 저장하려는 데이터를 관리
    - 데이터의 저장 및 복원 기능을 제공하는 주체
- 메멘토 (Memento)
    - 오리진의 상태를 캡슐화하여 저장하는 객체
    - 상태 정보는 불변으로 설계되어, 외부에서 상태 정보 수정이나 확인 불가
- 케어테이커 (Caretaker)
    - 메멘토 객체를 관리하는 역할
    - 여러개의 메멘토를 저장하거나 필요할 때 특정 메멘토를 반환
    - 메멘토의 내부 상태를 알지 못하며, “저장”과 “요청” 만을 처리

## 특징

- 상태 복원
    - 특정 시점으로 객체 되돌리기 가능
- 캡슐화
    - 메멘토 객체는 캡슐화되어, 외부에서 접근 불가
- 분리된 책임
    - 원본 객체는 상태를 저장하고, 케어테이커는 메멘토를 관리

## 실생활의 예제

- 게임을 하다가, 저장을 하면 진행상황이 저장된다.
    - 저장된 파일은 어떤 방식으로도 수정할 수 없다.
- 세이브 불러오기를 하면, 진행 상황을 그대로 불러온다.

---

# 코드

```java
package DesignPatterns.BehavioralPatterns.Memento;

public class MementoExample {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        game.lookStatus();
        game.levelUp();
        game.levelUp();
        game.warp("하이랄 대지");
        game.lookStatus();
        game.saveGame(1);

        game.start();
        game.lookStatus();
        game.loadGame(1);
        game.lookStatus();
    }
}

// Memento
class SaveFile{
    private final String playerLocation;
    private final int playerLevel;
    private final int playerHp;

    public SaveFile(String playerLocation, int playerLevel, int playerHp) {
        this.playerLocation = playerLocation;
        this.playerLevel = playerLevel;
        this.playerHp = playerHp;
    }

    public String getPlayerLocation() {
        return playerLocation;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getPlayerHp() {
        return playerHp;
    }
}

// Caretaker
class SaveLoader{

    public SaveFile[] saveFileSlot = new SaveFile[3];

    public void saveFile(int saveSlot,SaveFile saveFile){
        saveFileSlot[saveSlot] = saveFile;
        System.out.println("System : " + saveSlot+"번 슬롯에 저장 완료!");
    }

    public SaveFile loadSaveFile(int saveSlot){
        System.out.println("System : " + saveSlot+"번 세이브 불러오기중...");
        return saveFileSlot[saveSlot];
    }
}

// Originator
class Game{
    private String playerLocation;
    private int playerLevel;
    private int playerHp;
    private final SaveLoader saveLoader;

    public Game() {
        this.saveLoader = new SaveLoader();
    }

    public void start() {
        this.playerLocation = "시작의 마을";
        this.playerLevel = 1;
        this.playerHp = 50;
    }

    public void warp(String area) {
        System.out.println("☆ " + area + "이동중...");
        this.playerLocation = area;
    }

    public void levelUp(){
        System.out.println("★ 레벨업!");
        playerLevel++;
        playerHp += 50;
    }

    public void lookStatus(){
        System.out.println("------현재 상태------");
        System.out.println("현재 지역 : "+playerLocation);
        System.out.println("현재 레벨 : "+playerLevel);
        System.out.println("현재 HP : "+playerHp);
        System.out.println("--------------------");
    }

    public void saveGame(int saveSlot){
        System.out.println("System : 현재 진행 상황을 " + saveSlot + "번 슬롯에 저장할게요!");
        SaveFile savefile = new SaveFile(this.playerLocation,this.playerLevel,this.playerHp);
        saveLoader.saveFile(saveSlot,savefile);
    }

    public void loadGame(int saveSlot){
        System.out.println("System : " + saveSlot+"번 세이브 슬롯을 불러올게요!");
        SaveFile savefile = saveLoader.loadSaveFile(saveSlot);
        this.playerLocation = savefile.getPlayerLocation();
        this.playerLevel = savefile.getPlayerLevel();
        this.playerHp = savefile.getPlayerHp();
        System.out.println("System : 불러오기 완료!");
    }
}
```

- `SaveFile`
    - 메멘토
    - 게임의 상태를 **불변**으로 저장한다.
    - 게임의 상태가 캡슐화 되어있어, `getter` 로만 가져올 수 있다.
- `SaveLoader`
    - 케어테이커
    - 3개의 슬롯을 통해서 게임의 저장파일(메멘토)을 `Game` 에게 건네받아 저장하거나, `Game` 의 요청에 따라 삭제한다.
- `Game`
    - 오리진
    - 게임의 상태를 관리하면서 저장/복원 작업을 수행한다.
    - `start`, `warp`, `levelUp` 을 통해 게임의 상태를 관리한다.
    - `saveGame` , `loadGame` 을 통해 메멘토를 저장하거나 불러오고, 불러온 `SaveFile` 을 통해 게임의 상태를 갱신한다.

---

# 장/단점

## 장점

- 복원성이 뛰어남
    - 객체의 상태를 쉽게 저장하고 복원
    - 상태 손실 없이 자유롭게 변경과 복원 반복 가능
- 우수한 캡슐화
    - 객체의 내부 상태를 외부에 노출하지 않고 보호

## 단점

- 메모리 사용량 증가
    - 메멘토 객체가 많을 수록 메모리 사용량이 증가함
    - 상태를 캡슐화하여 저장하기 때문에, 많은 상태를 저장할수록 메모리 부담이 커짐.

---

# 사용 사례

- 텍스트 편집기
    - Undo/Redo 기능 지원
- 게임 (예시 코드)
- 소프트웨어 개발
    - 상태 스냅샷 저장
    - 원하는 시점에 복원