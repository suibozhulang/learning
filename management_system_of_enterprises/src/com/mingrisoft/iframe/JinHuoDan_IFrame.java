package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;

import com.mingrisoft.*;
import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.*;

public class JinHuoDan_IFrame extends JInternalFrame {// ����������

	private JPanel jContentPane = null;// �������
	private JPanel topPanel = null;// �������
	private JLabel idLabel = null;// ������Ʊ�š���ǩ
	private JTextField idField = null;// ������Ʊ�š��ı���
	private JLabel gysLabel = null;// ����Ӧ�̡���ǩ
	private JLabel lxrLabel = null;// ����ϵ�ˡ���ǩ
	private JTextField lxrField = null;// ����ϵ�ˡ��ı���
	private JLabel jsfsLabel = null;// �����㷽ʽ����ǩ
	private JComboBox jsfsComboBox = null;// �����㷽ʽ�������б�
	private JLabel jhsjLabel = null;// ������ʱ�䡱��ǩ
	private JTextField jhsjField = null;// ������ʱ�䡱�ı���
	private JLabel jsrLabel = null;// �������ˡ���ǩ
	private JTextField czyField = null;// ������Ա���ı���
	private JPanel bottomPanel = null;// �ײ����
	private JLabel pzslLabel = null;// ��Ʒ����������ǩ
	private JTextField pzslField = null;// ��Ʒ���������ı���
	private JLabel hpzsLabel = null;// ����Ʒ��������ǩ
	private JTextField hpzsField = null;// ����Ʒ�������ı���
	private JLabel hjjeLabel = null;// ���ϼƽ���ǩ
	private JTextField hjjeField = null;// ���ϼƽ��ı���
	private JLabel ysjlLabel = null;// �����ս��ۡ���ǩ
	private JTextField ysjlField = null;// �����ս��ۡ��ı���
	private JLabel czyLabel = null;// ������Ա����ǩ
	private JButton tjButton = null;// ����ӡ���ť
	private JButton rukuButton = null;// ����⡱��ť
	private JScrollPane tablePane = null;// ������
	private JTable table = null;// ���ģ��
	private JComboBox spComboBox = null;// ��Ʒ�����б�
	private JComboBox gysComboBox = null;// ����Ӧ�̡������б�
	private JComboBox jsrComboBox = null;// �������ˡ������б�

	private Date jhsjDate = new Date();// �������ڶ���֮������ʱ�䡱

	/**
	 * ����������Ĺ��췽��
	 */
	public JinHuoDan_IFrame() {
		super();// ���ø���JInternalFrame�Ĺ��췽��
		initialize();// ��ʼ������������
	}

	/**
	 * ��ʼ������������ķ���
	 */
	private void initialize() {
		this.setSize(600, 320);// ���ý���������Ŀ��
		this.setIconifiable(true);// ���ý������������ͼ�껯
		this.setResizable(true);// ���Ե�������������Ĵ�С
		this.setMaximizable(true);// ���ý���������������
		this.setTitle("������");// ���ý���������ı���
		this.setClosable(true);// ���ý�����������Թر�
		this.setContentPane(getJContentPane());// �����������
	}

