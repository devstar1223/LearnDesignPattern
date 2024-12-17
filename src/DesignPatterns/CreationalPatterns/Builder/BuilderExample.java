package DesignPatterns.CreationalPatterns.Builder;

import java.util.*;

public class BuilderExample {
    public static void main(String[] args) {
        Sandwich mySandwich = new Sandwich.Builder()
                .menu("로스트 치킨")
                .bread("플랫 브래드")
                .cheese("아메리칸 치즈")
                .excludeVegetables("오이,토마토")
                .sauce("머스타드, 소금, 후추")
                .build();

        mySandwich.contains();
    }
}

class Sandwich {
    private final String menu;
    private final String bread;
    private final String cheese;
    private final String vegetable;
    private final String sauce;

    private Sandwich(Builder builder) {
        this.menu = builder.menu;
        this.bread = builder.bread;
        this.cheese = builder.cheese;
        this.vegetable = builder.vegetable;
        this.sauce = builder.sauce;
    }

    public void contains() {
        System.out.println("내 샌드위치의 구성을 보았다.");
        System.out.println("메뉴 : " + menu);
        System.out.println("빵 : " + bread);
        System.out.println("치즈 : " + cheese);
        System.out.println("야채 : " + vegetable);
        System.out.println("소스 : " + sauce);
    }

    public static class Builder {
        private String menu;
        private String bread;
        private String cheese;
        private String vegetable;
        private String sauce;

        private final List<String> vegetableList = new ArrayList<>(Arrays.asList("양상추", "토마토", "오이", "피클", "올리브"));

        public Builder menu(String menu) {
            this.menu = menu;
            return this;
        }

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder excludeVegetables(String excludedVegetable) {
            List<String> requestVegetables = new ArrayList<>(vegetableList);
            String[] excludedArray = excludedVegetable.split(",");
            for (String exclude : excludedArray) {
                requestVegetables.remove(exclude.trim());
            }
            this.vegetable = String.join(", ", requestVegetables);
            return this;
        }

        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Sandwich build() {
            return new Sandwich(this);
        }
    }
}
