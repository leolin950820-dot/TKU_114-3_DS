import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入數字： ");
        int num =sc.nextInt();

        while (num != 0) {
            System.out.println("請繼續輸入: ");
            num = sc.nextInt();

        }
        sc.close();
    }
    
}
