package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import com.mingrisoft.*;
import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.TbSpinfo;

public class KuCunPanDian extends JInternalFrame {// ����̵��ڲ�����

	private final JTable table;// ���ģ��
	private final JTextField pdsj = new JTextField();// ���̵�ʱ�䡱�ı���
	private final JTextField pzs = new JTextField("0");// ��Ʒ�������ı���
	private JTextField czy = new JTextField();// ������Ա���ı���
	private Date pdDate = new Date();// �̵�����

	public KuCunPanDian() {// ����̵��ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ�����
		setMaximizable(true);// �������
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر�
		getContentPane().setLayout(new GridBagLayout());// ���ÿ���̵��ڲ�����Ĳ���Ϊ���񲼾�
		setTitle("����̵�");// ���ÿ���̵��ڲ�����ı���
		setBounds(50, 50, 750, 400);// �������ÿ���̵��ڲ������λ�úͿ��
		// ������Ա����ǩ�롰����Ա���ı���
		setupComponet(new JLabel("�� �� Ա��"), 0, 0, 1, 0, false);
		czy.setFocusable(false);
		czy.setText(MainFrame.getCzyStateLabel().getText());
		czy.setPreferredSize(new Dimension(120, 21));
		setupComponet(czy, 1, 0, 1, 0, true);
		// ���̵�ʱ�䡱��ǩ�롰�̵�ʱ�䡱�ı���
		setupComponet(new JLabel("�̵�ʱ�䣺"), 2, 0, 1, 0, false);
		pdsj.setFocusable(false);
		pdsj.setText(pdDate.toLocaleString());
		pdsj.setPreferredSize(new Dimension(180, 21));
		setupComponet(pdsj, 3, 0, 1, 1, true);
		// ��Ʒ��������ǩ�롰Ʒ�������ı���
		setupComponet(new JLabel("Ʒ �� ����"), 4, 0, 1, 0, false);
		pzs.setFocusable(false);
		pzs.setPreferredSize(new Dimension(80, 21));
		setupComponet(pzs, 5, 0, 1, 20, true);
		// ���ģ��
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		// �������
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(700, 300));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);
	}

	private void initTable() {// ��ʼ�����
		String[] columnNames = { "��Ʒ����", "��Ʒ���", "��Ӧ��", "����", "��λ", "���", "����", "����", "��װ", "�̵�����", "��������" };// ��ͷ
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();// ��ñ��Ĭ��ģ��
		tableModel.setColumnIdentifiers(columnNames);// �滻ģ���еı�ͷ
		// �����̵��ֶ�ֻ������������
		final JTextField pdField = new JTextField(0);// ���̵㡱�ı���
		pdField.setEditable(false);// ���á��̵㡱�ı��򲻿ɱ༭
		pdField.addKeyListener(new PanDianKeyAdapter(pdField));// Ϊ���̵㡱�ı�������̵��ֶεİ���������
		JTextField readOnlyField = new JTextField(0);// ��ֻ�����ı���
		readOnlyField.setEditable(false);// ���á�ֻ�����ı��򲻿ɱ༭

		DefaultCellEditor pdEditor = new DefaultCellEditor(pdField);// ����ʹ�á��̵㡱�ı���Ϊ�������̵�༭��
		DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);// ����ʹ�á�ֻ�����ı���Ϊ������ֻ���༭��
		for (int i = 0; i < columnNames.length; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);// ��ñ���е�ÿһ��
			column.setCellEditor(readOnlyEditor);// ���ñ��ԪΪֻ����ʽ
		}
		TableColumn pdColumn = table.getColumnModel().getColumn(9);// �̵�����
		TableColumn syColumn = table.getColumnModel().getColumn(10);// ��������
		pdColumn.setCellEditor(pdEditor);// Ϊ�̵����������̵�༭��
		syColumn.setCellEditor(readOnlyEditor);// Ϊ������������ֻ���༭��
		// ��ʼ���������
		List kcInfos = Dao.getKucunInfos();// ��ÿ����Ϣ�ļ���
		for (int i = 0; i < kcInfos.size(); i++) {// ���������Ϣ�ļ���
			List info = (List) kcInfos.get(i);// ��ÿ����Ϣ�����е�Ԫ��
			Item item = new Item();// ���ݱ�����
			item.setId((String) info.get(0));// �����˱��
			item.setName((String) info.get(1));// ����������
			TbSpinfo spinfo = Dao.getSpInfo(item);// ��ȡ��Ʒ��Ϣ
			Object[] row = new Object[columnNames.length];// ��������Ϊ��ͷ���鳤�ȵ�����
			if (spinfo.getId() != null && !spinfo.getId().isEmpty()) {// �����Ʒ��Ų�Ϊ��
				row[0] = spinfo.getSpname();// ���������֮����Ʒ���ơ�
				row[1] = spinfo.getId();// ���������֮����Ʒ��š�
				row[2] = spinfo.getGysname();// ���������֮����Ӧ�����ơ�
				row[3] = spinfo.getCd();// ���������֮�����ء�
				row[4] = spinfo.getDw();// ���������֮����Ʒ������λ��
				row[5] = spinfo.getGg();// ���������֮����Ʒ���
				row[6] = info.get(2).toString();// ���������֮�����ۡ�
				row[7] = info.get(3).toString();// ���������֮��������
				row[8] = spinfo.getBz();// ���������֮����װ��
				row[9] = 0;// ���������֮���̵�������
				row[10] = 0;// ���������֮������������
				tableModel.addRow(row);// ����Ĭ��ģ�������������
				String pzsStr = pzs.getText();// ��á�Ʒ�������ı����е��ı�����
				int pzsInt = Integer.parseInt(pzsStr);// ��String�͵ġ�Ʒ������ת��Ϊint��
				pzsInt++;// ��Ʒ��������1
				pzs.setText(pzsInt + "");// ���á�Ʒ�������ı����е��ı�����
			}
		}
	}

	// �̵��ֶεİ���������
	private class PanDianKeyAdapter extends KeyAdapter {
		private final JTextField field;// ���̵㡱�ı���

		private PanDianKeyAdapter(JTextField field) {// ����ͬ����������Ϊͬ��������ֵ
			this.field = field;
		}

		public void keyTyped(KeyEvent e) {// ����ĳ����ʱ
			if (("0123456789" + (char) 8).indexOf(e.getKeyChar() + "") < 0) {// ��ǰ���µİ���û��0123456789�ķ�Χ��
				e.consume();// ���ٵ�ǰû��key�б���İ���
			}
			field.setEditable(true);// ���á��̵㡱�ı���ɱ༭
		}

		public void keyReleased(KeyEvent e) {// �ͷ�ĳ����ʱ
			String pdStr = field.getText();// ��á��̵㡱�ı�����ı�����
			String kcStr = "0";// ����String�͵ġ����������
			int row = table.getSelectedRow();// ��ñ�ѡ�е���
			if (row >= 0) {// ������ģ���д��ڱ�ѡ�е���
				kcStr = (String) table.getValueAt(row, 7);// ��ÿ������
			}
			try {
				int pdNum = Integer.parseInt(pdStr);// ��String�͵ġ��̵�������ת��Ϊint��
				int kcNum = Integer.parseInt(kcStr);// ��String�͵ġ����������ת��Ϊint��
				if (row >= 0) {// ������ģ���д��ڱ�ѡ�е���
					table.setValueAt(kcNum - pdNum, row, 10);// Ϊ����еġ�������������ֵ
				}
				if (e.getKeyChar() != 8)// ��ǰ���µİ���û��0123456789�ķ�Χ��
					field.setEditable(false);// ���̵㡱�ı��򲻿ɱ༭
			} catch (NumberFormatException e1) {
				field.setText("0");// ���á��̵㡱�ı����е��ı�����Ϊ0
			}
		}
	}

	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();// �����������ƶ���
		gridBagConstrains.gridx = gridx;// �������λ������ĺ�������Ϊgridx
		gridBagConstrains.gridy = gridy;// �������λ���������������Ϊgridy
		gridBagConstrains.insets = new Insets(5, 1, 3, 5);// ����˴˵ļ��
		if (gridwidth > 1)// ����������������1
			gridBagConstrains.gridwidth = gridwidth;// ����������������Ϊgridwidth
		if (ipadx > 0)// ����������Ĵ�С����0
			gridBagConstrains.ipadx = ipadx;// ��������������Ĵ�С
		if (fill)// ���ռ�ݿհ�����
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
		getContentPane().add(component, gridBagConstrains);// ������
	}
}
