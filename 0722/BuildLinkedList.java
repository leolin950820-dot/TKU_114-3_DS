public class BuildLinkedList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        Node head = n1;

        System.out.println("=== 測試 1:正常鏈結串列走訪與統計 ===");
        printAndCalculate(head);

        System.out.println("\n=== 測試 2:尋找特定資料 ===");
        searchNode(head, 30); 
        searchNode(head, 99); 

        System.out.println("\n=== 測試 3:邊界條件（空串列處理） ===");
        Node emptyHead = null;
        printAndCalculate(emptyHead);
        searchNode(emptyHead, 10);
    }
    public static void printAndCalculate(Node head) {
        if (head == null) {
            System.out.println("[提示] 串列為空 (Empty List)。");
            System.out.println("節點數: 0, 總和: 0");
            return;
        }

        Node current = head;
        int count = 0;
        int sum = 0;

        System.out.print("串列內容：");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            sum += current.data;
            count++;
            current = current.next; 
        }
        System.out.println();
        System.out.println("總節點數: " + count);
        System.out.println("資料總和: " + sum);
    }
    public static boolean searchNode(Node head, int target) {
        if (head == null) {
            System.out.println("[搜尋失敗] 串列為空，無法搜尋數字 " + target);
            return false;
        }

        Node current = head;
        int position = 1;

        while (current != null) {
            if (current.data == target) {
                System.out.println("[搜尋成功] 找到數字 " + target + "，位於第 " + position + " 個節點。");
                return true;
            }
            current = current.next;
            position++;
        }

        System.out.println("[搜尋失敗] 串列中找不到數字 " + target + "。");
        return false;
    }
}