public class RolesAndPermissions {
    private static final String[][] usernamesAndPasswords = User.getUsernameAndPassword();

    public static int isPrivilegedUserOrNot(String username, String password) {
        int isFound = -1;
        for (int i = 0; i < usernamesAndPasswords.length ; i++) {
            if (username.equals(usernamesAndPasswords[i][0])) {
                if (password.equals(usernamesAndPasswords[i][1])) {
                    isFound = i;
                }
            }
        }
        return isFound;
    }
}
