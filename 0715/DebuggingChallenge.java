/**
 * 檔名：DebuggingChallenge.java
 * public class 名稱：DebuggingChallenge
 * 
 * ==================================================================================================
 * 【除錯修正報告 (Bug Fix Report)】
 * --------------------------------------------------------------------------------------------------
 * 1. 錯誤位置：System.out.println("系統結束，年齡：" + age)
 *    - 錯誤類型：語法錯誤 / 編譯錯誤 (Syntax Error)
 *    - 原因：該行敘述末端漏掉了分號 `;`。
 *    - 修正方式：在該行結尾加上分號 `;`。
 * 
 * 2. 錯誤位置：for (int i = 0; i <= scores.length; i++)
 *    - 錯誤類型：執行時期錯誤 / 陣列越界 (ArrayIndexOutOfBoundsException)
 *    - 原因：迴圈條件使用了 `<= scores.length`。當 i 等於長度 3 時，存取 scores[3] 會超出陣列邊界。
 *    - 修正方式：將 `<=` 改為 `<`，讓迴圈在 i 達到 scores.length 時自動停止。
 * 
 * 3. 錯誤位置：double average = total / scores.length;
 *    - 錯誤類型：邏輯錯誤 (Logical Error - 整數除法)
 *    - 原因：total 與 scores.length 皆為 int 型態，相除會進行整數除法並丟棄小數部分，導致精確度流失。
 *    - 修正方式：將 total 強制轉型為 double (例如：(double) total)，使運算轉為浮點數除法。
 * 
 * 4. 錯誤位置：int age = sc.nextInt(); 之後緊接 String command = sc.nextLine();
 *    - 錯誤類型：輸入緩衝區殘留問題 (Scanner Behavior Issue)
 *    - 原因：sc.nextInt() 只會讀取數字，會把使用者按下的換行符號 (\n) 留在緩衝區，導致下一個 sc.nextLine() 直接吞下換行符號而無法讓使用者輸入。
 *    - 修正方式：在 sc.nextInt() 後面額外呼叫一次 sc.nextLine()，用來清空緩衝區中的換行符號。
 * 
 * 5. 錯誤位置：if (command == "exit")
 *    - 錯誤類型：邏輯錯誤 / 字串比較錯誤 (String Comparison Error)
 *    - 原因：在 Java 中，使用 == 比較字串是在比對物件的「記憶體位址」，而不是「字串內容」。
 *    - 修正方式：改用 command.equals("exit") 或是 "exit".equals(command) 來進行實質內容的比對。
 * ==================================================================================================
 */

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();
        if ("exit".equals(command)) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}
