import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> nameList = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=== 歡迎使用名單管理系統 ===");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addName();
                    break;
                case "2":
                    searchName();
                    break;
                case "3":
                    modifyName();
                    break;
                case "4":
                    deleteName();
                    break;
                case "5":
                    listAllNames();
                    break;
                case "6":
                    System.out.println("感謝使用，系統已離開。");
                    running = false;
                    break;
                default:
                    System.out.println("[錯誤] 無效的選項，請重新輸入！");
            }
            System.out.println();
        }

        scanner.close();
    }
    private static void printMenu() {
        System.out.println("------------------------------------");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("6. 離開系統");
        System.out.println("------------------------------------");
    }
    private static void addName() {
        System.out.print("請輸入要新增的姓名: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("[失敗] 姓名不可為空！");
            return;
        }
        if (findNameIndex(input) != -1) {
            System.out.println("[失敗] 名單中已存在相同的姓名（不限大小寫）！");
            return;
        }

        nameList.add(input);
        System.out.println("[成功] 已新增姓名：" + input);
    }
    private static void searchName() {
        System.out.print("請輸入要搜尋的姓名: ");
        String query = scanner.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("[錯誤] 搜尋條件不可為空！");
            return;
        }

        int index = findNameIndex(query);
        if (index != -1) {
            System.out.printf("[結果] 找到姓名：「%s」（位於第 %d 筆記錄）%n", nameList.get(index), index + 1);
        } else {
            System.out.println("[結果] 找不到姓名：「" + query + "」");
        }
    }
    private static void modifyName() {
        System.out.print("請輸入要修改的原姓名: ");
        String oldName = scanner.nextLine().trim();

        int index = findNameIndex(oldName);
        if (index == -1) {
            System.out.println("[失敗] 找不到原姓名：「" + oldName + "」，無法修改！");
            return;
        }

        System.out.print("請輸入新的姓名: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("[失敗] 新姓名不可為空！");
            return;
        }
        int duplicateIndex = findNameIndex(newName);
        if (duplicateIndex != -1 && duplicateIndex != index) {
            System.out.println("[失敗] 新姓名與名單中既有項目重複！");
            return;
        }

        String targetOriginal = nameList.get(index);
        nameList.set(index, newName);
        System.out.printf("[成功] 已將「%s」修改為「%s」%n", targetOriginal, newName);
    }
    private static void deleteName() {
        System.out.print("請輸入要刪除的姓名: ");
        String target = scanner.nextLine().trim();

        if (target.isEmpty()) {
            System.out.println("[錯誤] 請輸入有效的姓名！");
            return;
        }

        int index = findNameIndex(target);
        if (index != -1) {
            String removedName = nameList.remove(index);
            System.out.println("[成功] 已成功刪除姓名：「" + removedName + "」");
        } else {
            System.out.println("[失敗] 找不到姓名：「" + target + "」，無法刪除！");
        }
    }
    private static void listAllNames() {
        System.out.println("\n=== 目前名單清單 ===");
        if (nameList.isEmpty()) {
            System.out.println("（目前名單為空）");
            return;
        }

        for (int i = 0; i < nameList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, nameList.get(i));
        }
        System.out.println("總計: " + nameList.size() + " 人");
    }
    private static int findNameIndex(String targetName) {
        if (targetName == null || targetName.trim().isEmpty()) {
            return -1;
        }

        String cleanTarget = targetName.trim();
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(cleanTarget)) {
                return i; 
            }
        }
        return -1;
    }
}