import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Equipment> equipmentList = new ArrayList<>();

    public static void main(String[] args) {
        equipmentList.add(new Equipment("EQ001", "Projector"));
        equipmentList.add(new Equipment("EQ002", "Laptop"));
        equipmentList.add(new Equipment("EQ003", "Wireless Mic"));

        boolean running = true;
        System.out.println("=== 歡迎使用設備借還管理系統 ===");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEquipment();
                    break;
                case "2":
                    searchById();
                    break;
                case "3":
                    borrowEquipment();
                    break;
                case "4":
                    returnEquipment();
                    break;
                case "5":
                    listAvailableEquipment();
                    break;
                case "6":
                    System.out.println("感謝使用，系統已安全離開。");
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
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出所有「可借用」設備");
        System.out.println("6. 離開系統");
        System.out.println("------------------------------------");
    }
    private static void addEquipment() {
        System.out.print("請輸入設備代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("[失敗] 設備代碼不可為空！");
            return;
        }
        if (findEquipmentById(id) != null) {
            System.out.println("[失敗] 設備代碼 \"" + id + "\" 已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("[失敗] 設備名稱不可為空！");
            return;
        }

        equipmentList.add(new Equipment(id, name));
        System.out.println("[成功] 已新增設備：" + name + " (代碼: " + id + ")");
    }
    private static void searchById() {
        System.out.print("請輸入要搜尋的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq != null) {
            System.out.println("[搜尋結果] " + eq.toString());
        } else {
            System.out.println("[結果] 找不到代碼為 \"" + id + "\" 的設備。");
        }
    }
    private static void borrowEquipment() {
        System.out.print("請輸入要借出的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("[失敗] 找不到該設備！");
            return;
        }

        if (eq.borrowEquipment()) {
            System.out.println("[成功] 設備 \"" + eq.getName() + "\" 已成功借出！");
        } else {
            System.out.println("[失敗] 該設備目前已被借出，無法重複借用！");
        }
    }
    private static void returnEquipment() {
        System.out.print("請輸入要歸還的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("[失敗] 找不到該設備！");
            return;
        }

        if (eq.returnEquipment()) {
            System.out.println("[成功] 設備 \"" + eq.getName() + "\" 已成功歸還！");
        } else {
            System.out.println("[提示] 該設備原本就是「可借用」狀態，無須歸還。");
        }
    }
    private static void listAvailableEquipment() {
        System.out.println("\n=== 目前可借用設備清單 ===");
        boolean found = false;

        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                System.out.println(eq.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有可借用的設備。");
        }
    }
    private static Equipment findEquipmentById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        String cleanId = id.trim();
        for (Equipment eq : equipmentList) {
            if (eq.getId().equalsIgnoreCase(cleanId)) {
                return eq;
            }
        }
        return null;
    }
}