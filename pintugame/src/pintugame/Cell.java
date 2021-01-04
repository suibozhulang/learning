package pintugame;
//Cell��----���ÿ����ť����Ӧ�þ߱������Թ���---��԰�ť
import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.JButton;
 
@SuppressWarnings("serial")
public class Cell extends JButton{
 private static int IMAGEWIDTH;//���ð�ť�Ŀ�ȴ�С
 private static int IMAGEHEIGHT;
 private int ID = 0;//���õ�ǰ��ť��ָ������
 
 public Cell(Icon icon, int id, int imagewidth, int height)//���캯����ʼ������������������һ����ͼ���ͼ�꣬һ���Ǹð�ť������ID
 {
  this.setIcon(icon);
  this.ID = id;
  this.IMAGEWIDTH = imagewidth;
  this.IMAGEHEIGHT = height;
  this.setSize(IMAGEWIDTH, IMAGEHEIGHT);
 }
 
 public void move(Direction dir)//�ƶ�
 {
  Rectangle rec = this.getBounds();//��ȡ��ǰ���������߿�
  switch(dir)
  {
  case UP://�����ƶ����ı�����
   this.setLocation(rec.x, rec.y + IMAGEHEIGHT);
   break;
  case DOWN://�����ƶ�
   this.setLocation(rec.x, rec.y - IMAGEHEIGHT);
   break;
  case LEFT://�����ƶ�
   this.setLocation(rec.x - IMAGEWIDTH, rec.y);
   break;
  case RIGHT://�����ƶ�
   this.setLocation(rec.x + IMAGEWIDTH, rec.y);
   break;
  }
 }
 
 public int getID() {
  return ID;
 }
 
 public int getX()
 {
  return this.getBounds().x;
 }
 
 public int getY()
 {
  return this.getBounds().y;
 }
}