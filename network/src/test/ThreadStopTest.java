package test;

import javax.swing.*;

public class ThreadStopTest {
    public static void main(String[] args) {
        MyThread t=new MyThread();
        t.start();
        String input= JOptionPane.showInputDialog("아무값이나 입력하세요.");
        System.out.println("입력하신 값은 "+input+"입니다.");
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        int i=10;
        while(i!=0 && !isInterrupted()){
            System.out.println(i--);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("잠자는 도중에 호출됨.");
                interrupt();
            }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}

