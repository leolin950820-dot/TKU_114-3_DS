import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = readCount(sc);
        int[] scores = new int[count];
        inputScores(sc, scores);
        System.out.println("\n--- 所有成績內容 ---");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + " ");
        }
        System.out.println();
        int total = calculateTotal(scores);
        double average = (double) total / count;
        System.out.println("\n--- 統計結果 ---");
        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f\n", average);
        System.out.println("最高分：" + findMax(scores));
        System.out.println("最低分：" + findMin(scores));
        int passCount = countPass(scores);
        int failCount = count - passCount;
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);
        System.out.print("\n請輸入要搜尋的目標成績: ");
        while (!sc.hasNextInt()) {
            System.out.print("輸入錯誤，請輸入整數成績：");
            sc.next();
        }
        int target = sc.nextInt();

        int index = findIndex(scores, target);
        if (index != -1) {
            System.out.println("成績 " + target + " 第一次出現的索引位置為：" + index);
        } else {
            System.out.println("找不到成績 " + target + " 的資料。");
        }

        sc.close();
    }
    public static int readCount(Scanner sc) {
        int count;
        while (true) {
            System.out.print("請輸入資料筆數 (1-50): ");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                }
            } else {
                sc.next(); 
            }
            System.out.println("輸入錯誤！請務必輸入 1 到 50 之間的整數。");
        }
        return count;
    }
    public static void inputScores(Scanner sc, int[] scores) {
        System.out.println("\n請開始輸入每位學生的成績 (0-100): ");
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("第 " + (i + 1) + " 筆成績：");
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break; 
                    }
                } else {
                    sc.next(); 
                }
                System.out.println("輸入錯誤！成績必須在 0 到 100 之間，請重新輸入。");
            }
        }
    }
    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }
    public static int countPass(int[] scores) {
        int count = 0;
        for (int score : scores) {
            if (score >= 60) {
                count++;
            }
        }
        return count;
    }
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i; 
            }
        }
        return -1; 
    }
}