	/**
	 * ����������ķ���
	 * 
	 * @return �������
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {// ������岻����
			jContentPane = new JPanel();// �����������
			jContentPane.setLayout(new BorderLayout());// �����߽粼��
			jContentPane.add(getTopPanel(), java.awt.BorderLayout.NORTH);// �Ѷ�����������������ı���
			jContentPane.add(getBottomPanel(), java.awt.BorderLayout.SOUTH);// �ѵײ�����������������ϲ�
			jContentPane.add(getTablePane(), java.awt.BorderLayout.CENTER);// �ѱ������������������м�
		}
		return jContentPane;
	}

	/**
	 * ��ö������ķ������ں������
	 * 
	 * @return ������壨�ں������
	 */
	private JPanel getTopPanel() {
		if (topPanel == null) {// ���������岻����
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints21.fill = GridBagConstraints.BOTH;// ���ˮƽ����ֱͬʱ������ռ�ݿհ�����
			gridBagConstraints21.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints21.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints21.gridx = 9;// ���λ������ĺ�������Ϊ9
			GridBagConstraints gridBagConstraints = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints.fill = GridBagConstraints.BOTH;// ���ˮƽ����ֱͬʱ������ռ�ݿհ�����
			gridBagConstraints.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints.gridx = 5;// ���λ������ĺ�������Ϊ5
			GridBagConstraints gridBagConstraints101 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints101.gridx = 8;// ���λ������ĺ�������Ϊ8
			gridBagConstraints101.gridy = 1;// ���λ���������������Ϊ1
			jsrLabel = new JLabel();// �������ˣ�����ǩ
			jsrLabel.setText("\u7ecf\u624b\u4eba\uff1a");// ���á������ˣ�����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints91 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints91.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints91.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints91.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints91.gridx = 5;// ���λ������ĺ�������Ϊ5
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints81.gridx = 4;// ���λ������ĺ�������Ϊ4
			gridBagConstraints81.gridy = 1;// ���λ���������������Ϊ1
			jhsjLabel = new JLabel();// ������ʱ�䣺����ǩ
			jhsjLabel.setText("����ʱ�䣺");// ���á�����ʱ�䣺����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints71.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints71.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints71.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints71.gridx = 1;// ���λ������ĺ�������Ϊ1
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints61.gridx = 0;// ���λ������ĺ�������Ϊ0
			gridBagConstraints61.gridy = 1;// ���λ���������������Ϊ1
			jsfsLabel = new JLabel();// �����㷽ʽ������ǩ
			jsfsLabel.setText("\u7ed3\u7b97\u65b9\u5f0f\uff1a");// ���á����㷽ʽ������ǩ�е��ı�����
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints51.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints51.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints51.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints51.gridx = 9;// ���λ������ĺ�������Ϊ9
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints41.fill = GridBagConstraints.NONE;// ���������
			gridBagConstraints41.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints41.gridx = 8;// ���λ������ĺ�������Ϊ8
			lxrLabel = new JLabel();// ����ϵ�ˣ�����ǩ
			lxrLabel.setText("\u8054\u7cfb\u4eba\uff1a");// ���á���ϵ�ˣ�����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints25.gridx = 4;// ���λ������ĺ�������Ϊ4
			gridBagConstraints25.gridy = 0;// ���λ���������������Ϊ0
			gysLabel = new JLabel();// ����Ӧ�̣�����ǩ
			gysLabel.setText("\u4f9b\u5e94\u5546\uff1a");// ���á���Ӧ�̣�����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints110 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints110.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints110.gridx = 1;// ���λ������ĺ�������Ϊ1
			gridBagConstraints110.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints110.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints110.insets = new Insets(0, 0, 0, 1);// ����˴˵ļ��
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints24.gridx = 0;// ���λ������ĺ�������Ϊ0
			gridBagConstraints24.gridy = 0;// ���λ���������������Ϊ0
			idLabel = new JLabel();// ������Ʊ�ţ�����ǩ
			idLabel.setText("����Ʊ�ţ�");// ���á�����Ʊ�ţ�����ǩ�е��ı�����
			topPanel = new JPanel();// �����������
			topPanel.setLayout(new GridBagLayout());// ���ö������Ĳ���Ϊ���񲼾�
			topPanel.add(idLabel, gridBagConstraints24);// �ѡ�����Ʊ�š���ǩ�������񲼾�����Ӧ���������ƶ�����
			topPanel.add(getIdField(), gridBagConstraints110);// �ѡ�����Ʊ�š��ı����������񲼾�����Ӧ���������ƶ�����
			topPanel.add(gysLabel, gridBagConstraints25);// �ѡ���Ӧ�̡���ǩ�������񲼾�����Ӧ���������ƶ�����
			topPanel.add(getGysComboBox(), gridBagConstraints);// �ѡ���Ӧ�̡������б��������񲼾�����Ӧ���������ƶ�����
			topPanel.add(lxrLabel, gridBagConstraints41);// �ѡ���ϵ�ˡ���ǩ�������񲼾�����Ӧ���������ƶ�����
			topPanel.add(getLxrField(), gridBagConstraints51);// �ѡ���ϵ�ˡ��ı����������񲼾�����Ӧ���������ƶ�����
			topPanel.add(jsfsLabel, gridBagConstraints61);// �ѡ����㷽ʽ����ǩ�������񲼾�����Ӧ���������ƶ�����
			topPanel.add(getJsfsComboBox(), gridBagConstraints71);// �ѡ����㷽ʽ�������б��������񲼾�����Ӧ���������ƶ�����
			topPanel.add(jhsjLabel, gridBagConstraints81);// �ѡ�����ʱ�䡱��ǩ�������񲼾�����Ӧ���������ƶ�����
			topPanel.add(getJhsjField(), gridBagConstraints91);// �ѡ�����ʱ�䡱�ı����������񲼾�����Ӧ���������ƶ�����
			topPanel.add(jsrLabel, gridBagConstraints101);// �ѡ������ˡ���ǩ�������񲼾�����Ӧ���������ƶ�����
			topPanel.add(getJsrComboBox(), gridBagConstraints21);// �ѡ������ˡ������б��������񲼾�����Ӧ���������ƶ�����
		}
		return topPanel;// ���ض������
	}

