import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};
        int totalBoughtCount = 0;
        int totalRestockedCount = 0;

        boolean running = true;
        System.out.println("====== 歡迎使用商品管理系統 ======");

        while (running) {
            displayMenu();
            int choice = readValidInt(sc, "請選擇功能 (1-7): ", 1, 7);

            switch (choice) {
                case 1: 
                    showAllProducts(names, prices, stocks);
                    break;
                case 2: 
                    queryProductById(sc, names, prices, stocks);
                    break;
                case 3: 
                    int bought = purchaseProduct(sc, names, prices, stocks);
                    totalBoughtCount += bought;
                    break;
                case 4: 
                    int restocked = restockProduct(sc, names, stocks);
                    totalRestockedCount += restocked;
                    break;
                case 5: 
                    showLowStockProducts(names, stocks);
                    break;
                case 6: 
                    showTotalValue(names, prices, stocks);
                    break;
                case 7: 
                    showSummaryAndExit(totalBoughtCount, totalRestockedCount);
                    running = false;
                    break;
            }
        }
        sc.close();
    }
    public static void displayMenu() {
        System.out.println("\n---------------- 選單功能 ----------------");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (< 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束程式");
        System.out.println("----------------------------------------");
    }
    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=================== 商品清單 ===================");
        System.out.printf("%-6s\t%-12s\t%-8s\t%-6s\n", "編號", "商品名稱", "價格", "庫存");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("[%d]\t%-12s\t$%d\t%d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("================================================");
    }
    public static void queryProductById(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 依編號查詢商品 ---");
        int id = readValidInt(sc, "請輸入要查詢的商品編號 (1-" + names.length + "): ", 1, names.length);
        int index = id - 1; 

        System.out.println("\n【查詢結果】");
        System.out.println("商品名稱：" + names[index]);
        System.out.println("商品價格：$" + prices[index]);
        System.out.println("目前庫存：" + stocks[index]);
    }
    public static int purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 購買商品 ---");
        int id = readValidInt(sc, "請輸入欲購買的商品編號 (1-" + names.length + "): ", 1, names.length);
        int index = id - 1;

        if (stocks[index] == 0) {
            System.out.println("很抱歉，[" + names[index] + "] 目前已無庫存，無法購買。");
            return 0;
        }

        System.out.println("選購商品: " + names[index] + " (目前庫存: " + stocks[index] + ")");
        int quantity = readValidInt(sc, "請輸入購買數量: ", 1, stocks[index]);

        stocks[index] -= quantity;
        int cost = prices[index] * quantity;
        System.out.println("交易成功！已扣除庫存。您購買了 " + quantity + " 個 " + names[index] + "，總計 $" + cost + " 元。");
        return quantity;
    }
    public static int restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.println("\n--- 補充商品庫存 ---");
        int id = readValidInt(sc, "請輸入欲補貨的商品編號 (1-" + names.length + "): ", 1, names.length);
        int index = id - 1;

        System.out.println("補貨商品: " + names[index] + " (目前庫存: " + stocks[index] + ")");

        int quantity = readValidInt(sc, "請輸入補貨數量: ", 1, 99999);

        stocks[index] += quantity;
        System.out.println("補貨成功！[" + names[index] + "] 目前最新庫存為: " + stocks[index]);
        return quantity;
    }
    public static void showLowStockProducts(String[] names, int[] stocks) {
        System.out.println("\n --- 低庫存商品警告 (庫存 < 10) ---");
        boolean hasLowStock = false;
        System.out.printf("%-12s\t%-6s\n", "商品名稱", "目前庫存");
        System.out.println("---------------------------------");
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-12s\t%d\n", names[i], stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("(目前所有商品庫存皆安全充足)");
        }
        System.out.println("---------------------------------");
    }
    public static void showTotalValue(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 庫存資產價值評估 ---");
        int totalValue = 0;
        for (int i = 0; i < names.length; i++) {
            int itemValue = prices[i] * stocks[i];
            System.out.printf("%-12s: $%d * %d = $%d\n", names[i], prices[i], stocks[i], itemValue);
            totalValue += itemValue;
        }
        System.out.println("---------------------------------");
        System.out.println("全店總庫存價值為：$" + totalValue + " 元");
    }
    public static void showSummaryAndExit(int totalBought, int totalRestocked) {
        System.out.println("\n========================================");
        System.out.println("系統已成功結束。本次運行操作摘要如下：");
        System.out.println(" 🛒 顧客總計購買商品件數：" + totalBought + " 件");
        System.out.println(" 📦 倉庫管理員總計補貨數：" + totalRestocked + " 件");
        System.out.println("謝謝您的使用，祝您有美好的一天！");
        System.out.println("========================================");
    }
    private static int readValidInt(Scanner sc, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
            } else {
                sc.next(); 
            }
            System.out.println("輸入錯誤！請輸入位於範圍內 (" + min + " 到 " + max + ") 的有效整數。");
        }
    }
}