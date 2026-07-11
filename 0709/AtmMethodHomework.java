import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000; // 初始餘額為 1000

        while (true) {
            printMenu(); // 呼叫方法：顯示選單
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("感謝您的使用，再見！");
                break;
            }

            switch (choice) {
                case 1:
                    // 查詢餘額
                    printBalance(balance);
                    break;

                case 2:
                    // 存款：先讀取大於 0 的金額，再傳入進行存款計算
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    // 提款：先讀取大於 0 的金額
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    
                    // 輸入驗證：提款金額不能大於目前餘額
                    if (withdrawAmount > balance) {
                        System.out.println("[錯誤] 提款失敗，餘額不足！當前餘額僅剩 " + balance + " 元。");
                    } else {
                        balance = withdraw(balance, withdrawAmount);
                    }
                    break;

                default:
                    System.out.println("[錯誤] 無此選項，請輸入 0-3 之間的數字。");
                    break;
            }
        }
        sc.close();
    }

    // 1. 顯示選單（無參數、無回傳值）
    public static void printMenu() {
        System.out.println("\n=== ATM 功能選單 ===");
        System.out.println("1:查詢餘額");
        System.out.println("2:存款");
        System.out.println("3:提款");
        System.out.println("0:離開");
        System.out.print("請輸入選項: ");
    }

    // 2. 讀取正數金額（有參數、有回傳值）：內含大於 0 的輸入驗證
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                break; // 金額大於 0，合法跳出
            } else {
                System.out.println("[錯誤] 金額必須大於 0 元，請重新輸入！");
            }
        }
        return amount;
    }

    // 3. 存款邏輯（有參數、有回傳值）
    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！已存入 " + amount + " 元。");
        printBalance(balance);
        return balance;
    }

    // 4. 提款邏輯（有參數、有回傳值）
    public static int withdraw(int balance, int amount) {
        balance -= amount;
        System.out.println("提款成功！已領取 " + amount + " 元。");
        printBalance(balance);
        return balance;
    }

    // 5. 顯示目前餘額（有參數、無回傳值）
    public static void printBalance(int balance) {
        System.out.println("目前餘額為：" + balance + " 元");
    }
}