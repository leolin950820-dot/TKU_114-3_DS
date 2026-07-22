public class Equipment {
    private String id;          
    private String name;       
    private boolean isAvailable; 
    public Equipment(String id, String name) {
        this.id = id.trim();
        this.name = name.trim();
        this.isAvailable = true;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public boolean borrowEquipment() {
        if (!isAvailable) {
            return false; 
        }
        this.isAvailable = false;
        return true;
    }
    public boolean returnEquipment() {
        if (isAvailable) {
            return false; 
        }
        this.isAvailable = true;
        return true;
    }
    @Override
    public String toString() {
        String status = isAvailable ? "可借用" : "已借出";
        return String.format("代碼: %-8s | 名稱: %-15s | 狀態: %s", id, name, status);
    }
}