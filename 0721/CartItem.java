public class CartItem {
    private String id;      
    private String name;     
    private int price;       
    private int quantity;   
    public CartItem(String id, String name, int price, int quantity) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("商品代碼不可為空！");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("商品名稱不可為空！");
        }
        this.id = id.trim();
        this.name = name.trim();
        setPrice(price);
        setQuantity(quantity);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("單價必須大於 0!");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("數量必須大於 0！");
        }
        this.quantity = quantity;
    }
    public void addQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("新增數量必須大於 0！");
        }
        this.quantity += amount;
    }
    public int getSubtotal() {
        return this.price * this.quantity;
    }
    @Override
    public String toString() {
        return String.format("代碼: %-8s | 名稱: %-12s | 單價: %-6d | 數量: %-3d | 小計: %d 元", 
                id, name, price, quantity, getSubtotal());
    }
}