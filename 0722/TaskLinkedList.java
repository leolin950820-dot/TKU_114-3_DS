public class TaskLinkedList {
    private TaskNode head;
    private int totalTasks;

    public TaskLinkedList() {
        this.head = null;
        this.totalTasks = 0;
    }
    public boolean containsId(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.getId().equalsIgnoreCase(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public boolean addUrgentTask(String id, String description) {
        if (!validateInput(id, description)) return false;

        TaskNode newNode = new TaskNode(id.trim(), description.trim());
        newNode.next = head;
        head = newNode;
        totalTasks++;

        System.out.println("[🚨 緊急工作新增成功] 已插隊至前端: " + newNode);
        return true;
    }
    public boolean addNormalTask(String id, String description) {
        if (!validateInput(id, description)) return false;

        TaskNode newNode = new TaskNode(id.trim(), description.trim());

        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        totalTasks++;
        System.out.println("[📋 一般工作新增成功] 已加入至尾端: " + newNode);
        return true;
    }
    private boolean validateInput(String id, String description) {
        if (id == null || id.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            System.out.println("[新增失敗] 工作代碼與說明不可為空！");
            return false;
        }
        String cleanId = id.trim();
        if (containsId(cleanId)) {
            System.out.println("[新增失敗] 工作代碼 \"" + cleanId + "\" 已存在！");
            return false;
        }
        return true;
    }
    public boolean completeTask(String id) {
        if (head == null) {
            System.out.println("[操作失敗] 目前沒有任何工作！");
            return false;
        }

        TaskNode target = findTaskById(id);
        if (target == null) {
            System.out.println("[操作失敗] 找不到代碼為 \"" + id + "\" 的工作。");
            return false;
        }

        if (target.isCompleted()) {
            System.out.println("[提示] 工作 \"" + target.getDescription() + "\" 本來就已經是完成狀態。");
            return true;
        }

        target.setCompleted(true);
        System.out.println("[🎉 完成工作] " + target);
        return true;
    }
    public boolean deleteTask(String id) {
        if (head == null) {
            System.out.println("[刪除失敗] 目前沒有任何工作可供刪除！");
            return false;
        }

        String cleanId = id.trim();
        if (head.getId().equalsIgnoreCase(cleanId)) {
            System.out.println("[刪除成功] 已移除頭端工作: " + head);
            head = head.next;
            totalTasks--;
            return true;
        }
        TaskNode current = head;
        while (current.next != null && !current.next.getId().equalsIgnoreCase(cleanId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到代碼為 \"" + cleanId + "\" 的工作！");
            return false;
        }

        System.out.println("[刪除成功] 已移除工作: " + current.next);
        current.next = current.next.next;
        totalTasks--;
        return true;
    }
    public void printPendingTasks() {
        System.out.println("\n----------------------------------------");
        System.out.println("【 ⏳ 未完成工作項目清單 ⏳ 】");

        if (head == null) {
            System.out.println("（目前沒有任何工作）");
            printSummary();
            return;
        }

        TaskNode current = head;
        int pendingCount = 0;

        while (current != null) {
            if (!current.isCompleted()) {
                pendingCount++;
                System.out.println(" " + pendingCount + ". " + current);
            }
            current = current.next;
        }

        if (pendingCount == 0) {
            System.out.println("（所有工作均已完成！太棒了！👏）");
        }

        printSummary();
    }
    public void printAllTasks() {
        System.out.println("\n----------------------------------------");
        System.out.println("【 📌 所有工作項目完整清單 📌 】");

        if (head == null) {
            System.out.println("（目前沒有任何工作）");
            printSummary();
            return;
        }

        TaskNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(" " + index + ". " + current);
            current = current.next;
            index++;
        }

        printSummary();
    }
    private void printSummary() {
        int pendingCount = getPendingCount();
        System.out.println("----------------------------------------");
        System.out.println("【統計數據】 總工作數: " + totalTasks + " 項 | 未完成數量: " + pendingCount + " 項");
        System.out.println("----------------------------------------");
    }
    public int getPendingCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted()) {
                count++;
            }
            current = current.next;
        }
        return count;
    }
    private TaskNode findTaskById(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.getId().equalsIgnoreCase(id.trim())) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}