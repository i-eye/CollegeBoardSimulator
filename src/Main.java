
import java.util.Scanner;
import java.lang.Math;

public class Main {
    static int annoyancePoints = 500;
    static int totalMoney = 1000000;
    static int students = 1000;
    static double difficulty = .1;
    static boolean taxEvasion = false;
    static int highScore = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //explains the game
        System.out.println("You are the CEO of college board");
        System.out.println("Take the money of the high school students");
        System.out.println("And don't lose all your money and/or students");
        System.out.println("Also try to annoy the students as much as possible");

        //loops the game if the play chooses too
        while(true){
            gameLoop(sc);
            System.out.println("Would you like to play again?(press 1 if you do, -1 if you don't) ");
            try{
                if(sc.nextInt() != 1){
                    System.out.println("You ended the game");
                    System.out.println("High school students everywhere thank you");
                    break;
                }
            }
            //insults the player if the inputs incorrectly
            catch(Exception e){
                System.out.println("You literally had a "+ e + " at the very end of the game");
                System.out.println("Shame on you");
                break;
            }

            //resets game stats before the game happens again
            annoyancePoints = 500;
            totalMoney = 1000000;
            students = 1000;
            difficulty = .1;
            taxEvasion = false;
        }

    }

    public static void gameLoop(Scanner sc){
        // Loop rounds while students and money are both greater than 0
        while(students > 0 && totalMoney > 0){
            //prints stats
            System.out.println("Money: "+totalMoney);
            System.out.println("Students: "+ students);
            System.out.println("Annoyance Points: "+ annoyancePoints);

            //randomly generates 3 options out of the 8 actions
            int answer = 0;
            double randomVal = Math.random();
            double[] gameSelection = {Math.random()*8,Math.random()*8, Math.random()*8};
            optionPrinter(randomVal, gameSelection);

            //makes sure input is valid(both it is an int, and it is one of the options generated above)
            answer = getAnswer(sc, answer, gameSelection);
            System.out.println("------------------------------------------");

            //Executes the action selected above
            callAction(answer, randomVal);

            //small pause to make sure the user notices the dialogue
            try {
                Thread.sleep(1050);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();

            //turn based changes
            //increases difficulty, graduates students, and taxes
            if (endOfRoundChanges()) break;
        }
        //tells why the game was lost, calculates high score, and displays score and high score
        endOfGameInfo();
    }

    private static void endOfGameInfo() {
        if(totalMoney <= 0){
            System.out.println("You ran out of money");
        }
        if(students <= 0){
            System.out.println("You became so unpopular you couldn't make students pay for any more tests");
        }
        if(annoyancePoints > highScore){
            highScore = annoyancePoints;
        }
        System.out.println("Your total points were "+ annoyancePoints);
        System.out.println("Your high score is "+ highScore);
    }

    private static boolean endOfRoundChanges() {
        difficulty += .15;

        System.out.println("Students are lost from graduation");
        students -= (int)(difficulty*25); // graduation
        if(taxEvasion){
            System.out.println("Chance of being caught evading taxes: "+ (difficulty*.005*100)+ "%");
            if(Math.random() < difficulty*.005){
                System.out.println("You got caught evading taxes by the IRS...");
                return true;
            }
        } else {
            totalMoney -= (.075*totalMoney); // taxes
            System.out.println("Taxes are taken out");
        }
        return false;
    }

    private static int getAnswer(Scanner sc, int answer, double[] gameSelection) {
        boolean needInput = true;

        while(needInput) {
            try {
                answer = sc.nextInt();
                if(answer == ((int) gameSelection[0]+1) || answer == ((int) gameSelection[1]+1) || answer == ((int) gameSelection[2]+1)){
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
        return answer;
    }

    private static void callAction(int answer, double randomVal) {
        switch (answer) {
            case -1 -> System.out.println("You don't get to do anything this turn");
            case 1 -> payColleges(randomVal);
            case 2 -> loginAgain();
            case 3 -> payForTest();
            case 4 -> {
                if (students >= 5000) {
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
    }

    private static void optionPrinter(double randomVal, double[] gameSelection) {
        for(double x: gameSelection) {
            System.out.print(((int)x + 1) + ": ");
            switch ((int) x + 1) {
                case 1 -> System.out.println("Pay Colleges(" + (int) (randomVal * 460000 * difficulty) + ")");
                case 2 -> System.out.println("Make the students all log in again");
                case 3 -> System.out.println("Make the students pay for the AP tests");
                //dinosauce is a special case that requires 5000 students
                //it will be the same as option 1 if that case isn't met
                case 4 -> {
                    if (students >= 5000) {
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
    }

    //College Board paying colleges to accept AP scores, and thus making more students take AP scores
    //I don't know if this actually happens, but possibly
    public static void payColleges(double money){
        System.out.println("You pay colleges money and they force more students to join");
        money *= 460000 * difficulty;
        System.out.println("Money lost: "+ (int)money);
        System.out.println("Students Gained: "+(int)(money / 125));
        students += money / 125;
        totalMoney -= money;
    }

    //College Board makes you log a lot
    public static void loginAgain(){
        System.out.println("Students are slightly annoyed by logging in again");
        System.out.println("Points gained: "+ (int)(students*difficulty));
        annoyancePoints += students*difficulty;
    }

    //You have to pay for AP tests
    public static void payForTest(){
        System.out.println("Students pay for the test, you get some nice annoyance and money");
        System.out.println("Money gained: "+ (students * 120));
        System.out.println("Points gained: "+ (students * 2));
        totalMoney += students * 120;
        annoyancePoints += students * 2;
    }

    //Dinosauce was probably college board making a reddit account to catch cheaters
    //It was really stupid
    public static void dinoSauce(){
        System.out.println("Dinosauce scourges reddit and many AP students get really annoyed");
        System.out.println("Students lost: "+(int)(students*.02*difficulty));
        System.out.println("Points gained: "+ (int)(students*difficulty*25));
        annoyancePoints += students * difficulty * 25;
        students -= students*.1*difficulty;
    }

    //This game has taxes, therefore it needs tax evasion too
    public static void taxEvasion(){
        System.out.println("You're tax evasion status is now "+ !taxEvasion);
        if(!taxEvasion){
            System.out.println("Beware the IRS");
        } else {
            System.out.println("The IRS is no longer be a threat");
        }
        taxEvasion = !taxEvasion;
    }

    //The SAT costs money
    public static void satForcing(){
        System.out.println("The students don't hate the SAT too much");
        System.out.println("But it's also cheaper");
        System.out.println("Money gained: "+(students*90));
        System.out.println("Points gained: "+(int)(students*difficulty*2.5));
        totalMoney += students * 90;
        annoyancePoints += students * difficulty * 2.5;
    }

    //Dena Legget was an AP Chemistry teacher that was in at least the 2021-2022 school year daily videos
    //I and some other people think she was annoying
    //However she is good at teaching, but I digress
    public static void denaLegget(double apChemStudents){
        System.out.println("Dena Legget annoys many AP Chemistry students");
        System.out.println("Points gained: "+(int)(students*apChemStudents*6*difficulty));
        annoyancePoints += students * apChemStudents * 6*difficulty;
    }

    //at home AP tests were really unpopular since they were put together badly
    public static void atHomeTest(){
        System.out.println("The at home test is annoying to many students, and it even makes some take IB classes instead");
        System.out.println("However, you still get money");
        System.out.println("Money gained: "+(students*120));
        System.out.println("Students lost: "+(int)(students*.01*difficulty));
        System.out.println("Points gained: "+(int)(students*5*difficulty));
        annoyancePoints += students * 5*difficulty;
        totalMoney += students * 120;
        students -= students*.01*difficulty;
    }
}