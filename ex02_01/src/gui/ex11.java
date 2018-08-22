package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class ex11 extends Frame implements Runnable{
        static int h;           //時を入れる変数を宣言
        static int m;           //分を入れる変数を宣言
        static int s;           //秒を入れる変数を宣言

        boolean a = true;
        
        //インスタンス化
        static ex11 f = new ex11();
        static Thread th = new Thread(f);
        Calendar now = Calendar.getInstance();  
    public static void main(String args[]){

        //フレーム作成
        f.setSize(200, 100);
        f.setVisible(true);
        f.addWindowListener(new Ada());

        th.start();   //スレッドスタート
    }
    public void run(){
        while(a==true){
              h = now.getInstance().get(now.HOUR_OF_DAY); //時を代入
              m = now.getInstance().get(now.MINUTE);      //分を代入
              s= now.getInstance().get(now.SECOND);       //秒を代入
              repaint();

              try{
                  th.sleep(1000);  //スリープ１秒
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
    public void windowClosing(WindowEvent e){   //×を押されたときの処理
       System.exit(0);
    }
}