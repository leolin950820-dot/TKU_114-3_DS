import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalQuantity = 0;
        int totalAmount = 0;

        while (true) {
            System.out.println("===== Menu =====");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("輸入商品選項: ");

            int option = sc.nextInt();

            if (option == 0) {
                break;
            }

            String itemName = "";
            int price = 0;

            switch (option) {
                case 1:
                    price = 30;
                    break;
                case 2:
                    price = 35;
                    break;
                case 3:
                    price = 50;
                    break;
                default:
                    System.out.println("Invalid option!");
                    continue;
            }

            int quantity = 0;
            while (true) {
                System.out.print("請輸入數量: ");
                quantity = sc.nextInt();
                
                if (quantity > 0) {
                    break; // 數量正確，跳出輸入數量的迴圈
                } else {
                    System.out.println("數量必須大於0 請重新輸入!");
                }
            }

            int subtotal = price * quantity;

            System.out.println("Subtotal: $" + subtotal);
            System.out.println();

            totalQuantity += quantity;
            totalAmount += subtotal;
        }

        System.out.println();
        System.out.println("===== Checkout =====");
        System.out.println("Total Quantity: " + totalQuantity);
        System.out.println("Total Amount: $" + totalAmount);

        sc.close();
    }
}