	/**
	 * ��á�����Ʊ�š��ı���ķ���
	 * 
	 * @return ������Ʊ�š��ı���
	 */
	private JTextField getIdField() {
		if (idField == null) {// ���������Ʊ�š��ı��򲻴���
			idField = new JTextField();// ����������Ʊ�š��ı���
			idField.setEditable(false);// ���á�����Ʊ�š��ı��򲻿ɱ༭
		}
		return idField;
	}

	/**
	 * ��á���ϵ�ˡ��ı���ķ���
	 * 
	 * @return ����ϵ�ˡ��ı���
	 */
	private JTextField getLxrField() {
		if (lxrField == null) {// �������ϵ�ˡ��ı��򲻴���
			lxrField = new JTextField();// ��������ϵ�ˡ��ı���
		}
		return lxrField;
	}

	/**
	 * ��á����㷽ʽ���ı���ķ���
	 * 
	 * @return �����㷽ʽ���ı���
	 */
	private JComboBox getJsfsComboBox() {
		if (jsfsComboBox == null) {// �����㷽ʽ�������б�����
			jsfsComboBox = new JComboBox();// ���������㷽ʽ�������б�
			jsfsComboBox.addItem("�ֽ���");// �򡰽��㷽ʽ�������б�����ӡ��ֽ��ѡ��
			jsfsComboBox.addItem("֧Ʊ���");// �򡰽��㷽ʽ�������б�����ӡ�֧Ʊ��ѡ��
		}
		return jsfsComboBox;
	}

	/**
	 * ��á�����ʱ�䡱�ı���ķ���
	 * 
	 * @return ������ʱ�䡱�ı���
	 */
	private JTextField getJhsjField() {
		if (jhsjField == null) {// ������ʱ�䡱�ı��򲻴���
			jhsjField = new JTextField();// ����������ʱ�䡱�ı���
		}
		return jhsjField;
	}

	/**
	 * ��á�����Ա���ı���ķ���
	 * 
	 * @return ������Ա���ı���
	 */
	private JTextField getCzyField() {
		if (czyField == null) {// ������Ա���ı��򲻴���
			czyField = new JTextField();// ����������Ա���ı���
			czyField.setEditable(false);// ���á�����Ա���ı��򲻿ɱ༭
			czyField.setText(MainFrame.getCzyStateLabel().getText());// ���á�����Ա���ı����е��ı�����
		}
		return czyField;
	}

