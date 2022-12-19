import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
        
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        RealEstate realEstate = new RealEstate();
        int userInput;

        System.out.println("Please choose the action number");
        do {
            System.out.println("1. Sign up \n" +
                                "2. Log in \n" +
                                "3. End program");
            userInput = scanner.nextInt();
        } while (!(userInput>=1 && userInput<=3));

        if (userInput==1) {
            realEstate.createUser();
        }
    }
}