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
                new City("Ashkelon", "south", new String[] {"Dovev", "Acalanit"}),
                new City("Ashdod", "south", new String[] {"Mango", "Rambam"}),
                new City("Tel aviv", "central", new String[] {"Alenbi", "Dizingof"}),
                new City("Rishon Letsiyon", "central", new String[] {"Hadad", "Kaplan"}),
                new City("Beer sheva", "negev", new String[] {"Nesher", "Gilad"}),
                new City("Dimona", "negev", new String[] {"Agefen", "Ayona"}),
                new City("Netanya", "sharon", new String[] {"Holand", "Kedma"}),
                new City("Kfar Yona", "sharon", new String[] {"Egoz", "Alfasi"}),
                new City("Tiberias", "north", new String[] {"Golda", "Golani"}),
                new City("Haifa", "north", new String[] {"Sirkin", "Arad"}),
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
            }

            else {
                newProperty.setCityName(cities[cityIndex].getCityName());
                String streetName;
                for (int i = 0; i < cities[cityIndex].getStreets().length; i++) {
                    System.out.println(cities[cityIndex].getStreets()[i]);
                }

                System.out.println("Enter the name of the street");
                streetName = scanner.nextLine();
                streetName = streetName.toLowerCase().trim();

                int streetIndex = checkIfStreetExists(streetName, cityIndex);
                if (streetIndex == -1) {
                    System.out.println("The street doesnt exist");
                }

                else {
                    newProperty.setStreet(cities[cityIndex].getStreets()[streetIndex]);
                    int typeOfProperty;

                    System.out.println("Enter the number of property type \n" +
                            "1. Regular apartment in apartment building\n" +
                            "2. Penthouse in apartment building\n" +
                            "3. Land house");
                    typeOfProperty = scanner.nextInt();
                    scanner.nextLine();

                    newProperty.setType(typeOfProperty);

                    if (newProperty.getType() != typeOfProperty) {
                        System.out.println("Your input was not correct");
                    }

                    else {
                        if (newProperty.getType() == 1 || newProperty.getType() == 2) {
                            int floorNumber;
                            System.out.println("Enter the floor number");
                            floorNumber = scanner.nextInt();
                            scanner.nextLine();
                            newProperty.setFloorNumber(floorNumber);
                        }

                        int numberOfRooms;
                        System.out.println("Enter the number of rooms");
                        numberOfRooms = scanner.nextInt();
                        scanner.nextLine();
                        newProperty.setNumberOfRooms(numberOfRooms);

                        int houseNumber;
                        System.out.println("Enter the house number");
                        houseNumber = scanner.nextInt();
                        scanner.nextLine();
                        newProperty.setHouseNumber(houseNumber);

                        int forRent;
                        System.out.println("Choose one of the option bellow: \n" +
                                "1. property for rent \n" +
                                "2. property for sale");
                        forRent = scanner.nextInt();
                        scanner.nextLine();
                        newProperty.setForRent(forRent);

                        if (newProperty.isForRent() == null) {
                            System.out.println("You chose invalid option");
                        }

                        else {
                            int propertyPrice;
                            System.out.println("Enter the price of the property");
                            propertyPrice = scanner.nextInt();
                            scanner.nextLine();
                            newProperty.setPrice(propertyPrice);
                            newProperty.setUser(user);
                            properties = addProperty(properties, newProperty);
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    public void removeProperty(User user){
        int userPosts = countUserPublishments(user);
        if(userPosts == 0){
            System.out.println("You have no publishments to delete");
        }else{
            int[][] postIdArray = new int[userPosts][];
            System.out.println("Choose the number of the property you'd like to delete");

            int option = 0;
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getUser().getUserName().equals(user.getUserName())) {
                    postIdArray[option] = new int[]{
                            option+1, i
                    };
                    option++;
                    System.out.println(option + ") " + properties[i].getCityName() + " - " + properties[i].getStreet() + " " + properties[i].getHouseNumber());
                }
            }

                int userInput = scanner.nextInt();
                scanner.nextLine();
                int postIndex = getChosenPostIndex(postIdArray,userInput);
                if(postIndex != -1){
                    properties = deletePost(postIndex);
                    System.out.println("The post was successfully removed!");
                }else{
                    System.out.println("Remove process failed! You chose invalid option");
                }
            }
        }

        public void printAllProperties(){
        int count = 0;
        if(properties != null) {
            for (int i = 0; i < properties.length; i++) {
                count++;
                System.out.println(count + ") " + properties[i] + "\n");
            }
        }else{
            System.out.println("Currently there are no publications");
        }
        }

        public void printProperties (User user) {
        int count = 0;
        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getUser().getUserName().equals(user.getUserName())) {
                    count++;
                    System.out.println(count + ") " + properties[i] + "\n");
                }
            }
            if(count == 0){
                System.out.println("You have no publications");
            }
        }

        else {
            System.out.println("You have no publications");
        }
    }

    public Property[] search () {

        Property[] filteredProperties = null;

        if (properties != null) {
            System.out.println("Lets start searching. \n" +
                    "If you want to skip a filter just write -999");
            Boolean forRent = isForRentFilter();
            Integer type = getTypeFilter();
            Integer roomNumber = getRoomNumberFilter();
            Integer minimumPrice = getMinimumPriceFilter();
            Integer maximumPrice = getMaximumPriceFilter(minimumPrice);

            for (int i = 0; i < properties.length; i++) {

                if (    (forRent == null || properties[i].isForRent() == forRent) &&
                        (type == null || properties[i].getType() == type) &&
                        (roomNumber == null || properties[i].getNumberOfRooms() == roomNumber) &&
                        (minimumPrice == null || properties[i].getPrice() > minimumPrice) &&
                        (maximumPrice == null || properties[i].getPrice() <= maximumPrice)
                ) {
                    filteredProperties = addProperty(filteredProperties, properties[i]);
                }
            }

            if (filteredProperties == null) {
                System.out.println("No results found");
            }
        }

        else {
            System.out.println("There are no properties to search");
        }
        return filteredProperties;
    }

    private Integer getMaximumPriceFilter (Integer minimumPrice) {
        Integer maximumPrice = null;
        int userInput;
        boolean endLoop = false;

        do {
            System.out.println("Enter the maximum price");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput > 0) {
                if (minimumPrice != null) {
                    if (minimumPrice >= userInput) {
                        System.out.println("Maximum price should be higher then minimum price");
                    }


                    else {
                        maximumPrice = userInput;
                        endLoop = true;
                    }
                }

                else {
                    maximumPrice = userInput;
                    endLoop = true;
                }

            }

            else if (userInput == -999){
                endLoop = true;
            }

            else {
                System.out.println("Maximum price should be higher than 0");
            }
        } while (!endLoop);
        return maximumPrice;
    }

    private Integer getMinimumPriceFilter () {
        Integer minimumPrice = null;
        int userInput;
        boolean endloop = false;

        do {
            System.out.println("Enter the minimum price");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput >= 0) {
                minimumPrice = userInput;
                endloop = true;
            }

            else {
                if (userInput == -999) {
                    endloop = true;
                }

                else {
                    System.out.println("Minimum price should be at least 0");
                }
            }

        } while (!endloop);
        return minimumPrice;
    }

    private Integer getRoomNumberFilter () {
        int userInput;
        Integer roomNumber = null;
        boolean endLoop = false;

        do {
            System.out.println("Enter the number of rooms you want");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput>0) {
                roomNumber = userInput;
                endLoop = true;
            }

            else if (userInput == -999) {
                endLoop = true;
            }

            else {
                System.out.println("Room number must be at least 1");
            }

        } while(!endLoop);


        return roomNumber;
    }


    private Integer getTypeFilter (){
        int userInput;
        Integer type = null;
        boolean endLoop = false;

        do {
            System.out.println("Enter the number of property type \n" +
                    "1. Regular apartment in apartment building\n" +
                    "2. Penthouse in apartment building\n" +
                    "3. Land house");
            userInput = scanner.nextInt();

            if (userInput >= 1 && userInput<=3 || userInput==-999) {
                switch (userInput) {
                    case 1 -> type = 1;
                    case 2 -> type = 2;
                    case 3 -> type = 3;
                }
                endLoop = true;
            }

            else {
                System.out.println("Please choose a relevant option or skip by writing -999");
            }

        } while (!endLoop);
        return type;
    }

    private Boolean isForRentFilter (){
        int userInput;
        Boolean forRent = null;
        boolean endLoop = false;

        do {
            System.out.println("Enter the number of relevant option: \n" +
                    "1. Property for rent \n" +
                    "2. Property for sale");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1 || userInput == 2 || userInput==-999) {
                endLoop = true;

                switch (userInput) {
                    case 1 -> forRent = true;
                    case 2 -> forRent = false;
                }
            }

            else {
                System.out.println("Please choose a relevant option or skip by writing -999");
            }
        } while (!endLoop);
        return forRent;
    }


    private Property[] deletePost(int postIndex){
        int arrLength;

        if (properties == null) {
            arrLength = 0;
        }

        else {
            arrLength = properties.length;
        }

        Property[] tempProperties = new Property[arrLength - 1];

        for (int i = 0; i < properties.length; i++) {
            if(i != postIndex) {
                tempProperties[i] = properties[i];
            }
        }

        return tempProperties;
    }

    private int getChosenPostIndex(int[][] postId, int userInput){
        int index = -1;
        if(postId != null) {
            for (int i = 0; i < postId.length; i++) {
                if (postId[i][0] == userInput) {
                    index = postId[i][1];
                    break;
                }
            }
        }
        return index;
    }
    private int countUserPublishments(User user){
        int quantity = 0;
        if(properties != null) {
            for (int i = 0; i < properties.length; i++) {
                if(properties[i].getUser().getUserName().equals(user.getUserName())){
                    quantity++;
                }
            }
        }
        return quantity;
    }


    private int checkIfStreetExists (String streetName, int cityIndex) {
        int streetIndex = -1;

        for (int i = 0; i < cities[cityIndex].getStreets().length; i++) {
            if (cities[cityIndex].getStreets()[i].toLowerCase().equals(streetName)) {
                streetIndex = i;
                break;
            }
        }
        return streetIndex;
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
        if(users != null) {
            for (int i = 0; i < users.length; i++) {
                if (users[i].getUserName().equals(userLogin)) {
                    if (users[i].getPassword().equals(userPassword)) {
                        result = i;
                        break;
                    }
                }
            }
        }

        if (result == -1) {
            System.out.println("The username or the password is incorrect");
        }
        return result;
    }
    private User[] addUser (User[] users, User newUser) {
        int usersLength;
        if(users == null){
            usersLength = 0;
        }else{
            usersLength = users.length;
        }
        User[] tempUsers = new User[usersLength + 1];

        for (int i = 0; i < usersLength; i++) {
            tempUsers[i] = users[i];
        }

        tempUsers[usersLength] = newUser;

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
        if(users != null) {
            for (int i = 0; i < users.length; i++) {
                if (userName.equals(users[i].getUserName())) {
                    System.out.println("The user name is already taken");
                    isAvailable = false;
                    break;
                }
            }
        }
        return isAvailable;
    }

    public void printProperties (Property[] properties) {
        int count = 0;

        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                count++;
                System.out.println(count + ") " + properties[i] + "\n");
            }
        }
    }
}
