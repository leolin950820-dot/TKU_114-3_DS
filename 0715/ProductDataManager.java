/**
 * 檔名：ProductDataManager.java
 * public class 名稱：ProductDataManager
 * 
 * 【測試案例紀錄 (8 組 Test Cases)】
 * --------------------------------------------------------------------------------------------------
 * 測試案例 1：初始化與顯示解析後的商品表格
 * - 操作步驟：啟動程式，系統自動解析原始 records，並在主選單上方或選擇「1」時顯示。
 * - 預期結果：正確以表格形式輸出 5 筆初始商品的名稱、價格與庫存。
 * 
 * 測試案例 2：完整名稱與部分名稱搜尋（搜尋 "Mouse" 與 "o"）
 * - 輸入：選擇選單「2」，輸入「Mouse」（完整比對）或「o」（部分比對，應挑出 Keyboard, Mouse, Monitor）。
 * - 預期結果：系統正確列出符合條件的商品資訊與總筆數。
 * 
 * 測試案例 3：顯示低庫存商品
 * - 操作步驟：選擇選單「3」（系統設定低庫存臨界值為小於或等於 10）。
 * - 預期結果：正確篩選出庫存為 5 的 Monitor 與庫存為 8 的 Headset。
 * 
 * 測試案例 4：顯示庫存總價值
 * - 操作步驟：選擇選單「4」。
 * - 預期結果：計算所有商品的 (價格 * 庫存) 並加總。
 *   初始總價值計算：(890*12)+(490*20)+(5200*5)+(250*30)+(1290*8) = 10,680 + 9,800 + 26,000 + 7,500 + 10,320 = 64,300。
 * 
 * 測試案例 5：新增新文字資料（正確格式）
 * - 輸入：選擇選單「5」，輸入「Speaker,1990,15」。
 * - 預期結果：格式完全正確，商品成功加入陣列，再次查看商品列表會多出 Speaker。
 * 
 * 測試案例 6：錯誤資料格式驗證 — 欄位數量不足
 * - 輸入：選擇選單「5」，輸入「Webcam,1200」（少一個欄位）。
 * - 預期結果：系統提示格式錯誤原因（必須包含名稱、價格、庫存三個欄位），程式不會崩潰，安全回到選單。
 * 
 * 測試案例 7：錯誤資料格式驗證 — 價格非數字
 * - 輸入：選擇選單「5」，輸入「Microphone,abc,10」（價格填入文字）。
 * - 預期結果：捕捉到 NumberFormatException，提示價格必須為有效整數，程式不會中止。
 * 
 * 測試案例 8：錯誤資料格式驗證 — 庫存非數字或名稱空白
 * - 輸入：選擇選單「5」，輸入「   ,1500,xyz」（名稱空白且庫存為文字）。
 * - 預期結果：提示名稱不可為空且庫存必須為有效整數，拒絕寫入，程式維持正常運作。
 * --------------------------------------------------------------------------------------------------
 */

import java.util.Scanner;

public class ProductDataManager {
    private static final int MAX_PRODUCTS = 100;
    
    private static String[] productNames = new String[MAX_PRODUCTS];
    private static int[] productPrices = new int[MAX_PRODUCTS];
    private static int[] productStocks = new int[MAX_PRODUCTS];
    private static int productCount = 0; 

