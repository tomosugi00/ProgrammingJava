package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class ex11 extends Frame implements Runnable{
        static int h;           //��������ϐ���錾
        static int m;           //��������ϐ���錾
        static int s;           //�b������ϐ���錾

        boolean a = true;
        
        //�C���X�^���X��
        static ex11 f = new ex11();
        static Thread th = new Thread(f);
        Calendar now = Calendar.getInstance();  
    public static void main(String args[]){

        //�t���[���쐬
        f.setSize(200, 100);
        f.setVisible(true);
        f.addWindowListener(new Ada());

        th.start();   //�X���b�h�X�^�[�g
    }
    public void run(){
        while(a==true){
              h = now.getInstance().get(now.HOUR_OF_DAY); //������
              m = now.getInstance().get(now.MINUTE);      //������
              s= now.getInstance().get(now.SECOND);       //�b����
              repaint();

              try{
                  th.sleep(1000);  //�X���[�v�P�b
              }catch(InterruptedException e){
              }               
        }
    }
    public void paint(Graphics g)
    {
        
        g.drawString(h+":"+m+":"+s,50,59);
    }
    

}
class Ada extends WindowAdapter
{
    public void windowClosing(WindowEvent e){   //�~�������ꂽ�Ƃ��̏���
       System.exit(0);
    }
}