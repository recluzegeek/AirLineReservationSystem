public class RolesAndPermissions extends User {
    private final String[][] usernamesAndPasswords = getUsernameAndPassword();

    public int isPrivilegedUserOrNot(String username, String password) {
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
