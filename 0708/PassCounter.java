public class PassCounter {
    public static void main(String[] args) {
        int count = 0;
        int score1 = 80;
        int score2 = 55;
        int score3 = 70;
        if(score1>60){
            count++;
        }
        if(score2>60){
            count++;
        }
        if (score3>60){
            count++;
        }
        System.out.println("pass count: "+count);
    }
}
