public class TemperatureLevel {
     public static void main(String[] args) {
        double temperature =38.0;

        if (temperature < 15.0) {
            System.out.println("Cold");
        } else if (temperature > 15 && temperature<28) {
            System.out.println("Comfortable");
        } else {
            System.out.println("Hot");
        }
    }
}
