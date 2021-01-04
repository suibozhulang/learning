package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.TbJsr;

public class XiaoShouChaXun extends JInternalFrame{// ���۲�ѯ�ڲ�����
	
	private JButton queryButton;// ����ѯ����ť
	private JTextField endDate;// ����ֹ���ڡ��ı���
	private JTextField startDate;// ����ʼ���ڡ��ı���
	private JTable table;// ���ģ��
	private JTextField content;// ���������ݡ��ı���
	private JComboBox operation;// ���������㡱�����б�
	private JComboBox condition;// ���������ơ������б�
	private TbJsr user;// ��������Ϣ
	private DefaultTableModel dftm;// ���ģ�͵�ʵ��������
	private JCheckBox selectDate;// ��ָ����ѯ���ڡ���ѡ��
	
	public XiaoShouChaXun() {// ���۲�ѯ�ڲ�����Ĺ��췽��
		// �����۲�ѯ�ڲ�����ʱ�����á���ʼ���ڡ��͡���ֹ���ڡ�
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(final InternalFrameEvent e) {
				java.sql.Date date=new java.sql.Date(System.currentTimeMillis());// ������ϵͳ��ǰʱ��Ϊ���������ڶ���
				endDate.setText(date.toString());// ���á���ֹ���ڡ��ı����е��ı�����
				date.setMonth(0);// �����·�Ϊ1��
				date.setDate(1);// ��������Ϊ��ǰ��ݵĵ�ǰ�·ݵĵ�һ��
				startDate.setText(date.toString());// ���á���ʼ���ڡ��ı����е��ı�����
			}
		});
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر�
		setTitle("������Ϣ��ѯ");// �������۲�ѯ�ڲ�����ı���
		getContentPane().setLayout(new GridBagLayout());// �������۲�ѯ�ڲ�����Ĳ��������񲼾�
		setBounds(100, 100, 680, 375);// �������۲�ѯ�ڲ������λ�úͿ��
		// ��ѡ���ѯ��������ǩ�͡��������ơ������б�
		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		condition = new JComboBox();
		condition.setModel(new DefaultComboBoxModel(new String[] {"�ͻ�ȫ��", "����Ʊ��"}));
		setupComponet(condition, 1, 0, 1, 30, true);
		// ���������㡱�����б�
		operation = new JComboBox();
		operation.setModel(new DefaultComboBoxModel(new String[]{"����", "����"}));
		setupComponet(operation, 4, 0, 1, 30, true);
		// ���������ݡ��ı���
		content = new JTextField();
		content.addKeyListener(new KeyAdapter() {// Ϊ���������ݡ��ı�����Ӽ����¼��ļ���
			public void keyReleased(final KeyEvent e) {
				if(e.getKeyCode()==10) {// �����¡��س�����ʱ
					queryButton.doClick();// ����ѯ����ťִ�С�����������
				}
			}
		});
		setupComponet(content, 5, 0, 2, 240, true);
		// ����ѯ����ť
		queryButton = new JButton();
		queryButton.addActionListener(new QueryAction());// Ϊ����ѯ����ť��Ӷ����¼��ļ���
		setupComponet(queryButton, 7, 0, 1, 1, false);
		queryButton.setText("��ѯ");
		// ��ָ����ѯ���ڡ���ѡ��
		selectDate = new JCheckBox();
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.anchor = GridBagConstraints.EAST;
		gridBagConstraints_7.insets = new Insets(0, 10, 0, 0);
		gridBagConstraints_7.gridy = 1;
		gridBagConstraints_7.gridx = 0;
		getContentPane().add(selectDate, gridBagConstraints_7);
		// ��ָ����ѯ����    �ӡ���ǩ
		final JLabel label_1 = new JLabel();
		label_1.setText("ָ����ѯ����    ��");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.anchor = GridBagConstraints.EAST;
		gridBagConstraints_8.gridy = 1;
		gridBagConstraints_8.gridx = 1;
		getContentPane().add(label_1, gridBagConstraints_8);
		// ����ʼ���ڡ��ı���
		startDate = new JTextField();
		startDate.setPreferredSize(new Dimension(100,21));
		setupComponet(startDate, 2, 1, 3, 1, true);
		// �������ı���
		setupComponet(new JLabel("��"), 5, 1, 1, 1, false);
		// ����ֹ���ڡ��ı���
		endDate = new JTextField();
		endDate.addKeyListener(content.getKeyListeners()[0]);// ���������ݡ��ı����ü����¼��ļ���(�����¡��س�����ʱ������ѯ����ťִ�С�����������)
		endDate.setPreferredSize(new Dimension(100,21));
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.ipadx = 90;
		gridBagConstraints_11.anchor = GridBagConstraints.WEST;
		gridBagConstraints_11.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_11.gridy = 1;
		gridBagConstraints_11.gridx = 6;
		getContentPane().add(endDate, gridBagConstraints_11);
		// ����ʾȫ�����ݡ���ť
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {// Ϊ����ʾȫ�����ݡ���ť��Ӷ����¼��ļ���
			public void actionPerformed(final ActionEvent e) {
				content.setText("");// ���á��������ݡ��ı���Ϊ��
				List list=Dao.findForList("select * from v_sellView");// ���������Ϣ�ļ���
				Iterator iterator=list.iterator();// ����������Ϣ���ϵĵ�����
				updateTable(iterator);// ���±������
			}
		});
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 7;
		getContentPane().add(showAllButton, gridBagConstraints_5);
		showAllButton.setFont(new Font("", Font.PLAIN, 12));
		showAllButton.setText("��ʾȫ������");
		// �������
		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
		// ���ģ��
		table = new JTable();
		table.setEnabled(false);// ���ñ��ģ���޷���Ӧ�û�����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		dftm = (DefaultTableModel)table.getModel();// ���Ĭ�ϵı��ģ��
		String[] tableHeads = new String[]{"����Ʊ��", "��Ʒ����", "��Ʒ���", "���", "����",
				"����", "���", "�ͻ�ȫ��", "��������", "����Ա", "������", "���㷽ʽ"};// ��ͷ
		dftm.setColumnIdentifiers(tableHeads);// ����Ĭ�ϱ��ģ�͵ı�ͷ
		scrollPane.setViewportView(table);// �ѱ��ģ����ӵ����������
	}
	// ���±������
	private void updateTable(Iterator iterator) {
		int rowCount=dftm.getRowCount();// ���Ĭ�ϵı��ģ���е�����
		for(int i=0;i<rowCount;i++) {
			dftm.removeRow(0);// �Ƴ����ģ���еĵ�һ��
		}
		while(iterator.hasNext()) {// ������Ϣ�ļ���������Ԫ�ؿ��Ե���
			Vector vector=new Vector();// ��������
			List view=(List) iterator.next();// ��õ�������һ������
			vector.addAll(view);// �������󼯺������е�Ԫ����ӵ���������
			dftm.addRow(vector);// ��Ĭ�ϵı��ģ�������������
		}
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();// �����������ƶ���
		gridBagConstrains.gridx = gridx;// �������λ������ĺ�������Ϊgridx
		gridBagConstrains.gridy = gridy;// �������λ���������������Ϊgridy
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);// ����˴˵ļ��
		if (gridwidth > 1)// ����������������1
			gridBagConstrains.gridwidth = gridwidth;// ����������������Ϊgridwidth
		if (ipadx > 0)// ����������Ĵ�С����0
			gridBagConstrains.ipadx = ipadx;// ��������������Ĵ�С
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
		getContentPane().add(component, gridBagConstrains);// ������
	}
	// ������ѯ
	private final class QueryAction implements ActionListener {// ����ѯ����ť��Ӷ����¼��ļ���
		public void actionPerformed(final ActionEvent e) {
			boolean selDate = selectDate.isSelected();// ��ָ����ѯ���ڡ���ѡ���Ƿ�ѡ��
			if(content.getText().equals("")) {// ���������ݡ��ı���Ϊ��
				JOptionPane.showMessageDialog(getContentPane(), "�������ѯ���ݣ�");// ������ʾ��
				return;
			}
			if(selDate) {// ��ָ����ѯ���ڡ���ѡ��ѡ��
				if(startDate.getText()==null||startDate.getText().equals("")) {// ����ʼ���ڡ��ı���Ϊ��
					JOptionPane.showMessageDialog(getContentPane(), "�������ѯ�Ŀ�ʼ���ڣ�");// ������ʾ��
					return;
				}
				if(endDate.getText()==null||endDate.getText().equals("")) {// ����ֹ���ڡ��ı���Ϊ��
					JOptionPane.showMessageDialog(getContentPane(), "�������ѯ�Ľ������ڣ�");// ������ʾ��
					return;
				}
			}
			List list=null;// �����
			String con = condition.getSelectedIndex() == 0 ? "khname " : "sellId ";// �жϡ��������ơ������б��б�ѡ�е��ǡ��ͻ�ȫ�ơ����ǡ�����Ʊ�š�
			int oper = operation.getSelectedIndex();// ��á��������㡱�����б�ѡ�е�ѡ��
			String opstr = oper == 0 ? "= " : "like ";// ���ݡ��������㡱�����б�ѡ�е�ѡ���ж�Ӧ����ֱ�Ӳ�ѯ����ģ����ѯ
			String cont = content.getText();// ��á��������ݡ��ı����е��ı�����
			list = Dao.findForList("select * from v_sellView where "
					+ con
					+ opstr
					+ (oper == 0 ? "'"+cont+"'" : "'%" + cont + "%'")
					+ (selDate ? " and xsdate>'" + startDate.getText()
							+ "' and xsdate<='" + endDate.getText()+" 23:59:59'" : ""));// ִ��ƴ�ӵ�SQL���󣬻�õ�������Ϣ�����
			Iterator iterator = list.iterator();// ����������Ϣ������ĵ�����
			updateTable(iterator);// ���±������
		}
	}
}