import java.util.Scanner;

public class ProductManagementSystem {
    // 全域掃描器，供各輔助方法使用
    private static final Scanner scanner = new Scanner(System.in);
    
    // 操作統計（用於最後的摘要）
    private static int addCount = 0;
    private static int sellCount = 0;
    private static int restockCount = 0;
    private static int priceUpdateCount = 0;

    public static void main(String[] args) {
        // 1. 初始化商品陣列 (長度 10)
        Product[] products = new Product[10];
        
        // 2. 初始加入 5 項商品
        products[0] = new Product("iPhone 15", 29900, 10);
        products[1] = new Product("MacBook Air", 32900, 5);
        products[2] = new Product("iPad Air", 19900, 3); // 低庫存基準設為小於 4
        products[3] = new Product("AirPods Pro", 7490, 15);
        products[4] = new Product("Apple Watch", 13500, 2);  // 低庫存

        boolean running = true;
        System.out.println("====== 歡迎使用 Apple 商品管理系統 ======");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-9): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showAllProducts(products);
                    break;
                case "2":
                    searchProduct(products);
                    break;
                case "3":
                    addNewProduct(products);
                    break;
                case "4":
                    sellProduct(products);
                    break;
                case "5":
                    restockProduct(products);
                    break;
                case "6":
                    updateProductPrice(products);
                    break;
                case "7":
                    showLowStockProducts(products, 4); // 假設低於 4 件為低庫存
                    break;
                case "8":
                    showTotalInventoryValue(products);
                    break;
                case "9":
                    showSummaryAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("[錯誤] 無效的選項，請重新輸入。");
            }
            System.out.println(); // 換行美化
        }
    }

    // --- 輔助方法 1：顯示選單 ---
    private static void printMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.println("------------------------------------");
    }

    // --- 輔助方法 2：顯示全部商品 ---
    private static void showAllProducts(Product[] products) {
        System.out.println("\n=== 目前商品清單 ===");
        boolean empty = true;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.printf("[%d] %s%n", i, products[i].toString());
                empty = false;
            }
        }
        if (empty) {
            System.out.println("目前無任何商品。");
        }
    }

    // --- 輔助方法 3：依名稱搜尋商品 (不限大小寫與前後空白) ---
    private static void searchProduct(Product[] products) {
        System.out.print("請輸入要搜尋的完整商品名稱: ");
        String query = scanner.nextLine().trim();
        
        int index = findProductIndexByName(products, query);
        if (index != -1) {
            System.out.println("[搜尋結果] 找到商品：");
            System.out.println(products[index].toString());
        } else {
            System.out.println("[提示] 找不到名稱為 \"" + query + "\" 的商品。");
        }
    }

    // --- 輔助方法 4：新增商品 ---
    private static void addNewProduct(Product[] products) {
        // 檢查陣列是否已滿
        int emptyIndex = findFirstEmptyIndex(products);
        if (emptyIndex == -1) {
            System.out.println("[拒絕] 陣列已滿 (10項商品上限)，無法新增！");
            return;
        }

        System.out.print("請輸入新商品名稱: ");
        String name = scanner.nextLine().trim();

        // 檢查重複名稱
        if (findProductIndexByName(products, name) != -1) {
            System.out.println("[拒絕] 商品名稱 \"" + name + "\" 已存在，不可重複新增！");
            return;
        }

        try {
            System.out.print("請輸入價格: ");
            int price = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("請輸入初始庫存: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());

            // 透過建構子建立，若數值不合規會拋出異常
            products[emptyIndex] = new Product(name, price, stock);
            System.out.println("[成功] 已成功新增商品：" + products[emptyIndex].getName());
            addCount++;
        } catch (NumberFormatException e) {
            System.out.println("[錯誤] 價格與庫存必須為整數數字！");
        } catch (IllegalArgumentException e) {
            System.out.println("[錯誤] 新增失敗：" + e.getMessage());
        }
    }

    // --- 輔助方法 5：出售商品 ---
    private static void sellProduct(Product[] products) {
        System.out.print("請輸入要出售的商品名稱: ");
        String name = scanner.nextLine().trim();
        int index = findProductIndexByName(products, name);

        if (index == -1) {
            System.out.println("[錯誤] 找不到該商品！");
            return;
        }

        try {
            System.out.print("請輸入出售數量: ");
            int amount = Integer.parseInt(scanner.nextLine().trim());
            
            // 呼叫 Product 物件內部方法，不直接修改欄位
            products[index].decreaseStock(amount);
            System.out.printf("[成功] 已售出 %d 件 %s。剩餘庫存: %d%n", 
                    amount, products[index].getName(), products[index].getStock());
            sellCount++;
        } catch (NumberFormatException e) {
            System.out.println("[錯誤] 數量必須為整數數字！");
        } catch (IllegalArgumentException e) {
            System.out.println("[錯誤] 出售失敗：" + e.getMessage());
        }
    }

    // --- 輔助方法 6：補充庫存 ---
    private static void restockProduct(Product[] products) {
        System.out.print("請輸入要補貨的商品名稱: ");
        String name = scanner.nextLine().trim();
        int index = findProductIndexByName(products, name);

        if (index == -1) {
            System.out.println("[錯誤] 找不到該商品！");
            return;
        }

        try {
            System.out.print("請輸入補貨數量: ");
            int amount = Integer.parseInt(scanner.nextLine().trim());
            
            products[index].increaseStock(amount);
            System.out.printf("[成功] 已為 %s 補貨 %d 件。目前庫存: %d%n", 
                    products[index].getName(), amount, products[index].getStock());
            restockCount++;
        } catch (NumberFormatException e) {
            System.out.println("[錯誤] 數量必須為整數數字！");
        } catch (IllegalArgumentException e) {
            System.out.println("[錯誤] 補貨失敗：" + e.getMessage());
        }
    }

    // --- 輔助方法 7：修改商品價格 ---
    private static void updateProductPrice(Product[] products) {
        System.out.print("請輸入要修改價格的商品名稱: ");
        String name = scanner.nextLine().trim();
        int index = findProductIndexByName(products, name);

        if (index == -1) {
            System.out.println("[錯誤] 找不到該商品！");
            return;
        }

        try {
            System.out.print("請輸入新價格: ");
            int newPrice = Integer.parseInt(scanner.nextLine().trim());
            
            products[index].setPrice(newPrice);
            System.out.printf("[成功] %s 價格已更新為: %d 元%n", products[index].getName(), products[index].getPrice());
            priceUpdateCount++;
        } catch (NumberFormatException e) {
            System.out.println("[錯誤] 價格必須為整數數字！");
        } catch (IllegalArgumentException e) {
            System.out.println("[錯誤] 修改價格失敗：" + e.getMessage());
        }
    }

    // --- 輔助方法 8：顯示低庫存商品 ---
    private static void showLowStockProducts(Product[] products, int threshold) {
        System.out.printf("\n=== 低庫存警戒商品 (庫存少於 %d) ===%n", threshold);
        boolean found = false;
        for (Product p : products) {
            if (p != null && p.getStock() < threshold) {
                System.out.println(p.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("太棒了！目前沒有商品庫存不足。");
        }
    }

    // --- 輔助方法 9：顯示全部庫存總價值 ---
    private static void showTotalInventoryValue(Product[] products) {
        int totalValue = 0;
        for (Product p : products) {
            if (p != null) {
                totalValue += p.getTotalValue();
            }
        }
        System.out.printf("\n[統計] 目前全店庫存總價值為: %,d 元%n", totalValue);
    }

    // --- 輔助內部工具方法 A：依名稱尋找商品在陣列中的索引 ---
    private static int findProductIndexByName(Product[] products, String name) {
        if (name == null) return -1;
        String cleanName = name.trim();
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getName().equalsIgnoreCase(cleanName)) {
                return i;
            }
        }
        return -1;
    }

    // --- 輔助內部工具方法 B：尋找陣列第一個空欄位 ---
    private static int findFirstEmptyIndex(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                return i;
            }
        }
        return -1;
    }

    // --- 輔助內部工具方法 C：結束程式並輸出摘要 ---
    private static void showSummaryAndExit() {
        System.out.println("\n=====================================");
        System.out.println("       系統已安全結束。操作摘要如下：       ");
        System.out.println("=====================================");
        System.out.printf(" * 新增商品次數: %d 次%n", addCount);
        System.out.printf(" * 出售交易次數: %d 次%n", sellCount);
        System.out.printf(" * 補貨操作次數: %d 次%n", restockCount);
        System.out.printf(" * 修改價格次數: %d 次%n", priceUpdateCount);
        System.out.println("=====================================");
        System.out.println("謝謝使用！祝您今天生意興隆。");
    }
}