import java.util.Scanner;

public class RealEstate {
    private City[] cities;
    private Property[] properties;
    private User[] users;

    public RealEstate () {
        users = new User[] {
                new User("Michelle", "11458_", "0529516569", true),
                new User("Alik", "37777777_", "0533061346", false)
        };
    }

    public void createUser () {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean isAvailable;

        do {
            isAvailable = true;
            System.out.println("Enter the user name:");
            userInput = scanner.nextLine();

            for (int i = 0; i < users.length; i++) {
                if (userInput.equals(users[i].getUserName())) {
                    System.out.println("The user name is already taken");
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                System.out.println("The user name is available");
            }

        } while (!isAvailable);


        if (isAvailable) {
            String userPassword;

            System.out.println("The password must contain the following elements: \n" +
                    "- At least 1 character must be a digit \n" +
                    "- Password must be minimum 5 characters long \n" +
                    "- At least 1 of this special characters: % _ $");
            do {
                System.out.println("Enter your password: ");
                userPassword = scanner.nextLine();
            } while (!checkPassword(userPassword));

            System.out.println("success");
        }
    }

    private boolean checkPassword(String password) {
        boolean result = false;
        if (password.length()>=5) {
            if (password.contains("$") || password.contains("_") || password.contains("%")) {
                if (containsNumber(password)) {
                    result = true;
                }
            }
        }
    return result;
    }

    private boolean containsNumber(String string) {
        boolean isNumberCheck = false;
        for (int i = 0; i < 10; i++) {
            if (string.contains("" + i)) {
                isNumberCheck = true;
                break;
            }
        }
        return isNumberCheck;
    }
}
