import java.util.Scanner;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入輸入商品數量: ");
        int amount = sc.nextInt();
        while (amount<1) {
            System.out.println("請重新輸入: ");
            amount = sc.nextInt();
        }
        System.out.println("Amount: " + amount);
        sc.close();
    }
}
