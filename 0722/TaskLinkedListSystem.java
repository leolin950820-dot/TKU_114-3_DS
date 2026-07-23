public class TaskLinkedListSystem {

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("====== 工作項目系統測試 ======\n");
        System.out.println("=== 1. 新增一般工作 ===");
        taskList.addNormalTask("T01", "撰寫 Java 課後作業");
        taskList.addNormalTask("T02", "整理房間與垃圾");
        taskList.addNormalTask("T03", "準備期末考複習");
        taskList.printAllTasks();
        System.out.println("\n=== 2. 插隊新增緊急工作 ===");
        taskList.addUrgentTask("T00", "修復系統嚴重 Bug (緊急!)");
        taskList.printAllTasks();
        System.out.println("\n=== 3. 標記工作完成 ===");
        taskList.completeTask("T00"); 
        taskList.completeTask("T02"); 
        System.out.println("\n=== 4. 列出未完成工作 ===");
        taskList.printPendingTasks();
        System.out.println("\n=== 5. 刪除工作測試 ===");
        taskList.deleteTask("T00"); 
        taskList.deleteTask("T03");
        taskList.printAllTasks();
        System.out.println("\n=== 6. 找不到與重複代碼測試 ===");
        taskList.addNormalTask("T01", "重複代碼測試"); 
        taskList.completeTask("T99");
        taskList.deleteTask("T99");
        System.out.println("\n=== 7. 清空清單與空串列測試 ===");
        taskList.deleteTask("T01"); 
        taskList.printPendingTasks();
        taskList.completeTask("T01"); 
        taskList.deleteTask("T01");  
    }
}