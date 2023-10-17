package test;

import javax.swing.*;

public class ThreadStopTest {
    public static void main(String[] args) {
        MyThread t=new MyThread();
        t.start();
        String input= JOptionPane.showInputDialog("�ƹ����̳� �Է��ϼ���.");
        System.out.println("�Է��Ͻ� ���� "+input+"�Դϴ�.");
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
                System.out.println("���ڴ� ���߿� ȣ���.");
                interrupt();
            }
        }
        System.out.println("ī��Ʈ�� ����Ǿ����ϴ�.");
    }
}

