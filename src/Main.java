
import java.util.Scanner;
import java.lang.Math;

public class Main {
    static long annoyancePoints = 500;
    static long totalMoney = 1000000;
    static int students = 1000;
    static double difficulty = .1;
    static boolean taxEvasion = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        gameLoop(sc);
    }

    public static void gameLoop(Scanner sc){
        while(students > 0 && totalMoney > 0){
            System.out.println("Money: "+totalMoney);
            System.out.println("Students: "+ students);
            System.out.println("Points: "+ annoyancePoints);
            int answer;
            double randomVal = Math.random();
            double[] gameSelection = {Math.random()*8,Math.random()*8, Math.random()*8};
            for(double x: gameSelection) {
                System.out.print(((int)x + 1) + ": ");
                switch ((int) x + 1) {
                    case 1 -> System.out.println("Pay Colleges(" + (int) (randomVal * 230000 * difficulty) + ")");
                    case 2 -> System.out.println("Make the students all log in again");
                    case 3 -> System.out.println("Make the students pay for the test");
                    case 4 -> {
                        if (students >= 2500) {
                            System.out.println("DINOSAUCE");
                        } else {
                            System.out.println("Pay Colleges");
                        }
                    }
                    case 5 -> System.out.println("Evade Taxes(or stop evading taxes");
                    case 6 -> System.out.println("Force the SAT on people");
                    case 7 -> System.out.println("Hire Dena Legget(annoying teacher)");
                    case 8 -> System.out.println("Make the kids take their test at home(with technical difficulties");
                }
            }

            try{
                answer = sc.nextInt();
            } catch(Exception e){
                System.out.println("CollegeBoard, are you really that bad at knowing what a number is?");
                System.out.println("Ironic considering the difficulty of your tests");
                answer = -1;
            }

            switch (answer) {
                case -1 -> System.out.println("You don't get to do anything this turn");
                case 1 -> payColleges(randomVal);
                case 2 -> loginAgain();
                case 3 -> payForTest();
                case 4 -> {
                    if (students >= 2500) {
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



            difficulty += .05;
            students -= difficulty*5; // graduation
            if(taxEvasion){
                if(Math.random() < difficulty*.01){
                    System.out.println("You got caught evading taxes...");
                    break;
                }
            } else {
                totalMoney -= difficulty*.1*totalMoney; // taxes
            }

        }
    }

    public static void payColleges(double money){
        money *= 230000;
        students += money / (1000 * difficulty);
        totalMoney -= money;
    }
    public static void loginAgain(){
        annoyancePoints += students;
        students -= (students * .01 * difficulty);
    }
    public static void payForTest(){
        totalMoney += students * 60L;
        students -= 5*difficulty;
        annoyancePoints += students * 5L;
    }
    public static void dinoSauce(){
        annoyancePoints += students * difficulty * 10;
        students -= 50*difficulty;
    }
    public static void taxEvasion(){
        taxEvasion = !taxEvasion;
    }
    public static void satForcing(){
        totalMoney += students * 30L;
        annoyancePoints += students * difficulty * 2.5;
    }
    public static void denaLegget(double apChemStudents){
        annoyancePoints += students * apChemStudents * 6;
    }
    public static void atHomeTest(){
        annoyancePoints += students* 20L;
        totalMoney += students * 60L;
        students -= 15*difficulty;
    }

}