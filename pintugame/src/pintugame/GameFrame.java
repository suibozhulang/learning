package pintugame;

/*GamePanel类-----游戏面板设计类，真正的游戏思想从此开始

主要实现的功能有：

1.初始化面板按钮数组，将图像转化成图标然后存入按钮中；
2.打乱数组面板中的按钮排序，实现游戏娱乐功能；
3.每个按钮添加监听机制，实现点击按钮后的移动功能；
*/
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class GameFrame extends JFrame {
 public JPanel pane1 = new JPanel();
 public JButton button1 = new JButton("游戏开始");
 public JButton button2 = new JButton("游戏结束");
 public GameFrame()
 {
  super("拼图游戏");
  pane1.setLayout(new FlowLayout());
  pane1.add(button1);
  pane1.add(button2);
  Container con = this.getContentPane();
  con.add(pane1,BorderLayout.NORTH);
  GamePanel gamepane = new GamePanel();
  con.add(gamepane,BorderLayout.CENTER);
  this.setBounds(100, 100, 680, 600);
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  button1.addActionListener(new ActionListener()
  {
   public void actionPerformed(final ActionEvent e)
   {
    gamepane.OutOfOrder();
   }
  });
  button2.addActionListener(new ActionListener()
  {
   public void actionPerformed(final ActionEvent e)
   {
    System.exit(1);
   }
  });
 }
 
 public static void main(String[] args) {
  new GameFrame();  }
}