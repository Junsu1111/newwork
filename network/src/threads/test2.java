package threads;

import javax.swing.*;


class test2 {
    public static void main(String[] args) {
        Runnable r=new Thread_ex2();
        Thread t=new Thread(r);
        t.start();
        String input= JOptionPane.showInputDialog("�ƹ� ���̳� �Է��ϼ���.");
        System.out.println("�Է��Ͻ� ���� "+input+"�Դϴ�.");
    }
}
class Thread_ex2 implements Runnable{
    @Override
    public void run() {
        for(int i=10;i>0;i--){
            System.out.println(i);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
