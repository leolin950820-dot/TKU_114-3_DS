public class Contact {
    private String id;       
    private String name;     
    private String phone;    
    private String email;    
    public Contact(String id, String name, String phone, String email) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("代碼不可為空！");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不可為空！");
        }
        this.id = id.trim();
        this.name = name.trim();
        setPhone(phone);
        setEmail(email);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("電話不可為空！");
        }
        this.phone = phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "" : email.trim();
    }
    @Override
    public String toString() {
        return String.format("代碼: %-8s | 姓名: %-10s | 電話: %-15s | Email: %s", 
                id, name, phone, email.isEmpty() ? "(未提供)" : email);
    }
}