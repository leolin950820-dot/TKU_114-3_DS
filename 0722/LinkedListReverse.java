public class LinkedListReverse {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1:多節點串列反轉 (10 -> 20 -> 30 -> 40) ===");
        Node head1 = createList(new int[]{10, 20, 30, 40});
        printList("反轉前", head1);
        head1 = reverse(head1);
        printList("反轉後", head1);

        System.out.println("\n=== 測試 2:單一節點串列反轉 (10) ===");
        Node head2 = createList(new int[]{10});
        printList("反轉前", head2);
        head2 = reverse(head2);
        printList("反轉後", head2);

        System.out.println("\n=== 測試 3:邊界條件（空串列反轉） ===");
        Node head3 = null;
        printList("反轉前", head3);
        head3 = reverse(head3);
        printList("反轉後", head3);
    }
    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;      
        Node current = head;   
        Node next = null;     

        while (current != null) {
            next = current.next; 
            current.next = prev; 
            prev = current;      
            current = next;     
        }
        return prev;
    }
    private static Node createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }
    private static void printList(String label, Node head) {
        if (head == null) {
            System.out.println(label + ":[ 空串列 (null) ]");
            return;
        }

        Node current = head;
        System.out.print(label + ":");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }
}