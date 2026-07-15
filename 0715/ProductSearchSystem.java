/**
 * 檔名：ProductSearchSystem.java
 * 
 * 【測試案例紀錄 (Test Cases)】
 * --------------------------------------------------------------------------------------------------
 * 測試案例 1：顯示全部商品
 * - 操作步驟：啟動程式，在選單輸入「1」。
 * - 預期結果：正確輸出 5 筆商品的名稱、價格與庫存，並維持排版整齊。
 * 
 * 測試案例 2：完整名稱搜尋（精確比對、忽略大小寫與前後空白）
 * - 輸入：選擇選單「2」，輸入關鍵字「  iPHone 15 prO  」
 * - 預期結果：成功找到商品「iPhone 15 Pro」，顯示其價格 36900、庫存 15。
 * 
 * 測試案例 3：完整名稱搜尋（搜尋不存在的商品）
 * - 輸入：選擇選單「2」，輸入關鍵字「Samsung S24」
 * - 預期結果：顯示「找不到此商品！」提示。
 * 
 * 測試案例 4：部分名稱搜尋（模糊比對，多筆結果）
 * - 輸入：選擇選單「3」，輸入關鍵字「pro」
 * - 預期結果：同時找到並顯示「iPhone 15 Pro」與「iPad Pro」兩筆商品的資訊。
 * 
 * 測試案例 5：顯示最長名稱商品
 * - 操作步驟：選擇選單「4」。
 * - 預期結果：系統正確辨識並輸出字數最長的商品名稱「Sony WH-1000XM5」（15 個字元）。
 * 
 * 測試案例 6：關鍵字第一次出現的位置（Index 搜尋）
 * - 輸入：選擇選單「5」，輸入關鍵字「Book」
 * - 預期結果：系統列出包含 "Book" 的商品，並標註首次出現的位置。
 *   例如：「MacBook Air」-> "Book" 首次出現在索引第 3 位 (從 0 開始計算)。
 * --------------------------------------------------------------------------------------------------
 */

import java.util.Scanner;

public class ProductSearchSystem {
    private static final String[] PRODUCT_NAMES = {
        "iPhone 15 Pro", 
        "MacBook Air", 
        "Sony WH-1000XM5", 
        "iPad Pro", 
        "Logitech MX Master 3S"
    };
    private static final int[] PRODUCT_PRICES = {36900, 32900, 11900, 27900, 3290};
    private static final int[] PRODUCT_STOCKS = {15, 8, 20, 12, 35};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== 歡迎使用商品搜尋系統 ===");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showAllProducts();
                    break;
                case "2":
                    System.out.print("請輸入要精確搜尋的完整商品名稱：");
                    String exactKeyword = scanner.nextLine();
                    searchExactProduct(exactKeyword);
                    break;
                case "3":
                    System.out.print("請輸入部分商品名稱進行搜尋：");
                    String partialKeyword = scanner.nextLine();
                    searchPartialProduct(partialKeyword);
                    break;
                case "4":
                    showLongestProductName();
                    break;
                case "5":
                    System.out.print("請輸入要定位的搜尋關鍵字：");
                    String indexKeyword = scanner.nextLine();
                    showKeywordFirstIndex(indexKeyword);
                    break;
                case "6":
                    System.out.println("感謝使用，系統已結束。");
                    running = false;
                    break;
                default:
                    System.out.println("無效的選擇，請輸入 1 到 6 的數字。");
            }
            System.out.println(); 
        }
        scanner.close();
    }
    public static void printMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (模糊搜尋)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與關鍵字首次出現位置");
        System.out.println("6. 結束");
        System.out.println("----------------------------------------");
    }
    public static void showAllProducts() {
        System.out.println("\n【商品列表】");
        System.out.printf("%-25s\t%-10s\t%-10s\n", "商品名稱", "價格", "庫存");
        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            System.out.printf("%-25s\t$%,-10d\t%-10d\n", PRODUCT_NAMES[i], PRODUCT_PRICES[i], PRODUCT_STOCKS[i]);
        }
    }
    public static void searchExactProduct(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("關鍵字不能為空！");
            return;
        }

        String target = keyword.trim();
        boolean found = false;

        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            if (PRODUCT_NAMES[i].equalsIgnoreCase(target)) {
                System.out.println("\n[搜尋結果] 找到精確符合的商品：");
                System.out.println("名稱: " + PRODUCT_NAMES[i]);
                System.out.println("價格: $" + PRODUCT_PRICES[i]);
                System.out.println("庫存: " + PRODUCT_STOCKS[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("找不到此商品！");
        }
    }
    public static void searchPartialProduct(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("關鍵字不能為空！");
            return;
        }

        String target = keyword.trim().toLowerCase();
        boolean found = false;
        int matchCount = 0;

        System.out.println("\n[部分名稱搜尋結果] 包含 \"" + keyword.trim() + "\" 的商品：");
        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            if (PRODUCT_NAMES[i].toLowerCase().contains(target)) {
                System.out.printf("- %s | 價格: $%d | 庫存: %d\n", PRODUCT_NAMES[i], PRODUCT_PRICES[i], PRODUCT_STOCKS[i]);
                found = true;
                matchCount++;
            }
        }

        if (!found) {
            System.out.println("沒有找到符合任何部分的商品。");
        } else {
            System.out.println("共找到 " + matchCount + " 筆相符的商品。");
        }
    }
    public static void showLongestProductName() {
        if (PRODUCT_NAMES.length == 0) {
            System.out.println("目前系統中無商品。");
            return;
        }

        int longestIndex = 0;
        for (int i = 1; i < PRODUCT_NAMES.length; i++) {
            if (PRODUCT_NAMES[i].length() > PRODUCT_NAMES[longestIndex].length()) {
                longestIndex = i;
            }
        }

        System.out.println("\n【名稱最長的商品】");
        System.out.println("商品名稱: " + PRODUCT_NAMES[longestIndex] + " (長度: " + PRODUCT_NAMES[longestIndex].length() + " 個字元)");
        System.out.println("價格: $" + PRODUCT_PRICES[longestIndex]);
        System.out.println("庫存: " + PRODUCT_STOCKS[longestIndex]);
    }
    public static void showKeywordFirstIndex(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("關鍵字不能為空！");
            return;
        }

        String target = keyword.trim().toLowerCase();
        boolean found = false;

        System.out.println("\n【關鍵字 \"" + keyword.trim() + "\" 的首次出現位置搜尋結果 (索引值由 0 開始)】");
        for (String productName : PRODUCT_NAMES) {
            String lowerName = productName.toLowerCase();
            int index = lowerName.indexOf(target);

            if (index != -1) {
                System.out.printf("- 商品: %-25s | 首次出現的位置 (Index): %d\n", productName, index);
                found = true;
            }
        }

        if (!found) {
            System.out.println("所有商品名稱中皆未含有此關鍵字！");
        }
    }
}