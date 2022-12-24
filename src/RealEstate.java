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

        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getUser().getUserName().equals(user.getUserName())) {
                    countUserPublishment++;
                }
            }
        }

        if (countUserPublishment>=PUBLISHMENT_QUANTITY) {
            System.out.println("You have reached the maximum amount of publishments");
            result = false;
        }

        else {
            Property newProperty = new Property();
            String cityName;
            for (int i = 0; i < cities.length; i++) {
                int num = i + 1;
                System.out.println(num + ". " + cities[i].getCityName());
            }

            System.out.println("Enter the name of the city");
            cityName = scanner.nextLine();
            cityName = cityName.toLowerCase().trim();

            int cityIndex = checkIfCityExists(cityName);

            if (cityIndex == -1) {
                System.out.println("The city you wrote doesnt exist");
                result = false;
            }

            else {
                newProperty.setCityName(cityName);
                String streetName;
                for (int i = 0; i < cities[cityIndex].getStreets().length; i++) {
                    System.out.println(cities[cityIndex].getStreets()[i]);
                }

                System.out.println("Enter the name of the street");
                streetName = scanner.nextLine();

                if (!checkIfStreetExists(streetName, cityIndex)) {
                    System.out.println("The street doesnt exist");
                    result = false;
                }

                else {
                    newProperty.setStreet(streetName);
                    int typeOfProperty;

                    System.out.println("Enter the number of property type \n" +
                            "1. Regular apartment in apartment building\n" +
                            "2. penthouse in apartment building\n" +
                            "3. land house");
                    typeOfProperty = scanner.nextInt();

                    newProperty.setType(typeOfProperty);

                    if (newProperty.getType() != typeOfProperty) {
                        System.out.println("Your input was not correct");
                    }

                    else {
                        if (newProperty.getType() == 1 || newProperty.getType() == 2) {
                            int floorNumber;
                            System.out.println("Enter the floor number");
                            floorNumber = scanner.nextInt();
                            newProperty.setFloorNumber(floorNumber);
                        }

                        int numberOfRooms;
                        System.out.println("Enter the number of rooms");
                        numberOfRooms = scanner.nextInt();
                        newProperty.setNumberOfRooms(numberOfRooms);

                        int houseNumber;
                        System.out.println("Enter the house number");
                        houseNumber = scanner.nextInt();
                        newProperty.setHouseNumber(houseNumber);

                        int forRent;
                        System.out.println("Choose one of the option bellow: \n" +
                                "1. property for rent \n" +
                                "2. property for sale");
                        forRent = scanner.nextInt();
                        newProperty.setForRent(forRent);

                        if (newProperty.isForRent() == null) {
                            System.out.println("You chose invalid option");
                        }

                        else {
                            int propertyPrice;
                            System.out.println("Enter the price of the property");
                            propertyPrice = scanner.nextInt();
                            newProperty.setPrice(propertyPrice);
                            result = true;
                        }
                    }
                }
            }

            if (result) {
                properties = addProperty(properties, newProperty);

                System.out.println("Publishment was successful");
                System.out.println(properties[0]);
            }

            else {
                System.out.println("Publishment has failed");
            }
        }
        return result;
    }


    private boolean checkIfStreetExists (String streetName, int cityIndex) {
        boolean streetExists = false;

        for (int i = 0; i < cities[cityIndex].getStreets().length; i++) {
            if (cities[cityIndex].getStreets()[i].equals(streetName)) {
                streetExists = true;
                break;
            }
        }
        return streetExists;
    }

    private int checkIfCityExists (String cityName) {
        int indexOfCity = -1;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getCityName().toLowerCase().equals(cityName)) {
                indexOfCity = i;
                break;
            }
        }
        return indexOfCity;
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

    private Property[] addProperty (Property[] properties, Property newProperty) {
        int arrLength;

        if (properties == null) {
            arrLength = 0;
        }

        else {
            arrLength = properties.length;
        }

        Property[] tempProperties = new Property[arrLength + 1];

        for (int i = 0; i < arrLength; i++) {
            tempProperties[i] = properties[i];
        }

        tempProperties[arrLength] = newProperty;
        return tempProperties;
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
