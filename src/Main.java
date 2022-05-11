
import java.util.Scanner;
import java.lang.Math;

public class Main {
    static int annoyancePoints = 500;
    static int totalMoney = 1000000;
    static int students = 1000;
    static double difficulty = .1;
    static boolean taxEvasion = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("You are the CEO of college board");
        System.out.println("Take the money of the high school students");
        System.out.println("And don't lose all your money and/or students");
        System.out.println("Also try to annoy the students as much as possible");
        while(true){
            gameLoop(sc);
            System.out.println("Would you like to play again?(press 1) ");
            if(sc.nextInt() != 1){
                System.out.println("You ended the game");
                System.out.println("High school students everywhere thank you");
                break;
            }
        }

    }

    public static void gameLoop(Scanner sc){
        while(students > 0 && totalMoney > 0){
            System.out.println("Money: "+totalMoney);
            System.out.println("Students: "+ students);
            System.out.println("Points: "+ annoyancePoints);
            int answer = 0;
            double randomVal = Math.random();
            double[] gameSelection = {Math.random()*8,Math.random()*8, Math.random()*8};
            for(double x: gameSelection) {
                System.out.print(((int)x + 1) + ": ");
                switch ((int) x + 1) {
                    case 1 -> System.out.println("Pay Colleges(" + (int) (randomVal * 460000 * difficulty) + ")");
                    case 2 -> System.out.println("Make the students all log in again");
                    case 3 -> System.out.println("Make the students pay for the AP tests");
                    case 4 -> {
                        if (students >= 3500) {
                            System.out.println("DINOSAUCE");
                        } else {
                            System.out.println("Pay Colleges(" + (int) (randomVal * 460000 * difficulty) + ")");
                        }
                    }
                    case 5 -> System.out.println("Toggle Tax Evasion(Currently "+taxEvasion+")");
                    case 6 -> System.out.println("Force the SAT on people");
                    case 7 -> System.out.println("Hire Dena Legget(for free)");
                    case 8 -> System.out.println("Make the kids take their AP test at home");
                }
            }
            boolean needInput = true;

            while(needInput) {
                try {
                    answer = sc.nextInt();
                    if(answer == ((int)gameSelection[0]+1) || answer == ((int)gameSelection[1]+1) || answer == ((int)gameSelection[2]+1)){
                        needInput = false;
                    } else {
                        System.out.println("Not an option, sorry");
                        System.out.println("Please enter a valid number");
                    }

                } catch (Exception e) {
                    System.out.println("CollegeBoard, are you really that bad at knowing what a number is?");
                    System.out.println("Ironic considering the difficulty of your tests");
                    System.out.println("Please enter a valid number");
                    sc.nextLine();
                }
            }
            System.out.println("------------------------------------------");

            switch (answer) {
                case -1 -> System.out.println("You don't get to do anything this turn");
                case 1 -> payColleges(randomVal);
                case 2 -> loginAgain();
                case 3 -> payForTest();
                case 4 -> {
                    if (students >= 3500) {
                        dinoSauce();
                    } else {
                        payColleges(randomVal);
                    }
                }
                case 5 -> taxEvasion();
                case 6 -> satForcing();
                case 7 -> denaLegget(randomVal);
                case 8 -> atHomeTest();
            }
            System.out.println();

            difficulty += .15;

            System.out.println("Students lost from graduation: "+ (int)(difficulty*5));
            students -= (int)(difficulty*5); // graduation
            if(taxEvasion){
                System.out.println("Chance of being caught evading taxes: "+ (difficulty*.01*100)+ "%");
                if(Math.random() < difficulty*.01){
                    System.out.println("You got caught evading taxes by the IRS...");
                    break;
                }
            } else {
                totalMoney -= (.075*totalMoney); // taxes
                System.out.println("Taxes: "+(int)(.075*totalMoney));
            }

        }
        if(totalMoney <= 0){
            System.out.println("You ran out of money");
        }
        if(students <= 0){
            System.out.println("You became so unpopular you couldn't make students pay for any more tests");
        }
        System.out.println("Your total points were "+ annoyancePoints);
    }

    public static void payColleges(double money){
        System.out.println("You pay colleges money and they force more students to join");
        money *= 460000 * difficulty;
        System.out.println("Money lost: "+ (int)money);
        System.out.println("Students Gained: "+(int)(money / 125));
        students += money / 125;
        totalMoney -= money;
    }
    public static void loginAgain(){
        System.out.println("Students are slightly annoyed by logging in again");
        System.out.println("Points gained: "+ (int)(students*difficulty));
        annoyancePoints += students*difficulty;
    }
    public static void payForTest(){
        System.out.println("Students pay for the test, you get some nice annoyance and money");
        System.out.println("Money gained: "+ (students * 120));
        System.out.println("Points gained: "+ (students * 5));
        totalMoney += students * 120;
        annoyancePoints += students * 5;
    }
    public static void dinoSauce(){
        System.out.println("Dinosauce scourges reddit and many AP students get really annoyed");
        System.out.println("Students lost: "+(int)(students*.02*difficulty));
        System.out.println("Points gained: "+ (int)(students*difficulty*100));
        annoyancePoints += students * difficulty * 100;
        students -= students*.1*difficulty;
    }
    public static void taxEvasion(){
        System.out.println("You're tax evasion status is now "+ !taxEvasion);
        if(!taxEvasion){
            System.out.println("Beware the IRS");
        } else {
            System.out.println("The IRS is no longer be a threat");
        }
        taxEvasion = !taxEvasion;
    }
    public static void satForcing(){
        System.out.println("The students don't hate the SAT too much");
        System.out.println("But it's also cheaper");
        System.out.println("Money gained: "+(students*90));
        System.out.println("Points gained: "+(students*difficulty*2.5));
        totalMoney += students * 90;
        annoyancePoints += students * difficulty * 2.5;
    }
    public static void denaLegget(double apChemStudents){
        System.out.println("Dena Legget annoys many AP Chemistry students");
        System.out.println("Points gained: "+(int)(students*apChemStudents*6*difficulty));
        annoyancePoints += students * apChemStudents * 6*difficulty;
    }
    public static void atHomeTest(){
        System.out.println("The at home test is annoying to many students, and it even makes some take IB classes instead");
        System.out.println("However, you still get money");
        System.out.println("Money gained: "+(students*120));
        System.out.println("Students lost: "+(int)(students*.01*difficulty));
        System.out.println("Points gained: "+(students*20));
        annoyancePoints += students* 20;
        totalMoney += students * 120;
        students -= students*.01*difficulty;
    }
}