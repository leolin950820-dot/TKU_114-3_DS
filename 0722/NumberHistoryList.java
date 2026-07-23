public class NumberHistoryList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("====== 開始 8 次操作測試 ======\n");
        System.out.println("【操作 1】尾端新增 20");
        list.addLast(20);
        list.printListAndStats();
        System.out.println("\n【操作 2】前端新增 10");
        list.addFirst(10);
        list.printListAndStats();
        System.out.println("\n【操作 3】尾端新增 40");
        list.addLast(40);
        list.printListAndStats();
        System.out.println("\n【操作 4】前端新增 5");
        list.addFirst(5);
        list.printListAndStats();
        System.out.println("\n【操作 5】搜尋測試 (搜尋 20 與 99)");
        list.search(20);
        list.search(99);
        System.out.println("\n【操作 6】刪除中間節點 20");
        list.removeValue(20);
        list.printListAndStats();
        System.out.println("\n【操作 7】刪除頭節點 5");
        list.removeValue(5);
        list.printListAndStats();
        System.out.println("\n【操作 8】刪除測試 (刪除 99,並陸續清空 10、40)");
        list.removeValue(99); 
        list.removeValue(10);  
        list.removeValue(40);  
        list.printListAndStats(); 
        System.out.println("\n【邊界測試】對空串列進行刪除與搜尋");
        list.removeValue(100);
        list.search(100);
    }
    public void addFirst(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void addLast(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public boolean search(int val) {
        if (head == null) {
            System.out.println("[搜尋結果] 串列為空，找不到數字 " + val);
            return false;
        }

        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == val) {
                System.out.println("[搜尋結果] 找到數字 " + val + "，部位於索引 (Index) " + index);
                return true;
            }
            current = current.next;
            index++;
        }

        System.out.println("[搜尋結果] 串列中找不到數字 " + val);
        return false;
    }
    public boolean removeValue(int val) {
        if (head == null) {
            System.out.println("[刪除失敗] 串列為空，無法刪除 " + val);
            return false;
        }
        if (head.data == val) {
            head = head.next;
            size--;
            System.out.println("[刪除成功] 已移除頭節點 " + val);
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.data != val) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到要刪除的數字 " + val);
            return false;
        }

        current.next = current.next.next;
        size--;
        System.out.println("[刪除成功] 已移除節點 " + val);
        return true;
    }
    public void printListAndStats() {
        System.out.println("----------------------------------------");
        if (head == null) {
            System.out.println("串列內容 : [ 空串列 (null) ]");
            System.out.println("統計數據 :");
            System.out.println(" * 大小 (Size)  : 0");
            System.out.println(" * 總和 (Sum)   : 0");
            System.out.println(" * 最大值 (Max) : N/A (串列為空)");
            System.out.println(" * 最小值 (Min) : N/A (串列為空)");
            System.out.println("----------------------------------------");
            return;
        }

        Node current = head;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        System.out.print("串列內容 : ");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            
            sum += current.data;
            if (current.data > max) max = current.data;
            if (current.data < min) min = current.data;

            current = current.next;
        }
        System.out.println();

        System.out.println("統計數據 :");
        System.out.println(" * 大小 (Size)  : " + size);
        System.out.println(" * 總和 (Sum)   : " + sum);
        System.out.println(" * 最大值 (Max) : " + max);
        System.out.println(" * 最小值 (Min) : " + min);
        System.out.println("----------------------------------------");
    }
}