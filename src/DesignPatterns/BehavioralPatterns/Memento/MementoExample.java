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