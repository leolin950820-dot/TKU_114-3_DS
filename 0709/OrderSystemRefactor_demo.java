import java.util.Scanner;

public class OrderSystemRefactor_demo {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalQuantity = 0;
        int totalAmount = 0;

        while (true) {
            printMenu(); // 1. 顯示選單
            int option = sc.nextInt();

            if (option == 0) {
                break;
            }

            // 檢查選項是否合法，不合法就重新顯示選單
            int price = getPrice(option);
            if (price == 0) {
                System.out.println("Invalid option!");
                continue;
            }

            // 2. 讀取合法數量（內含防呆迴圈）
            int quantity = readValidQuantity(sc);

            // 3. 計算小計
            int subtotal = calculateSubtotal(price, quantity);

            System.out.println("Subtotal: $" + subtotal);
            System.out.println();

            // 累積總數
            totalQuantity += quantity;
            totalAmount += subtotal;
        }

        // 4. 印出最終收據
        printReceipt(totalQuantity, totalAmount);

        sc.close();
    }

    // 顯示選單
    public static void printMenu() {
        System.out.println("===== Menu =====");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
        System.out.print("輸入商品選項: ");
    }

    // 根據選項回傳價格（若不合法則回傳 0）
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }

    // 根據選項回傳商品名稱
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Coffee";
            default: return "Unknown";
        }
    }

    // 讀取合法數量
    public static int readValidQuantity(Scanner sc) {
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
        return quantity;
    }

    // 計算小計
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // 印出收據
    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println();
        System.out.println("===== Checkout =====");
        System.out.println("Total Quantity: " + totalItems);
        System.out.println("Total Amount: $" + totalAmount);
    }
}
