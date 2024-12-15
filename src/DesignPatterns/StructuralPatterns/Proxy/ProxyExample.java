package StructuralPatterns.Proxy;

public class ProxyExample {
    public static void main(String[] args) {
        Account myAccount = new Account(100000,"1234");

        Bank storeATM = new ATM();
        storeATM.deposit(myAccount,50000);
        storeATM.withdraw(myAccount,1000,"1000");
        storeATM.withdraw(myAccount,77777,"1234");

        Bank serverHacking = new BankServer();
        serverHacking.deposit(myAccount,1000000000);
    }
}

class Account{
    private int money;
    private String PIN;

    public Account(int money, String PIN){
        this.money = money;
        this.PIN = PIN;
    }
    public int getMoney() {
        return money;
    }
    public void deposit(int money){
        this.money += money;
    }
    public void withdraw(int money){
        this.money -= money;
    }
    public boolean validPIN(String inputPin){
        return (this.PIN).equals(inputPin);
    }
}

interface Bank{
    void deposit(Account account, int requestMoney);
    int withdraw(Account account, int requestMoney, String PIN);
}

class BankServer implements Bank{

    @Override
    public void deposit(Account account, int requestMoney) {
        account.deposit(requestMoney);
        System.out.println("입금이 완료되었습니다.");
        System.out.println("현재 금액 : " + account.getMoney());
    }

    @Override
    public int withdraw(Account account, int requestMoney, String PIN) {
        account.withdraw(requestMoney);
        System.out.println("출금이 완료되었습니다.");
        System.out.println("현재 금액 : " + account.getMoney());
        return requestMoney;
    }
}

class ATM implements Bank{

    private final BankServer bankServer = new BankServer();

    @Override
    public int withdraw(Account account, int requestMoney, String PIN) {
        if(account.validPIN(PIN)){
            return bankServer.withdraw(account, requestMoney, PIN);
        }
        System.out.println("비밀번호가 일치하지 않습니다.");
        return 0;
    }

    @Override
    public void deposit(Account account, int requestMoney) {
        bankServer.deposit(account, requestMoney);
    }
}