package com.mr.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.mr.view.BackgroundImage;

/**
 * 障碍类
 * 
 * @author mingrisoft
 *
 */

public class Obstacle {
    public int x, y;// 横纵坐标
    public BufferedImage image;
    private BufferedImage stone;// 石头图片
    private BufferedImage cacti;// 仙人掌图片
    private int speed;// 移动速度

    public Obstacle() {
        try {
            stone = ImageIO.read(new File("image/石头.png"));
            cacti = ImageIO.read(new File("image/仙人掌.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random();// 创建随机对象
        if (r.nextInt(2) == 0) {// 从0和1中取一值，若为0
            image = cacti;// 采用仙人掌图片
        } else {
            image = stone;// 采用石头图片
        }
        x = 800;// 初始恒做包
        y = 200 - image.getHeight();// 纵坐标
        speed = BackgroundImage.SPEED;// 移动速度与背景同步
    }

    /**
     * 移动
     */
    public void move() {
        x -= speed;// 横坐标递减
    }

    /**
     * 获取边界
     * 
     * @return
     */
    public Rectangle getBounds() {
        if (image == cacti) {// 如果使用仙人掌图片
            // 返回仙人掌的边界
            return new Rectangle(x + 7, y, 15, image.getHeight());
        }
        // 返回石头的边界
        return new Rectangle(x + 5, y + 4, 23, 21);
    }

    /**
     * 是否存活
     * 
     * @return
     */
    public boolean isLive() {
        // 如果移出了游戏界面
        if (x <= -image.getWidth()) {
            return false;// 消亡
        }
        return true;// 存活
    }
}
