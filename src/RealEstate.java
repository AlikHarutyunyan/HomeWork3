import java.util.Scanner;

public class RealEstate {
    private City[] cities;
    private Property[] properties;
    private User[] users;

    public RealEstate () {
        users = new User[] {
                new User("Michelle", "11458$", "0529516569", true),
                new User("Alik", "37777777_", "0533061346", false)
        };
    }

    public void createUser () {
        User newUser = new User();
        Scanner scanner = new Scanner(System.in);

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

        System.out.println(newUser);
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
