import java.util.Scanner;

public class HealthAdvisor {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String string = "y";
        while (string.equals("y")) {
        System.out.println("請輸入姓名: ");
        String name=sc.next();
        System.out.println("請輸入身高（公尺）： ");
        double height=sc.nextDouble();
        System.out.println("請輸入體重（公斤）： ");
        double weight=sc.nextDouble();

        double BMI=weight/(height*height);

        String Level="";
        if(BMI< 18.5){
            Level="Underweight";
        }else if(BMI > 18.5 && BMI< 24){
           Level="Normal";
        }else if(BMI > 24 && BMI< 27){
            Level="Overweight";
        }else{
            Level="Obese";
        }
        System.out.println("=== BMI Report ===");
        System.out.println("Name: "+ name);
        System.out.println("BMI: "+ BMI);
        System.out.println("Level: "+ Level);
        System.out.println("\n");
        System.out.println("是否繼續輸入下一筆？(y/n):");
        string =sc.next();
        System.out.println();
        
        }
        
    }
}
