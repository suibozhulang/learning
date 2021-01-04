package pintugame;

/*GameFrame��------������Ϸ�Ĵ򿪴����࣬
������һ���˵��������Ϸ��ʼ����Ϸ����������ť��
���Ҷ���Ϸ�Ĵ��ڽ����˻������ã�
����������Ϸ����ڡ�
*/
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener{
 
 private Cell BlankCell = null;
 private int row = 4;
 private int col = 4;//�������ƴͼ������
 private Cell cells[] = new Cell[row*col];//����һ����ť��������
 int ImageWidth;
 int ImageHeight;
 
 public GamePanel()//���캯��
 {
  this.setLayout(null);
  init();
 }
 
 public void init()//��ʼ��������¹���--���ͼ����и���ͼ��ͼ���ת������ɰ�ťͼ�����ӣ�����ť��ӵ�����ϣ����Ҹ�ÿһ����ť��Ӽ�������
 {
  int num = 0;
  BufferedImage buf = null;
  BufferedImage bufnew = null;
  ImageIcon icon = null;
  int width = 0;
  int height = 0;
  try
  {
   buf = ImageIO.read(new File("1.jpg"));//��ȡ�ļ�ͼ��
   ImageWidth = buf.getWidth();
   ImageHeight = buf.getHeight();
   //System.out.println("ImageWidth->"+ImageWidth+"ImageHeight->"+ImageHeight);
   width = ImageWidth/col;
   height = ImageHeight/row;
  }catch(Exception e)
  {
   System.out.println(e);
  }
  for(int i = 0; i < row; i++)
  {
   for(int j = 0; j < col; j++)
   {
    num = i*col+j;//��ʾ��ǰ���ͼ�������id���������е��±�
    if(num < row*col-1)
    {
     bufnew = buf.getSubimage(width*j, height*i, width, height);
     icon = new ImageIcon(bufnew);//��ͼ��ת����ͼ��
    }
    else//ʹ���һ��ͼ��Ϊ�հ�ͼ��
    {
     icon = new ImageIcon("");//һ�ſհ�ͼ��
    }
    cells[num] = new Cell(icon, num, width, height);//���ͼ�굽ÿһ��BUTTON��ť����
    cells[num].setLocation(width*j, height*i);
   }
  }
  BlankCell = cells[cells.length-1];//��ʼ���հ׸�
  for(int i = 0; i < cells.length; i++)
  {
   this.add(cells[i]);//��ÿһ����ť��ӵ���ǰ����������
   if(i < cells.length-1)
    cells[i].addMouseListener(this);//�հ׸���Ӽ�������
  }
 }
 
 public void OutOfOrder()//����----����ͼƬ���Ų�˳��
 {
  Random random = new Random();
  for(int i = 0 ; i < cells.length ; i++)
  {
   int index1 = random.nextInt(cells.length);//cells�ĳ�����9����������������9��ȡ����9����ȡֵ��Χ��0-8
   int index2 = random.nextInt(cells.length);
   int x = cells[index1].getX();
   int y = cells[index1].getY();//��ȡ�±���index1������Ԫ�ذ�ť������
   cells[index1].setLocation(cells[index2].getX(), cells[index2].getY());
   cells[index2].setLocation(x, y);
  }
 }
 
 public boolean IsWin()//�ж���Ϸ����Ƿ�Ӯ
 {
  for(int i = 0; i < cells.length; i++)
  {
   int x = cells[i].getX();
   int y = cells[i].getY();
   if(x/(ImageWidth/col) + y/(ImageHeight/row) != i)
   {
    return false;
   }
  }
  return true;
 }
 
 public void mouseClicked(MouseEvent e)
 {
  Cell t = (Cell) e.getSource();
  int x = BlankCell.getX();
  int y = BlankCell.getY();
  if(t.getY() == y && t.getX() + ImageWidth/col == x)//ͼ��������
  {
   t.move(Direction.RIGHT);
   BlankCell.move(Direction.LEFT);
  }
  else if(t.getY() == y && t.getX() - ImageWidth/col == x)//ͼ��������
  {
   t.move(Direction.LEFT);
   BlankCell.move(Direction.RIGHT);
  }
  else if(t.getX() == x && t.getY() + ImageHeight/row == y)//ͼ��������
  {
   t.move(Direction.UP);
   BlankCell.move(Direction.DOWN);
  }
  else if(t.getX() == x && t.getY() - ImageHeight/row == y)//ͼ��������
  {
   t.move(Direction.DOWN);
   BlankCell.move(Direction.UP);
  }
  if(IsWin())
  {
   int choice = JOptionPane.showConfirmDialog(null, "��ϲ���������Ƿ���һ�֣�", "��ʾ", JOptionPane.YES_NO_OPTION);
   if(choice == 0)//��ʾ����һ��
   {
    this.OutOfOrder();
   }
   else//��ʾ�˳���Ϸ
    System.exit(1);
  }
 }
 @Override
 public void mousePressed(MouseEvent e) {
 }
 @Override
 public void mouseReleased(MouseEvent e) {
 }
 @Override
 public void mouseEntered(MouseEvent e) {
 }
 @Override
 public void mouseExited(MouseEvent e) {
 }
}