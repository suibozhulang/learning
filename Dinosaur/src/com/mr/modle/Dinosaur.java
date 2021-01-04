package com.mr.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import com.mr.service.FreshThread;
import com.mr.service.Sound;
/**
 * ������
 * 
 * @author mingrisoft
 *
 */

public class Dinosaur {
    public BufferedImage image;// ��ͼƬ
    private BufferedImage image1, image2, image3;// �ܲ�ͼƬ
    public int x, y;// ����
    private int jumpValue = 0;// ��Ծ��������
    private boolean jumpState = false;// ��Ծ״̬
    private int stepTimer = 0;// ̤����ʱ��
    private final int JUMP_HIGHT = 100;// �������߶�
    private final int LOWEST_Y = 120;// ����������
    private final int FREASH = FreshThread.FREASH;// ˢ��ʱ��

    public Dinosaur() {
        x = 50;
        y = LOWEST_Y;
        try {
            image1 = ImageIO.read(new File("image/����1.png"));
            image2 = ImageIO.read(new File("image/����2.png"));
            image3 = ImageIO.read(new File("image/����3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * �ƶ�
	 */
    public void move() {
        step();// ����̤��
        if (jumpState) {// ���������Ծ
            if (y >= LOWEST_Y) {// �����������ڵ�����͵�
                jumpValue = -4;// ������Ϊ��ֵ
            }
            if (y <= LOWEST_Y - JUMP_HIGHT) {// ���������ߵ�
                jumpValue = 4;// ������Ϊ��ֵ
            }
            y += jumpValue;// �����귢���仯
            if (y >= LOWEST_Y) {// ����ٴ����
                jumpState = false;// ֹͣ��Ծ
            }
        }
    }

    /**
     * ��Ծ
     */
    public void jump() {
        if (!jumpState) {// ���û������Ծ״̬
            Sound.jump();// ������Ծ��Ч
        }
        jumpState = true;// ������Ծ״̬
    }

    /**
     * ̤��
     */
    private void step() {
        // ÿ��250���룬����һ��ͼƬ����Ϊ����3ͼƬ�����Գ���3ȡ�࣬����չʾ������
        int tmp = stepTimer / 250 % 3;
        switch (tmp) {
            case 1 :
                image = image1;
                break;
            case 2 :
                image = image2;
                break;
            default :
                image = image3;
        }
        stepTimer += FREASH;// ��ʱ������
    }

    /**
     * �㲿�߽�����
     * 
     * @return
     */
    public Rectangle getFootBounds() {
        return new Rectangle(x + 30, y + 59, 29, 18);
    }

    /**
     * ͷ���߽�����
     * 
     * @return
     */
    public Rectangle getHeadBounds() {
        return new Rectangle(x + 66, y + 25, 32, 22);
    }
}