	/**
	 * ��õײ����ķ������ں������
	 * 
	 * @return �ײ���壨�ں������
	 */
	private JPanel getBottomPanel() {
		if (bottomPanel == null) {// ����ײ���岻����
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints2.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints2.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints2.gridx = 3;// ���λ������ĺ�������Ϊ3
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints1.fill = GridBagConstraints.BOTH;// ���ˮƽ����ֱͬʱ������ռ�ݿհ�����
			gridBagConstraints1.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints1.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints1.gridx = 9;// ���λ������ĺ�������Ϊ9
			GridBagConstraints gridBagConstraints231 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints231.fill = GridBagConstraints.NONE;// ���������
			gridBagConstraints231.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints231.weightx = 0.3;// ������������Ȩ����0.3
			gridBagConstraints231.gridx = 6;// ���λ������ĺ�������Ϊ6
			GridBagConstraints gridBagConstraints221 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints221.gridx = 5;// ���λ������ĺ�������Ϊ5
			gridBagConstraints221.weightx = 0.3;// ������������Ȩ����0.3
			gridBagConstraints221.gridy = 1;// ���λ���������������Ϊ1
			GridBagConstraints gridBagConstraints201 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints201.gridx = 2;// ���λ������ĺ�������Ϊ2
			gridBagConstraints201.gridy = 1;// ���λ���������������Ϊ1
			czyLabel = new JLabel();// ������Ա����ǩ
			czyLabel.setText("\u64cd\u4f5c\u5458\uff1a");// ���á�����Ա����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints191 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints191.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints191.gridy = 1;// ���λ���������������Ϊ1
			gridBagConstraints191.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints191.gridx = 1;// ���λ������ĺ�������Ϊ1
			GridBagConstraints gridBagConstraints181 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints181.gridx = 0;// ���λ������ĺ�������Ϊ0
			gridBagConstraints181.gridy = 1;// ���λ���������������Ϊ1
			ysjlLabel = new JLabel();// �����ս��ۣ�����ǩ
			ysjlLabel.setText("\u9a8c\u6536\u7ed3\u8bba\uff1a");// ���á����ս��ۣ�����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints171 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints171.anchor = GridBagConstraints.WEST;// ���������ʾ���������
			gridBagConstraints171.gridwidth = 2;// ������2������
			gridBagConstraints171.gridx = 5;// ���λ������ĺ�������Ϊ5
			gridBagConstraints171.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints171.weightx = 0.6;// ������������Ȩ����0.6
			gridBagConstraints171.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			GridBagConstraints gridBagConstraints161 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints161.gridx = 4;// ���λ������ĺ�������Ϊ4
			gridBagConstraints161.gridy = 0;// ���λ���������������Ϊ0
			hjjeLabel = new JLabel();// ���ϼƽ�����ǩ
			hjjeLabel.setText("\u5408\u8ba1\u91d1\u989d\uff1a");// ���á��ϼƽ�����ǩ�е��ı�����
			GridBagConstraints gridBagConstraints151 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints151.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints151.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints151.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints151.gridx = 3;// ���λ������ĺ�������Ϊ3
			GridBagConstraints gridBagConstraints141 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints141.gridx = 2;// ���λ������ĺ�������Ϊ2
			gridBagConstraints141.gridy = 0;// ���λ���������������Ϊ0
			hpzsLabel = new JLabel();// ����Ʒ����������ǩ
			hpzsLabel.setText("\u8d27\u54c1\u603b\u6570\uff1a");// ���á���Ʒ����������ǩ�е��ı�����
			GridBagConstraints gridBagConstraints131 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints131.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints131.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints131.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints131.gridx = 1;// ���λ������ĺ�������Ϊ1
			GridBagConstraints gridBagConstraints121 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints121.anchor = GridBagConstraints.WEST;// ���������ʾ���������
			gridBagConstraints121.gridy = 0;// ���λ���������������Ϊ0
			gridBagConstraints121.gridx = 0;// ���λ������ĺ�������Ϊ0
			pzslLabel = new JLabel();// ��Ʒ������������ǩ
			pzslLabel.setText("\u54c1\u79cd\u6570\u91cf\uff1a");// ���á�Ʒ������������ǩ�е��ı�����
			bottomPanel = new JPanel();// �����ײ����
			bottomPanel.setLayout(new GridBagLayout());// ���õײ����Ĳ���Ϊ���񲼾�
			bottomPanel.add(pzslLabel, gridBagConstraints121);// �ѡ�Ʒ����������ǩ�������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getPzslField(), gridBagConstraints131);// �ѡ�Ʒ���������ı����������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(hpzsLabel, gridBagConstraints141);// �ѡ���Ʒ����������ǩ�������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getHpzsField(), gridBagConstraints151);// �ѡ���Ʒ�������ı����������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(hjjeLabel, gridBagConstraints161);// �ѡ��ϼƽ�����ǩ�������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getHjjeField(), gridBagConstraints171);// �ѡ��ϼƽ��ı����������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(ysjlLabel, gridBagConstraints181);// �ѡ����ս��ۣ�����ǩ�������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getYsjlField(), gridBagConstraints191);// �ѡ����ս��ۡ��ı����������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(czyLabel, gridBagConstraints201);// �ѡ�����Ա������ǩ�������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getCzyField(), gridBagConstraints2);// �ѡ�����Ա���ı����������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getTjButton(), gridBagConstraints221);// �ѡ���ӡ���ť�������񲼾�����Ӧ���������ƶ�����
			bottomPanel.add(getRukuButton(), gridBagConstraints231);// �ѡ���⡱��ť�������񲼾�����Ӧ���������ƶ�����
		}
		return bottomPanel;// ���صײ����
	}

