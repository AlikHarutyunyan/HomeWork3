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
            System.out.println("""
                    1. Sign up
                    2. Log in
                    3. End program""");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput==1) {
                realEstate.createUser();
                userInput = -1;
            }

            else if (userInput==2) {
                User currentUser = realEstate.login();

                if (currentUser != null) {
                    System.out.println("Login successful. Welcome: " + currentUser.getUserName());
                    System.out.println("Please choose the action number");
                    do {
                        System.out.println("""
                                1. Publish new property
                                2. Remove property publication
                                3. Show all the property publication
                                4. Show all your property publications
                                5. Search property by filters
                                6. Sign out and return to main menu""");
                        userInput = scanner.nextInt();
                        scanner.nextLine();

                        switch(userInput){
                            case 1 -> {
                                if(realEstate.postNewProperty(currentUser)){
                                    System.out.println("Publishment was successful!");
                                }else{
                                    System.out.println("Publishment has failed!");
                                }
                            }
                            case 2 -> realEstate.removeProperty(currentUser);
                            case 3 -> realEstate.printAllProperties();
                            case 4 -> realEstate.printProperties(currentUser);
                            case 5 -> {
                                Property[] filteredProperties = realEstate.search();
                                realEstate.printFilteredProperties(filteredProperties);
                            }
                        }
                    } while (userInput != 6);
                }else{
                    userInput = -1;
                }
            }

        } while (!(userInput>=1 && userInput<=3));
    }
}