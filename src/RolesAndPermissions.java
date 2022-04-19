public class RolesAndPermissions extends User {
    //        ************************************************************ Behaviours/Methods ************************************************************

    public int isPrivilegedUserOrNot(String username, String password) {
        int isFound = -1;
        for (int i = 0; i < adminUserNameAndPassword.length; i++) {
            if (username.equals(adminUserNameAndPassword[i][0])) {
                if (password.equals(adminUserNameAndPassword[i][1])) {
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
