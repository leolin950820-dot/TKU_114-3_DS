public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount first = new BankAccount("A001", "Amy", 5000);
        BankAccount second = new BankAccount("A002", "Ben", 1000);

        System.out.println("存款成功：" + first.deposit(500));
        System.out.println("提款成功：" + second.withdraw(300));
        System.out.println("轉帳成功：" + first.transferTo(second, 2000));
        System.out.println("超額轉帳：" + second.transferTo(first, 10000));

        System.out.println(first);
        System.out.println(second);
    }
}