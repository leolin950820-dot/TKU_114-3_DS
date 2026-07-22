import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {
        contactList.add(new Contact("C001", "王小明", "0912-345-678", "ming@example.com"));
        contactList.add(new Contact("C002", "陳美麗", "0987-654-321", "mary@example.com"));
        contactList.add(new Contact("C003", "John Doe", "0911-111-222", "john@example.com"));

        boolean running = true;
        System.out.println("====== 歡迎使用聯絡人管理系統 ======");

        while (running) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    modifyPhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listAllContacts();
                    break;
                case "6":
                    System.out.println("感謝使用，系統已安全結束。");
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
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人 (依代碼或姓名)");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 顯示完整聯絡人清單");
        System.out.println("6. 結束程式");
        System.out.println("------------------------------------");
    }
    private static void addContact() {
        System.out.print("請輸入代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("[失敗] 代碼不可為空！");
            return;
        }
        if (findContactById(id) != null) {
            System.out.println("[失敗] 代碼 \"" + id + "\" 已存在，不可重複！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("[失敗] 姓名不可為空！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入 Email (選填，可直接按 Enter 略過): ");
        String email = scanner.nextLine().trim();

        try {
            Contact contact = new Contact(id, name, phone, email);
            contactList.add(contact);
            System.out.println("[成功] 已成功新增聯絡人：" + name);
        } catch (IllegalArgumentException e) {
            System.out.println("[失敗] " + e.getMessage());
        }
    }
    private static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名關鍵字: ");
        String query = scanner.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("[錯誤] 搜尋條件不可為空！");
            return;
        }

        System.out.println("\n=== 搜尋結果 ===");
        boolean found = false;
        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(query) || 
                c.getName().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(c.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合 \"" + query + "\" 的聯絡人。");
        }
    }
    private static void modifyPhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact contact = findContactById(id);
        if (contact == null) {
            System.out.println("[失敗] 找不到代碼為 \"" + id + "\" 的聯絡人！");
            return;
        }

        System.out.println("目前聯絡人資訊: " + contact.toString());
        System.out.print("請輸入新的電話號碼: ");
        String newPhone = scanner.nextLine().trim();

        try {
            contact.setPhone(newPhone);
            System.out.println("[成功] 已成功將 " + contact.getName() + " 的電話更新為：" + newPhone);
        } catch (IllegalArgumentException e) {
            System.out.println("[失敗] " + e.getMessage());
        }
    }
    private static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact contact = findContactById(id);
        if (contact == null) {
            System.out.println("[失敗] 找不到代碼為 \"" + id + "\" 的聯絡人，無法刪除！");
            return;
        }

        contactList.remove(contact);
        System.out.println("[成功] 已成功刪除聯絡人：" + contact.getName() + " (" + id + ")");
    }
    private static void listAllContacts() {
        System.out.println("\n=== 完整聯絡人清單 ===");
        if (contactList.isEmpty()) {
            System.out.println("（目前通訊錄為空）");
            return;
        }

        for (int i = 0; i < contactList.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, contactList.get(i).toString());
        }
        System.out.println("總計: " + contactList.size() + " 位聯絡人");
    }
    private static Contact findContactById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        String cleanId = id.trim();
        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(cleanId)) {
                return c;
            }
        }
        return null;
    }
}