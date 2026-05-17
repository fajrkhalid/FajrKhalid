public class PasswordReset {
    public void resetPassword(String email) {
        System.out.println("Password reset link sent to: " + email);
    }

    public void confirmReset(String token) {
        System.out.println("Password reset confirmed with token: " + token);
    }
}