	/**
	 * ��á�Ʒ���������ı���ķ���
	 * 
	 * @return ��Ʒ���������ı���
	 */
	private JTextField getPzslField() {
		if (pzslField == null) {// �����Ʒ���������ı��򲻴���
			pzslField = new JTextField();// ������Ʒ���������ı���
			pzslField.setEditable(false);// ���á�Ʒ���������ı��򲻿ɱ༭
		}
		return pzslField;
	}

	/**
	 * ��á���Ʒ�������ı���ķ���
	 * 
	 * @return ����Ʒ�������ı���
	 */
	private JTextField getHpzsField() {
		if (hpzsField == null) {// �������Ʒ�������ı��򲻴���
			hpzsField = new JTextField();// ��������Ʒ�������ı���
			hpzsField.setEditable(false);// ���á���Ʒ�������ı��򲻿ɱ༭
		}
		return hpzsField;
	}

	/**
	 * ��á��ϼƽ��ı���ķ���
	 * 
	 * @return ���ϼƽ��ı���
	 */
	private JTextField getHjjeField() {
		if (hjjeField == null) {// ������ϼƽ��ı��򲻴���
			hjjeField = new JTextField();// �������ϼƽ��ı���
			hjjeField.setEditable(false);// ���á��ϼƽ��ı��򲻿ɱ༭
		}
		return hjjeField;
	}

	/**
	 * ��á����ս��ۡ��ı���ķ���
	 * 
	 * @return �����ս��ۡ��ı���
	 */
	private JTextField getYsjlField() {
		if (ysjlField == null) {// ��������ս��ۡ��ı��򲻴���
			ysjlField = new JTextField();// ���������ս��ۡ��ı���
		}
		return ysjlField;
	}

	/**
	 * ��á���ӡ���ť�ķ���
	 * 
	 * @return ����ӡ���ť
	 */
	private JButton getTjButton() {
		if (tjButton == null) {// �������ӡ���ť������
			tjButton = new JButton();// ��������ӡ���ť
			tjButton.setText("���");// ���á���ӡ���ť�е��ı�����
			tjButton.addActionListener(new ActionListener() {// Ϊ����ӡ���ť��Ӷ����¼��ļ���
				public void actionPerformed(ActionEvent e) {
					java.sql.Date date = new java.sql.Date(jhsjDate.getTime());// �������ڶ���
					jhsjField.setText(date.toString());// ���á�����ʱ�䡱�ı����е��ı�����
					String maxId = Dao.getRuKuMainMaxId(date);// ��ȡ���ġ�����Ʊ�š�
					idField.setText(maxId);// ���á�����Ʊ�š��ı����е��ı�����
					// ���������û�б�д�ĵ�Ԫ
					stopTableCellEditing();
					// �������в��������У����������
					for (int i = 0; i <= table.getRowCount() - 1; i++) {
						if (table.getValueAt(i, 0) == null)
							return;
					}
					DefaultTableModel model = (DefaultTableModel) table.getModel();// ����������
					model.addRow(new Vector());// ������ӿ���
				}
			});
		}
		return tjButton;
	}

