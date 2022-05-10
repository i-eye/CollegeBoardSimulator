
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
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
            double randomVal = Math.random();
            double[] gameSelection = {Math.random()*8,Math.random()*8, Math.random()*8};
            for(double x: gameSelection){
                switch ((int)x){
                    case 1-> System.out.println("Pay Colleges("+(int)(randomVal*230000*difficulty)+")");
                    case 2-> System.out.println("Make the students all log in again");
                    case 3-> System.out.println();
                }
            }
            sc.nextLine();


            difficulty += .05;
            students -= difficulty*5; // graduation
            if(taxEvasion){
                if(Math.random() < difficulty*.01){
                    System.out.println("You got caught evading taxes...");
                    break;
                }
            } else {
                totalMoney -= difficulty*2500; // taxes
            }

        }
    }

    public void payColleges(int money){
        students += money / (1000 * difficulty);
        totalMoney -= money;
    }
    public void loginAgain(){
        annoyancePoints += students;
        students -= (students * .01 * difficulty);
    }
    public void payForTest(){
        totalMoney += students * 60L;
        students -= 5*difficulty;
        annoyancePoints += students * 5L;
    }
    public void dinoSauce(){
        annoyancePoints += students * difficulty * 10;
        students -= 50*difficulty;
    }
    public void taxEvasion(){
        taxEvasion = !taxEvasion;
    }
    public void satForcing(){
        totalMoney += students * 30L;
        annoyancePoints += students * difficulty * 2.5;
    }
    public void denaLegget(double apChemStudents){
        annoyancePoints += students * apChemStudents * 6;
    }
    public void atHomeTest(){
        annoyancePoints += students* 20L;
        totalMoney += students * 60L;
        students -= 15*difficulty;
    }

}