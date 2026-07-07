import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

        // 1. 輸入基本資料與成績
        System.out.print("請輸入姓名: ");
        String name = scanner.next();

        System.out.print("請輸入 Java 成績: ");
        int javaScore = scanner.nextInt();

        System.out.print("請輸入 English 成績: ");
        int englishScore = scanner.nextInt();

        System.out.print("請輸入 Math 成績: ");
        int mathScore = scanner.nextInt();

        // 2. 計算平均分數 (使用 double)
        double average = (javaScore + englishScore + mathScore) / 3.0;

        // 3. 判斷及格狀態與等第 (至少使用一次 if else if else)
        String status = (average >= 60) ? "及格" : "不及格";
        char grade;

        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // 4. 使用 while 讓選單可以重複操作
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== 學生功能選單 ===");
            System.out.println("1:顯示平均分數");
            System.out.println("2:顯示及格狀態");
            System.out.println("3:顯示等第");
            System.out.println("0:離開");
            System.out.print("請選擇操作項目 (0-3): ");
            
            choice = scanner.nextInt();
            System.out.println(); // 換行美化

            // 5. 至少使用一次 switch 建立選單
            switch (choice) {
                case 1:
                    System.out.printf("%s 的三科平均分數為: %.2f 分\n", name, average);
                    break;
                case 2:
                    System.out.println(name + " 的及格狀態為: " + status);
                    break;
                case 3:
                    System.out.println(name + " 的成績等第為: " + grade);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("輸入錯誤！請輸入 0 到 3 之間的數字。");
                    break;
            }
        }

        scanner.close();
    }
}