    public static void main(String[] args) {
        // 原始CSV格式資料
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };
        initializeData(records);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== 歡迎使用商品資料管理系統 ===");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showProductTable();
                    break;
                case "2":
                    System.out.print("請輸入搜尋關鍵字 (支援完整與部分名稱)：");
                    String keyword = scanner.nextLine();
                    searchProducts(keyword);
                    break;
                case "3":
                    showLowStockProducts(10); // 假設小於或等於 10 為低庫存
                    break;
                case "4":
                    showTotalValue();
                    break;
                case "5":
                    System.out.print("請輸入新商品文字資料 (格式如: 名稱,價格,庫存)：");
                    String newRecord = scanner.nextLine();
                    addNewProduct(newRecord);
                    break;
                case "6":
                    System.out.println("系統已結束，謝謝使用。");
                    running = false;
                    break;
                default:
                    System.out.println("無效的選擇，請重新輸入！");
            }
            System.out.println();
        }
        scanner.close();
    }
    public static void initializeData(String[] records) {
        for (String record : records) {
            addNewProduct(record);
        }
    }
    public static void showProductTable() {
        System.out.println("\n=================== 商品資料列表 ===================");
        System.out.printf("%-5s \t %-20s \t %-10s \t %-10s\n", "編號", "商品名稱", "價格", "庫存");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("[%d] \t %-20s \t $%,-10d \t %-10d\n", (i + 1), productNames[i], productPrices[i], productStocks[i]);
        }
        System.out.println("====================================================");
    }
    public static void searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("搜尋關鍵字不可為空！");
            return;
        }

        String target = keyword.trim().toLowerCase();
        boolean found = false;
        int foundCount = 0;

        System.out.println("\n[搜尋結果] 符合 \"" + keyword.trim() + "\" 的商品如下：");
        for (int i = 0; i < productCount; i++) {
            String currentNameLower = productNames[i].toLowerCase();
            if (currentNameLower.equals(target) || currentNameLower.contains(target)) {
                System.out.printf("- %s (價格: $%d, 庫存: %d)\n", productNames[i], productPrices[i], productStocks[i]);
                found = true;
                foundCount++;
            }
        }

        if (!found) {
            System.out.println("找不到符合條件的商品。");
        } else {
            System.out.println("共找到 " + foundCount + " 筆商品。");
        }
    }
    public static void showLowStockProducts(int threshold) {
        System.out.println("\n【低庫存商品警示 (庫存 <= " + threshold + ")】");
        boolean hasLowStock = false;
        for (int i = 0; i < productCount; i++) {
            if (productStocks[i] <= threshold) {
                System.out.printf("警告!! 商品: %-15s | 目前庫存: %d (請儘速補貨)\n", productNames[i], productStocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前所有商品庫存皆在安全水位。");
        }
    }
    public static void showTotalValue() {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += (long) productPrices[i] * productStocks[i];
        }
        System.out.printf("\n當前倉庫內商品之【庫存總價值】：$%,d 元\n", totalValue);
    }
    public static void addNewProduct(String record) {
        if (record == null || record.trim().isEmpty()) {
            System.out.println("錯誤原因：輸入的文字資料為空值。");
            return;
        }

        if (productCount >= MAX_PRODUCTS) {
            System.out.println("錯誤原因：系統資料庫已滿，無法新增商品。");
            return;
        }
        String[] tokens = record.split(",");
        if (tokens.length != 3) {
            System.out.println("錯誤原因：資料欄位數量不正確！必須包含「名稱,價格,庫存」三個部分。你輸入了 " + tokens.length + " 個欄位。");
            return;
        }

        String name = tokens[0].trim();
        if (name.isEmpty()) {
            System.out.println("錯誤原因：商品名稱欄位不可為空白。");
            return;
        }

        try {
            int price = Integer.parseInt(tokens[1].trim());
            int stock = Integer.parseInt(tokens[2].trim());

            if (price < 0 || stock < 0) {
                System.out.println("錯誤原因：價格與庫存不能為負數！");
                return;
            }
            productNames[productCount] = name;
            productPrices[productCount] = price;
            productStocks[productCount] = stock;
            productCount++;

        } catch (NumberFormatException e) {
            System.out.println("錯誤原因：數字轉換失敗！價格或庫存欄位中含有非整數的字元。(" + e.getMessage() + ")");
        }
    }
    public static void printMenu() {
        System.out.println("----------------------------------------------------");
        System.out.println("1. 顯示全部商品表格");
        System.out.println("2. 搜尋商品名稱 (支援完整與部分比對)");
        System.out.println("3. 篩選低庫存商品");
        System.out.println("4. 計算庫存總價值");
        System.out.println("5. 輸入新商品資料 (格式：名稱,價格,庫存)");
        System.out.println("6. 結束");
        System.out.println("----------------------------------------------------");
    }
}