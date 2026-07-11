public class GradeMethod {
    public static void main(String[] args) {
        int javascore=60;
        int englishScore=90;
        int mathScore=40;
        double avg = calculateAverage(javascore,englishScore,mathScore);
        String grade = getGrade(avg);
        System.out.println("Average "+ avg);
        System.out.println("Grade "+ grade);
    }
    public static double calculateAverage(int javaScore, int englishScore, int mathScore){
        return (javaScore+englishScore+mathScore)/3.0;
    }
    public static String getGrade(double average){
        if (average > 80) {
            return "A";
        } else if (average > 60) {
            return "B";
        } else if (average > 40) {
            return "C";
        } else if (average > 20) {
            return "D";
        }else {
            return "F";
        }
    }
}
