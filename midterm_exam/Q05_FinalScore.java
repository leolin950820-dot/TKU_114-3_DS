package midterm_exam;

public class Q05_FinalScore {
    public static void main(String[] args) {
        System.out.println(calculateFinalScore(80, 90, 5));
        System.out.println(calculateFinalScore(100, 100, 10));
        System.out.println(calculateFinalScore(-1, 80, 5));
        System.out.println(calculateFinalScore(70, 60, 11));
    }

    public static double calculateFinalScore(
        int examScore,
        int assignmentScore,
        int bonus
    ) {
        if (examScore < 0 || examScore > 100) {
            return -1.0;
        }

        if (assignmentScore < 0 || assignmentScore > 100) {
            return -1.0;
        }

        if (bonus < 0 || bonus > 10) {
            return -1.0;
        }

        double score = examScore * 0.4 + assignmentScore * 0.6 + bonus;
        return Math.min(score, 100.0);
    }
}