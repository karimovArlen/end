package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 1200;
    public static int bossDamage = 60;
    public static String bossDefence = "";
    public static int[] heroesHealth = {400, 150, 200,250,250,130,250};
    public static int[] heroesDamage = {30, 20, 10,20,20,16,0};
    public static String[] heroesAttackType = {
            "Warrior", "Magical", "Kinetic","Thor","Berserk","Lucky","Medic"};

    public static int healerHealing = 50;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        if (bossHealth > 0) {
            chooseBossDefence();
            bossHits();
        }
        berserkActions();
        heroesHit();
        thorsActions();
        luckysActions();
        heals();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefence);
    }
    public static void heals(){
        for (int i = 0; i < 5 ; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + healerHealing;
            }
        }

    }
    public static void thorsActions(){
        Random random = new Random();
        boolean r1 = random.nextBoolean();
        if (heroesHealth[3] > 0){
            if (!(r1)) {
                bossDamage = 60;
            } else if (r1) {
                bossDamage = 0;
                System.out.println("Тор оглушил босса");
            }
        }
    }
    public static void luckysActions(){
        Random random = new Random();
        boolean r1 = random.nextBoolean();
        if (heroesHealth[5] >0 ){
            if (!(r1)){
                heroesHealth[5] = heroesHealth[5] + bossDamage;
                if ( heroesHealth[5] > 130){
                    heroesHealth[5] = heroesHealth[5] - bossDamage;
                }
                System.out.println("Lucky укланился");
            } else if (r1){
                heroesHealth[5] = heroesHealth[5];
            }
        }


    }
    public static void berserkActions(){
        Random randoms = new Random();
        int ranDamage = randoms.nextInt(15)+1;
        Random random = new Random();
        int ran = random.nextInt(3)+1;

        if (heroesHealth[4] >0 && bossHealth > 0){
            switch (ran){
                case 1:
                    heroesDamage[4] = (heroesDamage[4] + bossDamage) - ranDamage;
                    System.out.println("Берсерк наносит крит.урон");
                    System.out.println("потерии при увелечении удара Берсерка- "+ "[ " + ranDamage + " ]" );

                    break;
                case 2:
                    bossDamage = 60;
                    break;
                case 3:
                    bossDamage = 60;
                    break;
            }
        }

    }
   /* public static void berserkBum(){
        Random random = new Random();
        int ran = random.nextInt(4)+1;
        switch (ran){
            case 1:
                bossDamage = 20;
                bossHealth = (heroesDamage[5]  + bossDamage) - bossHealth;
                System.out.println("Берсерк нанес крит.урон");
                break;
            case 2:
                bossDamage = 60;
                break;
                case 3:
                bossDamage = 60;
                break;
                case 4:
                bossDamage = 60;
                break;
        }
    }*/

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(
                            "Critical Damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
                heroesDamage[4] = 20;
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }

    }

    public static void printStatistics() {
        System.out.println("++++++++++++++");
        System.out.println("Boss health: " + bossHealth + " ["
                + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i] + " ["
                    + heroesDamage[i] + "]");
        }
        System.out.println("++++++++++++++");

    }


}
