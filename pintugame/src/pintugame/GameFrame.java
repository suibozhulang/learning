package pintugame;

/*GamePanel��-----��Ϸ�������࣬��������Ϸ˼��Ӵ˿�ʼ

��Ҫʵ�ֵĹ����У�

1.��ʼ����尴ť���飬��ͼ��ת����ͼ��Ȼ����밴ť�У�
2.������������еİ�ť����ʵ����Ϸ���ֹ��ܣ�
3.ÿ����ť��Ӽ������ƣ�ʵ�ֵ����ť����ƶ����ܣ�
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
 public JButton button1 = new JButton("��Ϸ��ʼ");
 public JButton button2 = new JButton("��Ϸ����");
 public GameFrame()
 {
  super("ƴͼ��Ϸ");
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