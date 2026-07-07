import java.util.Scanner;

public class ScoreReport {
    public static void main(String[] args) {

        // 建立 Scanner 物件讀取使用者輸入
        Scanner sc = new Scanner(System.in);

        // 輸入姓名與三科成績
        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入 Java 成績：");
        int java = sc.nextInt();

        System.out.print("請輸入 English 成績：");
        int english = sc.nextInt();

        System.out.print("請輸入 Math 成績：");
        int math = sc.nextInt();

        // 計算平均分數
        double average = (java + english + math) / 3.0;

        System.out.println();
        System.out.println("=== 成績報表 ===");
        System.out.println("姓名: " + name);
        System.out.println("Java: " + java);
        System.out.println("English: " + english);
        System.out.println("Math: " + math);
        System.out.println("平均: " + average);

        sc.close();
    }
}
