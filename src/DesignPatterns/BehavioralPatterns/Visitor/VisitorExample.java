package DesignPatterns.BehavioralPatterns.Visitor;

public class VisitorExample {
    public static void main(String[] args) {
        Animal giraffe = new Giraffe("나뭇잎");
        Animal lion = new Lion("고기");
        Animal penguin = new Penguin("새우");

        Visitor minsu = new FeedVisitor();
        giraffe.accept(minsu);
        lion.accept(minsu);
        penguin.accept(minsu);

        Visitor sujin = new HealthCheckVisitor();
        giraffe.accept(sujin);
        lion.accept(sujin);
        penguin.accept(sujin);
    }
}

abstract class Animal {
    protected String favoriteFood;

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public abstract void accept(Visitor visitor);
}

class Giraffe extends Animal {
    public Giraffe(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Lion extends Animal {
    public Lion(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Penguin extends Animal {
    public Penguin(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(Giraffe giraffe);
    void visit(Lion lion);
    void visit(Penguin penguin);
}

class FeedVisitor implements Visitor {
    @Override
    public void visit(Giraffe giraffe) {
        System.out.println("기린에게 " + giraffe.getFavoriteFood() + " 먹이기 완료.");
    }

    @Override
    public void visit(Lion lion) {
        System.out.println("사자에게 " + lion.getFavoriteFood() + " 먹이기 완료.");
    }

    @Override
    public void visit(Penguin penguin) {
        System.out.println("펭귄에게 " + penguin.getFavoriteFood() + " 먹이기 완료.");
    }
}

class HealthCheckVisitor implements Visitor {
    @Override
    public void visit(Giraffe giraffe) {
        System.out.println("기린의 목을 체크합니다.");
    }

    @Override
    public void visit(Lion lion) {
        System.out.println("사자의 발톱을 체크합니다.");
    }

    @Override
    public void visit(Penguin penguin) {
        System.out.println("펭귄의 부리를 체크합니다.");
    }
}