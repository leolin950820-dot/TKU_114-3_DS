import java.util.Scanner;

public class AtmMenu {
    static Scanner sc = new Scanner(System.in);
    static int Balance = 1000;
    public static void main(String[] args) {
        while (true) {
            showMenu(); // 呼叫方法：顯示選單
            int choise = sc.nextInt();

            if (choise == 0) {
                System.out.println("Exit");
                break;
            }

            switch (choise) {
                case 1:
                    checkBalance(); 
                    break;
                case 2:
                    deposit();      
                    break;
                case 3:
                    withdraw();     
                    break;
                default:
                    System.out.println("無此選項，請輸入 0-3 之間的數字。");
                    break;
            }
        }
        sc.close();
    }
    public static void showMenu() {
        System.out.println("=== ATM 功能選單 ===");
        System.out.println("1:查詢餘額");
        System.out.println("2:存款");
        System.out.println("3:提款");
        System.out.println("0:離開");
        System.out.print("請輸入選項: ");
    }
    public static void checkBalance() {
        System.out.println("餘額: " + Balance);
    }
    public static void deposit() {
        System.out.print("請輸入存款金額： ");
        int depositAmount = sc.nextInt();
        if (depositAmount > 0) {
            Balance += depositAmount;
            System.out.println("存款成功！已存入 " + depositAmount + " 元。目前餘額：" + Balance + " 元");
        } else {
            System.out.println("存款金額必須大於 0 元！");
        }
    }
    public static void withdraw() {
        System.out.print("請輸入提款金額：");
        int withdrawAmount = sc.nextInt();
        if (withdrawAmount <= 0) {
            System.out.println("提款金額必須大於 0 元！");
        } else if (withdrawAmount > Balance) {
            System.out.println("餘額不足！目前餘額僅剩 " + Balance + " 元。");
        } else {
            Balance -= withdrawAmount;
            System.out.println("提款成功！已領取 " + withdrawAmount + " 元。目前餘額：" + Balance + " 元");
        }
    }
}
//showMenu()
//getOperationName(int option)
//readValidAmount(Scanner sc, int option)
//calculateNewBalance(int amount, int option)
//printTransactionResult(String opName, int amount, int finalBalance)
