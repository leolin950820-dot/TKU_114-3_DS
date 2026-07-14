import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];
        inputSales(sc, sales);
        displaySalesMatrix(sales);
        int[] productTotals = calculateProductTotals(sales);
        System.out.println("\n--- 每項商品銷售總量 ---");
        for (int i = 0; i < productTotals.length; i++) {
            System.out.println("商品 " + (i + 1) + " 總銷售量: " + productTotals[i]);
        }
        int[] dailyTotals = calculateDailyTotals(sales);
        System.out.println("\n--- 每天全部商品銷售總量 ---");
        for (int j = 0; j < dailyTotals.length; j++) {
            System.out.println("第 " + (j + 1) + " 天總銷售量: " + dailyTotals[j]);
        }
        findBestSellingProduct(productTotals);
        
        sc.close();
    }
    public static void inputSales(Scanner sc, int[][] sales) {
        System.out.println("請輸入 3 項商品在 4 天內的銷售量：");
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print("商品 " + (i + 1) + " 的第 " + (j + 1) + " 天銷售量: ");
                    if (sc.hasNextInt()) {
                        int amount = sc.nextInt();
                        if (amount >= 0) {
                            sales[i][j] = amount;
                            break; 
                        }
                    } else {
                        sc.next(); 
                    }
                    System.out.println("輸入錯誤！銷售量必須為大於或等於 0 的整數，請重新輸入。");
                }
            }
        }
    }
    public static void displaySalesMatrix(int[][] sales) {
        System.out.println("\n================= 銷售量表格 =================");
        System.out.printf("%-8s\t%-6s\t%-6s\t%-6s\t%-6s\n", "商品\\天數", "第1天", "第2天", "第3天", "第4天");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < sales.length; i++) {
            System.out.printf("商品 %d\t", (i + 1));
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%-6d\t", sales[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }
    public static int[] calculateProductTotals(int[][] sales) {
        int[] productTotals = new int[sales.length]; 
        for (int i = 0; i < sales.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < sales[i].length; j++) {
                rowSum += sales[i][j];
            }
            productTotals[i] = rowSum;
        }
        return productTotals;
    }
    public static int[] calculateDailyTotals(int[][] sales) {
        int columns = sales[0].length; // 欄數為 4
        int[] dailyTotals = new int[columns];
        for (int j = 0; j < columns; j++) {
            int colSum = 0;
            for (int i = 0; i < sales.length; i++) {
                colSum += sales[i][j];
            }
            dailyTotals[j] = colSum;
        }
        return dailyTotals;
    }
    public static void findBestSellingProduct(int[] productTotals) {
        int maxSales = productTotals[0];
        int bestProductIdx = 0;
        
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > maxSales) {
                maxSales = productTotals[i];
                bestProductIdx = i;
            }
        }
        System.out.println("\n--- 銷售冠軍 ---");
        System.out.println("總銷售量最高的商品是：商品 " + (bestProductIdx + 1) + " (總銷量：" + maxSales + ")");
    }
}