package com.example.demo.Arraylist;

import java.util.ArrayList;
import java.util.List;

public class testArrayList {
    public static void main(String[] args) {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 2;i < 10 ; i++){
            Hero hero = new Hero();
            hero.setName("hero"+i);
            heroes.add(hero);
        }
        Hero hero1 = new Hero("hero1");

        for (int i = 0; i < heroes.size(); i++){
            if (hero1.getName().equals(heroes.get(i).getName())){
                System.out.println("找到name是hero1的对象");
                break;
            }
        }

    }
}
