import java.util.Scanner;

public class StudyMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Menu ===");
            System.out.println("1:Review Java");
            System.out.println("2:Practice loops");
            System.out.println("3:Push to GitHub");
            System.out.println("0:Exit");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Review Java");
                    break;
                case 2:
                    System.out.println("Practice loops");
                    break;
                case 3:
                    System.out.println("Push to GitHub");
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }

        sc.close();
    }
}
