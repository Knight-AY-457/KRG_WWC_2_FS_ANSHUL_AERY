class BankAccount {
    private String accNo;
    private String name;
    private double balance;

    public BankAccount(String a, String n, double b) {
        accNo = a;
        name = n;
        balance = b;
    }

    public void deposit(double amt) {
        if (amt > 0) balance = balance + amt;
    }

    public void withdraw(double amt) {
        if (amt > 0 && amt <= balance) balance = balance - amt;
        else System.out.println("Not enough balance");
    }

    public double getBalance() {
        return balance;
    }

    public void show() {
        System.out.println("\nAccount No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {
    private double rate;

    public SavingsAccount(String a, String n, double b, double r) {
        super(a, n, b);
        rate = r;
    }

    public void withdraw(double amt) {
        if (amt <= getBalance()) super.withdraw(amt);
        else System.out.println("Cannot withdraw more than balance");
    }

    public void addInterest() {
        double i = getBalance() * rate / 100;
        deposit(i);
    }
}

public class Task1 {
    public static void main(String[] args) {

        BankAccount b = new BankAccount("A1", "Rohan", 2000);
        b.deposit(500);
        b.withdraw(800);
        b.show();

        SavingsAccount s = new SavingsAccount("S1", "Priya", 5000, 5);
        s.deposit(1000);
        s.withdraw(7000);
        s.withdraw(2000);
        s.addInterest();
        s.show();
    }
}