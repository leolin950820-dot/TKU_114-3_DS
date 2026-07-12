package week1_practice;

import java.util.Scanner;

public class AtmSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            if (!sc.hasNextInt()) {
                System.out.println("無效的輸入，請輸入選單中的數字。");
                sc.next(); 
                continue;
            }

            int option = sc.nextInt();
            if (option == 0) {
                break;
            }

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int dAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, dAmount);
                    depositCount++;
                    totalDeposit += dAmount;
                    printBalance(balance);
                    break;

                case 3:
                    int wAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    if (canWithdraw(balance, wAmount)) {
                        balance = withdraw(balance, wAmount);
                        withdrawCount++;
                        totalWithdraw += wAmount;
                        printBalance(balance);
                    } else {
                        System.out.println("錯誤：提款金額超過目前餘額！");
                    }
                    break;

                case 4:
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入！");
                    break;
            }
            System.out.println(); 
        }
        printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);

        sc.close();
    }
    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                if (amount > 0) {
                    break;
                }
            } else {
                sc.next(); 
            }
            System.out.println("金額必須大於 0,請重新輸入。");
        }
        return amount;
    }
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }
    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("\n=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}
