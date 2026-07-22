import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        cart.add(new CartItem("P001", "無線滑鼠", 590, 2));
        cart.add(new CartItem("P002", "機械鍵盤", 2490, 1));

        boolean running = true;
        System.out.println("====== 歡迎使用線上購物車系統 ======");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addOrUpdateItem();
                    break;
                case "2":
                    updateQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    showCart();
                    break;
                case "5":
                    calculateTotal();
                    break;
                case "6":
                    System.out.println("感謝使用購物車，系統已安全退出！");
                    running = false;
                    break;
                default:
                    System.out.println("[錯誤] 無效的選項，請重新選擇！");
            }
            System.out.println(); 
        }

        scanner.close();
    }
    private static void printMenu() {
        System.out.println("------------------------------------");
        System.out.println("1. 加入商品 (若已存在則累加數量)");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 顯示購物車內容");
        System.out.println("5. 計算結帳總額");
        System.out.println("6. 離開系統");
        System.out.println("------------------------------------");
    }
    private static void addOrUpdateItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("[失敗] 商品代碼不可為空！");
            return;
        }

        CartItem existingItem = findItemById(id);

        try {
            if (existingItem != null) {
                System.out.println("[提示] 購物車內已存在該商品（" + existingItem.getName() + "）。");
                System.out.print("請輸入要要額外增加的數量: ");
                int addQty = Integer.parseInt(scanner.nextLine().trim());

                existingItem.addQuantity(addQty);
                System.out.printf("[成功] 已為 %s 增加 %d 件，目前總數量: %d%n", 
                        existingItem.getName(), addQty, existingItem.getQuantity());
            }
            else {
                System.out.print("請輸入商品名稱: ");
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("[失敗] 商品名稱不可為空！");
                    return;
                }

                System.out.print("請輸入單價: ");
                int price = Integer.parseInt(scanner.nextLine().trim());

                System.out.print("請輸入數量: ");
                int quantity = Integer.parseInt(scanner.nextLine().trim());

                CartItem newItem = new CartItem(id, name, price, quantity);
                cart.add(newItem);
                System.out.println("[成功] 已將 " + name + " 加入購物車！");
            }
        } catch (NumberFormatException e) {
            System.out.println("[錯誤] 價格與數量必須為有效的整數數字！");
        } catch (IllegalArgumentException e) {
            System.out.println("[錯誤] 操作失敗：" + e.getMessage());
        }
    }
    private static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);
        if (item == null) {
            System.out.println("[失敗] 購物車中找不到代碼為 \"" + id + "\" 的商品！");
            return;
        }

        System.out.printf("目前 %s 的數量為: %d%n", item.getName(), item.getQuantity());
        System.out.print("請輸入新的數量: ");

        try {
            int newQty = Integer.parseInt(scanner.nextLine().trim());
            // 若 newQty <= 0 會引發 CartItem 內部的 IllegalArgumentException
            item.setQuantity(newQty);
            System.out.printf("[成功] 已將 %s 的數量更新為: %d%n", item.getName(), item.getQuantity());
        } catch (NumberFormatException e) {
            System.out.println("[錯誤] 數量必須為整數數字！");
        } catch (IllegalArgumentException e) {
            System.out.println("[錯誤] 修改失敗：" + e.getMessage());
        }
    }
    private static void removeItem() {
        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);
        if (item == null) {
            System.out.println("[失敗] 購物車中找不到代碼為 \"" + id + "\" 的商品！");
            return;
        }

        cart.remove(item);
        System.out.println("[成功] 已將 " + item.getName() + " 從購物車中移除！");
    }
    private static void showCart() {
        System.out.println("\n=== 目前購物車內容 ===");
        if (cart.isEmpty()) {
            System.out.println("（購物車內沒有任何商品）");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, cart.get(i).toString());
        }
    }
    private static void calculateTotal() {
        System.out.println("\n=== 購物車結帳小計 ===");
        if (cart.isEmpty()) {
            System.out.println("購物車為空，總金額: 0 元");
            return;
        }

        int grandTotal = 0;
        for (CartItem item : cart) {
            grandTotal += item.getSubtotal();
        }

        showCart();
        System.out.println("------------------------------------");
        System.out.printf(">>> 應付總金額：%,d 元 <<<%n", grandTotal);
    }
    private static CartItem findItemById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        String cleanId = id.trim();
        for (CartItem item : cart) {
            if (item.getId().equalsIgnoreCase(cleanId)) {
                return item;
            }
        }
        return null;
    }
}