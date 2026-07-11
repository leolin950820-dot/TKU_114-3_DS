public class SubtotalCalculator {
    public static void main(String[] args) {
        int result = calculateSubtotal(100, 2);
        System.out.println("Result: " + result);
    }
    public static int calculateSubtotal(int price, int quantity){
        return price*quantity;
    }
}
