package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.TbJsr;

public class XiaoShouPaiHang extends JInternalFrame {// ���������ڲ�����
	
	private JButton okButton;// ��ȷ������ť
	private JComboBox month;// ���¡������б�
	private JComboBox year;// ���ꡱ�����б�
	private JTable table;// ���ģ��
	private JComboBox operation;// ���������㡱�����б�
	private JComboBox condition;// ���������ݡ������б�
	private TbJsr user;// ��������Ϣ
	private DefaultTableModel dftm;// Ĭ�ϱ��ģ�͵�ʵ������
	private Calendar date = Calendar.getInstance();// �����ڡ�����
	
	public XiaoShouPaiHang() {// ���������ڲ�����Ĺ��췽��
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թرմ���
		setTitle("��������");// �������������ڲ�����ı���
		getContentPane().setLayout(new GridBagLayout());// �������������ڲ�����Ĳ���Ϊ���񲼾�
		setBounds(100, 100, 700, 375);// �������������ڲ������λ�úͿ��
		// ���ԡ���ǩ
		final JLabel label_1 = new JLabel();
		label_1.setText("��");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.anchor = GridBagConstraints.EAST;
		gridBagConstraints_8.gridy = 0;
		gridBagConstraints_8.gridx = 0;
		getContentPane().add(label_1, gridBagConstraints_8);
		// ���ꡱ�����б�
		year = new JComboBox();
		for (int i = 1981, j = 0; i <= date.get(Calendar.YEAR) + 1; i++, j++) {// ����ѭ�������ꡱ�����б������1981~(��ǰʱ�����+1)ѡ��
			year.addItem(i);
			if (i == date.get(Calendar.YEAR))// ��ǰʱ�����
				year.setSelectedIndex(j);// ѡ������ j �����б���
		}
		year.setPreferredSize(new Dimension(100, 21));
		setupComponet(year, 1, 0, 1, 90, true);
		// ���ꡱ��ǩ
		setupComponet(new JLabel("��"), 2, 0, 1, 1, false);
		// ���¡������б�
		month = new JComboBox();
		for (int i = 1; i <= 12; i++) {// ����ѭ�������¡������б������1~12
			month.addItem(String.format("%02d", i));// ��ʽ���·�
			if (date.get(Calendar.MONTH) == i)// ��ǰʱ���·�
				month.setSelectedIndex(i - 1);// ѡ������ (i - 1) �����б���
		}
		month.setPreferredSize(new Dimension(100, 21));
		setupComponet(month, 3, 0, 1, 30, true);
		// ���·ݵ�������Ϣ��������ǩ
		setupComponet(new JLabel(" �·ݵ�������Ϣ����"), 4, 0, 1, 1, false);
		condition = new JComboBox();
		condition.setModel(new DefaultComboBoxModel(new String[]{"���", "����"}));
		setupComponet(condition, 5, 0, 1, 30, true);
		// �����С���ǩ
		setupComponet(new JLabel(" ����"), 6, 0, 1, 1, false);
		// ���������㡱�����б�
		operation = new JComboBox();
		operation.setModel(new DefaultComboBoxModel(new String[]{"��������", "��������"}));
		setupComponet(operation, 7, 0, 1, 30, true);
		// ��ȷ������ť
		okButton = new JButton();
		okButton.addActionListener(new OkAction());// �����ȷ������ť�󣬷������¼�
		setupComponet(okButton, 8, 0, 1, 1, false);
		okButton.setText("ȷ��");
		// �������
		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
		// ���ģ��
		table = new JTable();
		table.setEnabled(false);// ���ñ��ģ���޷���Ӧ�û�����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		dftm = (DefaultTableModel) table.getModel();// ���Ĭ�ϵı��ģ��
		String[] tableHeads = new String[]{"��Ʒ���", "��Ʒ����", "���۽��", "��������",
				"���", "����", "��λ", "���", "��װ", "����", "��׼�ĺ�","���","��Ӧ��"};// ��ͷ
		dftm.setColumnIdentifiers(tableHeads);// ����Ĭ�ϱ��ģ�͵ı�ͷ
		scrollPane.setViewportView(table);// �ѱ��ģ����ӵ����������
	}
	// ���ı������
	private void updateTable(Iterator iterator) {
		int rowCount = dftm.getRowCount();// ���Ĭ�ϵı��ģ���е�����
		for (int i = 0; i < rowCount; i++) {
			dftm.removeRow(0);// �Ƴ����ģ���еĵ�һ��
		}
		while (iterator.hasNext()) {// ������Ϣ�ļ���������Ԫ�ؿ��Ե���
			Vector vector = new Vector();// ��������
			List view = (List) iterator.next();// ��õ�������һ������
			Vector row = new Vector(view);// ����һ������ָ������view�е�Ԫ�ص�����
			int rowSize = row.size();// ��������е�Ԫ�ظ���
			for(int i = rowSize - 2; i < rowSize; i++){// ͨ��ѭ������������������Ԫ�ص�����
				Object colValue = row.get(i);// ���������ָ����������Ԫ��
				row.remove(i);// �Ƴ���������ָ����������Ԫ��
				row.insertElementAt(colValue, 2);// ��������ָ����������Ԫ�ز��뵽ָ��������Ϊ2��λ����
			}
			vector.addAll(row);// �������󼯺������е�Ԫ����ӵ���������
			dftm.addRow(vector);// ��Ĭ�ϵı��ģ�������������
		}
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
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
	// �����ȷ������ť�󣬷������¼�
	private final class OkAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			List list = null;// �������������ġ���Ʒ��Ϣ������
			String strMonth = (String) month.getSelectedItem();// ��ñ�ѡ�е��·�
			String date = year.getSelectedItem() + "-" + strMonth;// ��ú����ꡢ�µ�����
			String con = condition.getSelectedIndex() == 0 ? "sumje " : "sl ";// �жϡ��������ݡ������б�ѡ�е��ǡ������ǡ�������
			int oper = operation.getSelectedIndex();// ��á��������㡱�����б�ѡ�е�ѡ������
			String opstr = oper == 0 ? " asc" : " desc";// �жϡ��������㡱�����б�ѡ�е��ǡ��������С����ǡ��������С�
			String sql1 = "select spid,sum(sl)as sl,sum(sl*dj) as sumje from v_sellView "
						+ "where left (xsdate,7)='" + date + "' group by spid";// ��÷����ꡢ�µ�������Ʒ��Ϣ��SQL���
			String queryStr = "select * from tb_spinfo s inner join (" + sql1
							+ ") as sp on s.id=sp.spid order by " + con + opstr;// ����������е�SQL���
			list = Dao.findForList(queryStr);// ��÷��������ġ���Ʒ��Ϣ������
			Iterator iterator = list.iterator();// ��÷��������ġ���Ʒ��Ϣ�����ϵĵ�����
			updateTable(iterator);// ���ı������
		}
	}
}
