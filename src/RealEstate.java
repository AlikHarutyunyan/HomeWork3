import java.util.Arrays;
import java.util.Scanner;

public class RealEstate {
    Scanner scanner = new Scanner(System.in);
    private City[] cities;
    private Property[] properties;
    private User[] users;


    public RealEstate () {
        users = new User[] {
                new User("Michelle", "11458$", "0529516569", true),
                new User("Alik", "37777777_", "0533061346", false)
        };

        cities = new City[] {
                new City("Ashkelon", "south", new String[] {"dovev", "acalanit"}),
                new City("Ashdod", "south", new String[] {"mango", "rambam"}),
                new City("Tel aviv", "central", new String[] {"alenbi", "dizingof"}),
                new City("Rishon Letsiyon", "central", new String[] {"hadad", "kaplan"}),
                new City("Beer sheva", "negev", new String[] {"nesher", "gilad"}),
                new City("Dimona", "negev", new String[] {"agefen", "ayona"}),
                new City("Netanya", "sharon", new String[] {"holand", "kedma"}),
                new City("Kfar Yona", "sharon", new String[] {"egoz", "alfasi"}),
                new City("Tiberias", "north", new String[] {"golda", "golani"}),
                new City("Haifa", "north", new String[] {"sirkin", "arad"}),
        };
    }

    public void createUser () {
        User newUser = new User();
        String userName;
        do {
            System.out.println("Enter the user name:");
            userName = scanner.nextLine();

            if(checkIfAvailable(userName)){
                newUser.setUserName(userName);
            }
        } while (newUser.getUserName() == null);


        String userPassword;
        System.out.println("The password must contain the following elements: \n" +
                "- At least 1 character must be a digit \n" +
                "- Password must be minimum 5 characters long \n" +
                "- At least 1 of this special characters: % _ $");
        do {
            System.out.println("Enter your password: ");
            userPassword = scanner.nextLine();
            newUser.setPassword(userPassword);
        } while (newUser.getPassword() == null);


        String phoneNumber;
        do {
            System.out.println("Enter your phone number");
            phoneNumber = scanner.nextLine();
            newUser.setPhoneNumber(phoneNumber);
        } while (newUser.getPhoneNumber() == null);


        do {
            System.out.println("Are you a broker? Answer yes/no");
            String userAnswer = scanner.nextLine();
            userAnswer = userAnswer.toLowerCase().trim();

            if(checkAnswer(userAnswer)){
                switch(userAnswer){
                    case "yes" -> newUser.setBroker(true);
                    case "no" -> newUser.setBroker(false);
                }
            }
        } while (newUser.getBroker() == null);

        users = addUser(users,newUser);
        System.out.println("Sign up is complete");
    }

    public User login() {

        User currentUser = null;

        System.out.println("Enter your user name");
        String userLogin = scanner.nextLine();
        System.out.println("Enter your password");
        String userPassword = scanner.nextLine();

        int userIndex = checkIfUserExists(userLogin, userPassword);

        if (userIndex != -1) {
            currentUser = users[userIndex];
        }

        return currentUser;
    }

    public boolean postNewProperty (User user) {
        final int PUBLISHMENT_QUANTITY;
        boolean result = false;
        if (user.getBroker()) {
            PUBLISHMENT_QUANTITY = 5;
        }

        else {
            PUBLISHMENT_QUANTITY = 3;
        }

        int countUserPublishment = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getUser().getUserName().equals(user.getUserName())) {
                countUserPublishment++;
            }
        }

        if (countUserPublishment>=PUBLISHMENT_QUANTITY) {
            System.out.println("You have reached the maximum amount of publishments");
            result = false;
        }

        return result;
    }

    private int checkIfUserExists (String userLogin, String userPassword) {
        int result = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUserName().equals(userLogin)) {
                if (users[i].getPassword().equals(userPassword)) {
                    result = i;
                    break;
                }
            }
        }

        if (result == -1) {
            System.out.println("The username or the password is incorrect");
        }
        return result;
    }
    private User[] addUser (User[] users, User newUser) {
        User[] tempUsers = new User[users.length + 1];

        for (int i = 0; i < users.length; i++) {
            tempUsers[i] = users[i];
        }

        tempUsers[users.length] = newUser;

        return tempUsers;
    }


    private boolean checkAnswer(String userAnswer){
        boolean result = false;
        if(userAnswer.equals("yes") || userAnswer.equals("no")){
            result = true;
        }else{
            System.out.println("Please answer correctly");
        }
        return result;
    }

    private boolean checkIfAvailable(String userName){
        boolean isAvailable = true;
        for (int i = 0; i < users.length; i++) {
            if (userName.equals(users[i].getUserName())) {
                System.out.println("The user name is already taken");
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }

}
