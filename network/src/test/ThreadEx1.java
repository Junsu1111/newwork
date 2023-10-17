package test;

public class ThreadEx1 {
    public static void main(String[] args) {
        Runnable r=new RunnableEx();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account{
    private int balance=0;
    public Account(int balance){
        this.balance=balance;
    }
    public int getBalance(){
        return balance;
    }

    public void withdraw(int money){
        synchronized (this) {
            if(balance>0){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                balance-=money;
            }
        }
    }
}


class RunnableEx implements Runnable{
    Account acc=new Account(1000);

    @Override
    public void run() {
        while(acc.getBalance()>0){
            int money=(int)(Math.random()*3+1)*100;
            acc.withdraw(money);
            System.out.println("Balance: "+acc.getBalance());
        }
    }
}