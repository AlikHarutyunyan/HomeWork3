public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private Boolean isBroker;


    public User (String userName, String password, String phoneNumber, boolean isBroker) {
        setUserName(userName);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        this.isBroker = isBroker;
    }
    public User(){
        this.userName = null;
        this.password = null;
        this.phoneNumber = null;
        this.isBroker = null;

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (isValidPassword(password)) {
           this.password = password;
        }else{
            System.out.println("The password is not strong enough");
        }
    }

    private boolean isValidPassword(String password){
        boolean result = false;
        if (password.length() >= Constants.MINIMUM_PASSWORD_LENGTH) {
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
        for (int i = 0; i <= Constants.LAST_DIGIT; i++) {
            if (string.contains("" + i)) {
                isNumberCheck = true;
                break;
            }
        }
        return isNumberCheck;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumberValidation(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }
    }

    private boolean phoneNumberValidation(String phoneNumber){
        boolean isPhoneNumber = true;
        if (phoneNumber.length() == Constants.PHONE_NUMBER_LENGTH && phoneNumber.startsWith("05")) {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!(containsNumber( "" + phoneNumber.charAt(i)))) {
                    isPhoneNumber = false;
                    System.out.println("Phone number is not acceptable, please try again");
                    break;
                }
            }
        }

        else {
            isPhoneNumber = false;
            System.out.println("Phone number is not acceptable, please try again");
        }
        return isPhoneNumber;
    }

    public void setBroker(boolean broker) {
        this.isBroker = broker;
    }

    public Boolean getBroker() {
        return isBroker;
    }

    public String toString(){
        return this.getUserName() + " " + this.getPhoneNumber();
    }


}
