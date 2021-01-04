
package com.mr.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.mr.service.ScoreRecorder;
/**
 * �ɼ��Ի���
 * 
 * @author mingrisoft
 *
 */

public class ScoreDialog extends JDialog {

    /**
     * ���췽��
     * 
     * @param frame
     *            ������
     */
    public ScoreDialog(JFrame frame) {
        super(frame, true);// ���ø��๹�췽��,����������
        int scores[] = ScoreRecorder.getScores();// ��ȡ��ǰǰ�����ɼ�
        JPanel scoreP = new JPanel(new GridLayout(4, 1));// �ɼ���壬4��1��
        scoreP.setBackground(Color.WHITE);// ��ɫ����
        JLabel title = new JLabel("�÷����а�", JLabel.CENTER);// �����ǩ������
        title.setFont(new Font("����", Font.BOLD, 20));// ��������
        title.setForeground(Color.RED);// ��ɫ����
        JLabel first = new JLabel("��һ����" + scores[2], JLabel.CENTER);// ��һ����ǩ
        JLabel second = new JLabel("�ڶ�����" + scores[1], JLabel.CENTER);// �ڶ�����ǩ
        JLabel third = new JLabel("��������" + scores[0], JLabel.CENTER);// ��������ǩ
        JButton restart = new JButton("���¿�ʼ");// ���¿�ʼ��ť
        restart.addActionListener(new ActionListener() {// ��ť����¼�����
            @Override
            public void actionPerformed(ActionEvent e) {// �����ʱ
                dispose();// ���ٶԻ���
            }
        });

        scoreP.add(title);// �ɼ������ӱ�ǩ
        scoreP.add(first);
        scoreP.add(second);
        scoreP.add(third);

        Container c = getContentPane();// ��ȡ������
        c.setLayout(new BorderLayout());// ʹ�ñ߽粼��
        c.add(scoreP, BorderLayout.CENTER);// �ɼ������м�
        c.add(restart, BorderLayout.SOUTH);// ��ť�ŵײ�

        setTitle("��Ϸ����");// �Ի������

        int width, height;// �Ի�����
        width = height = 200;// �Ի����߾�Ϊ200
        // ����������о���λ�õĺ�����
        int x = frame.getX() + (frame.getWidth() - width) / 2;
        // ����������о���λ�õ�������
        int y = frame.getY() + (frame.getHeight() - height) / 2;
        setBounds(x, y, width, height);// ��������Ϳ��
        setVisible(true);// ��ʾ�Ի���
    }
}