	/**
	 * ��á���⡱��ť�ķ���
	 * 
	 * @return ����⡱��ť
	 */
	private JButton getRukuButton() {
		if (rukuButton == null) {// �������⡱��ť������
			rukuButton = new JButton();// ��������⡱��ť
			rukuButton.setText("���");// ���á���⡱��ť�е��ı�����
			rukuButton.addActionListener(new java.awt.event.ActionListener() {// Ϊ����⡱��ť��Ӷ����¼��ļ���
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// ֹͣ���Ԫ�ı༭
					stopTableCellEditing();
					// �������
					clearEmptyRow();
					String pzsStr = pzslField.getText();// Ʒ����
					String jeStr = hjjeField.getText();// �ϼƽ��
					String jsfsStr = jsfsComboBox.getSelectedItem().toString();// ���㷽ʽ
					String jsrStr = jsrComboBox.getSelectedItem() + "";// ������
					String czyStr = jsrComboBox.getSelectedItem() + "";// ����Ա
					String rkDate = jhsjField.getText();// ���ʱ��
					String ysjlStr = ysjlField.getText().trim();// ���ս���
					String id = idField.getText();// Ʊ��
					String gysName = gysComboBox.getSelectedItem() + "";// ��Ӧ������
					if (jsrStr == null || jsrStr.isEmpty()) {// ����������ˡ������б����ڻ򡰾����ˡ������б�Ϊ��
						JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "����д������");
						return;
					}
					if (ysjlStr == null || ysjlStr.isEmpty()) {// ��������ս��ۡ��ı��򲻴��ڻ����ս��ۡ��ı���Ϊ��
						JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "��д���ս���");
						return;
					}
					if (table.getRowCount() <= 0) {// ������ģ�͵�����С�ڵ���0
						JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "��������Ʒ");
						return;
					}
					TbRukuMain ruMain = new TbRukuMain(id, pzsStr, jeStr, ysjlStr, gysName, rkDate, czyStr, jsrStr,
							jsfsStr);// �������
					Set<TbRukuDetail> set = ruMain.getTabRukuDetails();// �����ϸ
					int rows = table.getRowCount();// ��ñ��ģ���е�����
					for (int i = 0; i < rows; i++) {
						TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);// ��Ʒ��Ϣ
						if (spinfo == null || spinfo.getId() == null || spinfo.getId().isEmpty())// ��Ʒ��Ϣ�����ڡ���Ʒ��Ų����ڻ���Ʒ���Ϊ��
							continue;// ��������ѭ����ִ����һ��ѭ��
						String djStr = (String) table.getValueAt(i, 6);// ����
						String slStr = (String) table.getValueAt(i, 7);// ����
						Double dj = Double.valueOf(djStr);// ��String���͵ġ����ۡ�ת��Ϊint��
						Integer sl = Integer.valueOf(slStr);// ��String���͵ġ�������ת��Ϊint��
						TbRukuDetail detail = new TbRukuDetail();// �����ϸ
						detail.setTabSpinfo(spinfo.getId());// ��Ʒ��Ϣ
						detail.setTabRukuMain(ruMain.getRkId());// �������(�����)
						detail.setDj(dj);// ����
						detail.setSl(sl);// ����
						set.add(detail);// ��������ϸ
					}
					boolean rs = Dao.insertRukuInfo(ruMain);// �Ƿ�ɹ���������Ϣ
					if (rs) {// �ɹ���������Ϣ
						JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "������");// ������ʾ��
						DefaultTableModel dftm = new DefaultTableModel();// �������Ĭ��ģ�Ͷ���
						table.setModel(dftm);// ����������ģ������Ϊdftm
						pzslField.setText("0");// ���á�Ʒ���������ı����е�����Ϊ0
						hpzsField.setText("0");// ���á���Ʒ�������ı����е�����Ϊ0
						hjjeField.setText("0");// ���á��ϼƽ��ı����е�����Ϊ0
					}
				}
			});
		}
		return rukuButton;
	}

	/**
	 * ��ñ�����ķ���
	 * 
	 * @return ������
	 */
	private JScrollPane getTablePane() {
		if (tablePane == null) {// ��������岻����
			tablePane = new JScrollPane();// ����������
			tablePane.setViewportView(getTable());// �������ģ�ʹ���
		}
		return tablePane;
	}

	/**
	 * ��ñ��ģ�͵ķ���
	 * 
	 * @return ���ģ��
	 */
	private JTable getTable() {
		if (table == null) {// ������ģ�Ͳ�����
			String[] columnNames = { "��Ʒ����", "��Ʒ���", "����", "��λ", "���", "��װ", "����", "����", "����", "��׼�ĺ�" };// ��ͷ
			table = new JTable();// �������ģ��
			table.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));// ���ñ��ģ�͵ı߿���ʽ
			table.setShowGrid(true);// ����������
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
			// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
			table.addContainerListener(new computeInfo());
			((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);// �������ģ�Ͷ�����������ӱ�ͷ
			TableColumn column = table.getColumnModel().getColumn(0);// �Ա��ģ��������Ϊ0����������
			final DefaultCellEditor editor = new DefaultCellEditor(getSpComboBox());// ������Ԫ��༭��
			column.setCellEditor(editor);// �༭�������е�Ԫ��ʱ���õı༭��
			table.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e) {// Ϊ�����Ӹ������Եļ����¼�
					if ((e.getPropertyName().equals("tableCellEditor"))) {
						new computeInfo();// �¼����������ô��������ڼ����Ʒ�������ϼƽ�����Ϣ
					}
				}
			});
		}
		return table;
	}

	/**
	 * ��á���Ʒ�������б�ķ���
	 * 
	 * @return ����Ʒ�������б�
	 */
	private JComboBox getSpComboBox() {
		if (spComboBox == null) {// �������Ʒ�������б�����
			spComboBox = new JComboBox();// ��������Ʒ�������б�
			spComboBox.addItem(new TbSpinfo());// ����Ʒ�������б��������Ʒ��Ϣ
			spComboBox.addActionListener(new ActionListener() {// Ϊ����Ʒ�������б���Ӷ����¼��ļ���
				public void actionPerformed(ActionEvent e) {
					ResultSet set = Dao
							.query("select * from tb_spinfo where gysName='" + getGysComboBox().getSelectedItem() + "'");// ��ù�Ӧ����Ϣ�ļ���
					updateSpComboBox(set);// ������Ʒ�����б�
				}
			});
			spComboBox.addItemListener(new java.awt.event.ItemListener() {// Ϊ����Ʒ�������б����ѡ���¼��ļ���
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					TbSpinfo info = (TbSpinfo) spComboBox.getSelectedItem();// ��á���Ʒ�������б��б�ѡ�е���Ʒ��Ϣ
					// ���ѡ����Ч�͸��±��
					if (info != null && info.getId() != null) {
						updateTable();// ���±��ǰ�е�����
					}
				}
			});
		}
		return spComboBox;
	}

	/**
	 * ������Ʒ�����б�ķ���
	 * 
	 * @param set��JDBC���ص�ResultSet�����
	 */
	private void updateSpComboBox(ResultSet set) {
		try {
			while (set.next()) {// �ƶ���ļ�¼ָ��ָ��һ����Ч�ļ�¼
				TbSpinfo spinfo = new TbSpinfo();// ��Ʒ��Ϣ
				spinfo.setId(set.getString("id").trim());// ��Ʒ���
				spinfo.setSpname(set.getString("spname").trim());// ��Ʒ����
				spinfo.setCd(set.getString("cd").trim());// ����
				spinfo.setJc(set.getString("jc").trim());// ��Ʒ���
				spinfo.setDw(set.getString("dw").trim());// ��Ʒ������λ
				spinfo.setGg(set.getString("gg").trim());// ��Ʒ���
				spinfo.setBz(set.getString("bz").trim());// ��װ
				spinfo.setPh(set.getString("ph").trim());// ����
				spinfo.setPzwh(set.getString("pzwh").trim());// ��׼�ĺ�
				spinfo.setMemo(set.getString("memo").trim());// ��ע
				spinfo.setGysname(set.getString("gysname").trim());// ��Ӧ������
				DefaultComboBoxModel model = (DefaultComboBoxModel) spComboBox.getModel();// ����Ʒ�������б��Ĭ��ģ��
				if (model.getIndexOf(spinfo) < 0)// ����Ʒ�������б���������Ʒ
					spComboBox.addItem(spinfo);// �����ѡ��
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * ������Ʒ�����б��ѡ�񣬸��±��ǰ�е�����
	 */
	private synchronized void updateTable() {
		TbSpinfo spinfo = (TbSpinfo) spComboBox.getSelectedItem();// ��á���Ʒ�������б��б�ѡ�е�ѡ��
		int row = table.getSelectedRow();// ��ñ��ģ���б�ѡ�е���
		if (row >= 0 && spinfo != null) {// ���ģ���б�ѡ�е��д��ڵ���0�ҡ���Ʒ�������б��б�ѡ�е�ѡ�Ϊ��
			// ���ñ�ģ���е�Ԫ���ֵ
			table.setValueAt(spinfo.getId(), row, 1);// ��Ʒ���
			table.setValueAt(spinfo.getCd(), row, 2);// ����
			table.setValueAt(spinfo.getDw(), row, 3);// ��Ʒ������λ
			table.setValueAt(spinfo.getGg(), row, 4);// ��Ʒ���
			table.setValueAt(spinfo.getBz(), row, 5);// ��װ
			table.setValueAt("0", row, 6);// ����
			table.setValueAt("0", row, 7);// ����
			table.setValueAt(spinfo.getPh(), row, 8);// ����
			table.setValueAt(spinfo.getPzwh(), row, 9);// ��׼�ĺ�
			table.editCellAt(row, 6);// ���ۿɱ༭
		}
	}

	/**
	 * ��á���Ӧ�̡������б�ķ���
	 * 
	 * @return ����Ӧ�̡������б�
	 */
	private JComboBox getGysComboBox() {
		if (gysComboBox == null) {// �������Ӧ�̡������б�����
			gysComboBox = new JComboBox();// ��������Ӧ�̡������б�
			List gysInfos = Dao.getGysInfos();// ��ù�Ӧ����Ϣ�ļ���
			for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {// ������Ӧ����Ϣ�ļ���
				List list = (List) iter.next();// ��õ�������һ������
				Item item = new Item();// ���ݱ�����
				item.setId(list.get(0).toString().trim());// �������
				item.setName(list.get(1).toString().trim());// ������Ϣ
				gysComboBox.addItem(item);// �򡰹�Ӧ�̡������б������ѡ��
			}
			Item item = (Item) gysComboBox.getSelectedItem();// ��á���Ӧ�̡������б��б�ѡ�е�ѡ��
			TbGysinfo gysInfo = Dao.getGysInfo(item);// ��Ӧ����Ϣ
			getLxrField().setText(gysInfo.getLian());// ���á���ϵ�ˡ��ı�����ı�����
		}
		return gysComboBox;
	}

	/**
	 * �÷�������ֹͣ���Ԫ�ı༭
	 */
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();// �������Ԫ�༭��
		if (cellEditor != null)// ���Ԫ�༭������
			cellEditor.stopCellEditing();// ֹͣ���Ԫ�ı༭
	}

	/**
	 * ��á������ˡ������б�ķ���
	 * 
	 * @return �������ˡ������б�
	 */
	private JComboBox getJsrComboBox() {
		if (jsrComboBox == null) {// ����������ˡ������б�����
			jsrComboBox = new JComboBox();// �����������ˡ������б�
			List<List> czyList = Dao.getJsrs();// ��ñ����õľ����˼���
			for (List<String> list : czyList) {// ���������õľ����˼���
				String id = list.get(0);// �����˱��
				String name = list.get(1);// ����������
				Item item = new Item(id, name);// ���ݱ�����
				item.setId(id + "");// �������
				item.setName(name);// ������Ϣ
				jsrComboBox.addItem(item);// �򡰾����ˡ������б�����Ӿ�����
			}
		}
		return jsrComboBox;
	}

	/**
	 * �¼����������ô��������ڼ����Ʒ�������ϼƽ�����Ϣ��
	 */
	private final class computeInfo implements ContainerListener {
		@Override
		public void componentRemoved(ContainerEvent e) {
			// �������
			clearEmptyRow();
			int rows = table.getRowCount();// ��ñ��ģ���е�����
			int count = 0;// ����Ʒ������
			double money = 0.0;// ���ϼƽ�
			TbSpinfo column = null;// ����Ʒ��Ϣ����ʵ��
			if (rows > 0)// ���ģ���е���������0
				column = (TbSpinfo) table.getValueAt(rows - 1, 0);// Ϊ����Ʒ��Ϣ����ʵ����ֵ
			if (rows > 0 && (column == null || column.getId().isEmpty()))// ���ģ���е���������0�ҡ���Ʒ��Ϣ����ʵ�������ڻ���Ʒ���Ϊ��
				rows--;// ���ģ���е�������һ
			// �����Ʒ�����ͺϼƽ��
			for (int i = 0; i < rows; i++) {
				String column7 = (String) table.getValueAt(i, 7);// ��ñ���С�������
				String column6 = (String) table.getValueAt(i, 6);// ��ñ���С����ۡ�
				int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer.parseInt(column7);// ��String���͵ġ�������ת��Ϊint��
				float c6 = (column6 == null || column6.isEmpty()) ? 0 : Float.parseFloat(column6);// ��String���͵ġ����ۡ�ת��Ϊfloat��
				count += c7;// �����Ʒ����
				money += c6 * c7;// ����ϼƽ��
			}
			pzslField.setText(rows + "");// ���á�Ʒ���������ı����е��ı�����
			hpzsField.setText(count + "");// ���á���Ʒ�������ı����е��ı�����
			hjjeField.setText(money + "");// ���á��ϼƽ��ı����е��ı�����
		}
		@Override
		public void componentAdded(ContainerEvent e) {
		}
		
	}
	// �������
	private synchronized void clearEmptyRow() {
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();// ���Ĭ�ϵı��ģ��
		for (int i = 0; i < table.getRowCount(); i++) {
			TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);// �����Ʒ��Ϣ
			if (spinfo == null || spinfo.getId() == null || spinfo.getId().isEmpty()) {// ��Ʒ��ϢΪ��
				dftm.removeRow(i);// �Ƴ�Ĭ�ϵı��ģ���еĸ���
			}
		}
	}
}
