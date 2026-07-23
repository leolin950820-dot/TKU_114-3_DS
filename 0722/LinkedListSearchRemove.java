public class LinkedListSearchRemove {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    private static Node head;

    public static void main(String[] args) {
        initList();
        System.out.println("=== 初始鏈結串列 ===");
        printList();

        System.out.println("\n=== 1. 測試 contains 搜尋 ===");
        System.out.println("搜尋 30: " + contains(30)); // true
        System.out.println("搜尋 99: " + contains(99)); // false

        System.out.println("\n=== 2. 測試刪除：中間節點 (30) ===");
        removeValue(30);
        printList();

        System.out.println("\n=== 3. 測試刪除：頭節點 Head (10) ===");
        removeValue(10);
        printList();

        System.out.println("\n=== 4. 測試刪除：最後節點 (40) ===");
        removeValue(40);
        printList();

        System.out.println("\n=== 5. 測試刪除：找不到的資料 (99) ===");
        removeValue(99);
        printList();

        System.out.println("\n=== 6. 測試刪除：清空串列至空串列 (刪除 20) ===");
        removeValue(20);
        printList();

        System.out.println("\n=== 7. 測試邊界條件：對空串列進行刪除與搜尋 ===");
        System.out.println("空串列搜尋 10: " + contains(10));
        removeValue(10);
    }
    private static void initList() {
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        head = n1;
    }
    public static boolean contains(int val) {
        Node current = head;
        while (current != null) {
            if (current.data == val) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public static boolean removeValue(int val) {
        if (head == null) {
            System.out.println("[刪除失敗] 串列目前為空，無法刪除 " + val);
            return false;
        }
        if (head.data == val) {
            head = head.next; 
            System.out.println("[成功刪除] 頭節點 " + val);
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.data != val) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("[刪除失敗] 串列中找不到資料 " + val);
            return false;
        }
        System.out.println("[成功刪除] 節點 " + val);
        current.next = current.next.next;
        return true;
    }
    public static void printList() {
        if (head == null) {
            System.out.println("當前串列：[ 空串列 (null) ]");
            return;
        }

        Node current = head;
        System.out.print("當前串列：");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }
}