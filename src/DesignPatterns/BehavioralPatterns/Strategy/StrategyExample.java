package BehavioralPatterns.Strategy;

public class StrategyExample {
    public static void main(String[] args) {
        Gun gun = new Gun();

        gun.setBullet(new BlankBullet());
        gun.fire();

        gun.setBullet(new RealBullet());
        gun.fire();

        gun.setBullet(new GasBullet());
        gun.fire();
    }
}

class Gun {

    Bullet bullet;

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
        System.out.println("장전 완료.");
    }

    public void fire() {
        System.out.println("격발!");
        bullet.action();
    }
}

interface Bullet{
    void action();
}

class BlankBullet implements Bullet{

    @Override
    public void action() {
        System.out.println("공포탄 - 상태이상 부여");
    }
}
class GasBullet implements Bullet{

    @Override
    public void action() {
        System.out.println("가스탄 - 지속 데미지 10");
    }
}

class RealBullet implements Bullet{

    @Override
    public void action() {
        System.out.println("실탄 - 단발 데미지 100");
    }
}