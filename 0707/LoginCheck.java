public class LoginCheck {
      public static void main(String[] args) {
        String username = "admin";
        String inputUsername = "admin";
        String password = "1234";
        String inputPassword = "1234";
        
        boolean passCourse = username.equals(inputUsername) &&password.equals(inputPassword);

        System.out.println("帳號與密碼都正確");
    }
}
