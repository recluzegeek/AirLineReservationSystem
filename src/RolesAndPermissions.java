public class RolesAndPermissions extends User {
    private final String[][] usernamesAndPasswords = getUsernameAndPassword();

    public int isPrivilegedUserOrNot(String username, String password) {
        int isFound = -1;
        for (int i = 0; i < usernamesAndPasswords.length; i++) {
            if (username.equals(usernamesAndPasswords[i][0])) {
                if (password.equals(usernamesAndPasswords[i][1])) {
                    isFound = i;
                }
            }
        }
        return isFound;
    }

    public String isPassengerRegistered(String userName, String password) {
        String isFound = "0";
        Customer c1 = new Customer();
        /*Initializing customerWithTheID to the states of the first obj present in the customerCollection*/
        for (Customer c : c1.customerCollection) {
            if (userName.equals(c.getName())) {
                if (password.equals(c.getPassword())) {
                    isFound = "1-" + c.getUserID();
                    break;
                }
            }
        }
        return isFound;
    }
}
