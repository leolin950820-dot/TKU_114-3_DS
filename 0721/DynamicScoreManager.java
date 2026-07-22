import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請輸入成績 (0-100)，輸入 -1 結束輸入：");
        while (true) {
            System.out.print("請輸入成績: ");
            String input = scanner.nextLine().trim();

            try {
                int score = Integer.parseInt(input);
                if (score == -1) {
                    break;
                }
                if (score >= 0 && score <= 100) {
                    scores.add(score);
                } else {
                    System.out.println("[錯誤] 成績必須介於 0 到 100 之間，請重新輸入！");
                }
            } catch (NumberFormatException e) {
                System.out.println("[錯誤] 請輸入有效的整數數字！");
            }
        }
        System.out.println("\n===========================");
        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績，無法進行統計。");
        } else {
            printStatistics(scores);
        }
        System.out.println("===========================");

        scanner.close();
    }
    private static void printStatistics(ArrayList<Integer> scores) {
        System.out.println("【成績統計結果】");
        System.out.println("總筆數: " + scores.size() + " 筆");
        System.out.printf("平均分數: %.2f 分%n", calculateAverage(scores));
        System.out.println("最高分: " + findMaxScore(scores) + " 分");
        System.out.println("最低分: " + findMinScore(scores) + " 分");
        ArrayList<Integer> passingScores = filterPassingScores(scores, 60);
        System.out.println("及格名單 (>=60分): " + passingScores);
        System.out.println("及格人數: " + passingScores.size() + " 人");
    }
    private static double calculateAverage(ArrayList<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }
    private static int findMaxScore(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
    private static int findMinScore(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }
    private static ArrayList<Integer> filterPassingScores(ArrayList<Integer> scores, int passingGrade) {
        ArrayList<Integer> passingList = new ArrayList<>();
        for (int score : scores) {
            if (score >= passingGrade) {
                passingList.add(score);
            }
        }
        return passingList;
    }
}