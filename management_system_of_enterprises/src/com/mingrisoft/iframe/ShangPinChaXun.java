package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mingrisoft.Item;
import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.TbSpinfo;

public class ShangPinChaXun extends JInternalFrame {// ��Ʒ��ѯ�ڲ�����
	
	private JTable table;// ���ģ��
	private JTextField conditionContent;// ���������ݡ��ı���
	private JComboBox conditionOperation;// ���������㡱�����б�
	private JComboBox conditionName;// ���������ơ������б�
	
	public ShangPinChaXun() {// ��Ʒ��ѯ�ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ��췽��
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر�
		setTitle("��Ʒ��Ϣ��ѯ");// ������Ʒ��ѯ�ڲ�����ı���
		getContentPane().setLayout(new GridBagLayout());// ������Ʒ��ѯ�ڲ�����Ĳ���Ϊ���񲼾�
		setBounds(100, 100, 650, 375);// ������Ʒ��ѯ�ڲ������λ�úͿ��
		// ���ģ��
		table = new JTable();
		table.setEnabled(false);// ���ñ��ģ���޷���Ӧ�û�����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();// ���Ĭ�ϱ��ģ��
		String[] tableHeads = new String[]{"�ͻ�ID", "��Ʒ����", "���", "����", "��λ",
				"���", "��װ", "����", "��׼�ĺ�", "��Ӧ��ȫ��", "��ע",};// ��ͷ
		dftm.setColumnIdentifiers(tableHeads);// ����Ĭ�ϱ��ģ�͵ı�ͷ
		// �������
		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
		// ��ѡ���ѯ��������ǩ�͡��������ơ������б�
		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"��Ʒ����", "��Ӧ��ȫ��", "����", "���"}));
		setupComponet(conditionName, 1, 0, 1, 30, true);
		// ���������㡱�����б�
		conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"����", "����"}));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);
		// ���������ݡ��ı���
		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);
		// ����ѯ����ť
		final JButton queryButton = new JButton();
		queryButton.addActionListener(new QueryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("��ѯ");
		// ����ʾȫ�����ݡ���ť
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");// ���á��������ݡ��ı���Ϊ��
				List list = Dao.getSpInfos();// ��ȡ������Ʒ��Ϣ�ļ���
				updateTable(list, dftm);// ���±������
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("��ʾȫ������");
	}
	// �������ʾȫ�����ݡ���ť�󣬸��±������
	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();// ��ñ��ģ���е�����
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);// �Ƴ����ģ���еĵ�һ��
		Iterator iterator = list.iterator();// ����������Ʒ��Ϣ�ļ��ϵĵ�����
		TbSpinfo spInfo;// ��Ʒ��Ϣ
		while (iterator.hasNext()) {// ������Ʒ��Ϣ�ļ���������Ԫ�ؿ��Ե���
			List info = (List) iterator.next();// ��õ�������һ������
			Item item = new Item();// ���ݱ�����
			item.setId((String) info.get(0));// �������
			item.setName((String) info.get(1));// ������Ϣ
			spInfo = Dao.getSpInfo(item);// ��ȡ��Ʒ��Ϣ
			Vector rowData = new Vector();// ��������
			rowData.add(spInfo.getId().trim());// ��Ʒ���
			rowData.add(spInfo.getSpname().trim());// ��Ʒ����
			rowData.add(spInfo.getJc());// ��Ʒ���
			rowData.add(spInfo.getCd());// ����
			rowData.add(spInfo.getDw());// ��Ʒ������λ
			rowData.add(spInfo.getGg());// ��Ʒ���
			rowData.add(spInfo.getBz());// ��װ
			rowData.add(spInfo.getPh());// ����
			rowData.add(spInfo.getPzwh());// ��׼�ĺ�
			rowData.add(spInfo.getGysname());// ��Ӧ������
			rowData.add(spInfo.getMemo());// ��ע
			dftm.addRow(rowData);// ���������������ݣ���Ʒ��Ϣ��
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
	// ������ѯ
	private final class QueryAction implements ActionListener {
		private final DefaultTableModel dftm;// Ĭ�ϵı��ģ��
		private QueryAction(DefaultTableModel dftm) {// ����ͬ����������Ϊͬ��������ֵ
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e) {
			String conName, conOperation, content;// ����String�͵ġ��������ơ������������㡱�����������ݡ�
			conName = conditionName.getSelectedItem().toString().trim();// ��á��������ơ�
			conOperation = conditionOperation.getSelectedItem().toString();// ��á��������㡱
			content = conditionContent.getText().trim();// ��á��������ݡ�
			List list = null;// ��������Ʒ��Ϣ���ļ���
			list = searchInfo(conName, conOperation, content, list);// ��÷��������ġ���Ʒ��Ϣ������
			updateTable(list, dftm);// ���±������
		}
		// ƴ��SQL��䣬�����ִ��SQL������Ӧ�Ľ����
		private List searchInfo(String conName, String conOperation,
				String content, List list) {
			String sql = "select * from Tb_Spinfo where ";// ��ʼ��SQL���
			if (conOperation.equals("����")) {// ���������㡱Ϊ�����ڡ�
				if (conName.equals("��Ʒ����"))// ���������ơ�Ϊ����Ʒ���ơ�
					list = Dao.findForList(sql + "spname='" + content + "'");// ���ĳһ����Ʒ���ơ��ġ���Ʒ��Ϣ������
				if (conName.equals("��Ӧ��ȫ��"))// ���������ơ�Ϊ����Ӧ��ȫ�ơ�
					list = Dao.findForList(sql + "gysname='" + content + "'");// ���ĳһ����Ӧ��ȫ�ơ��ġ���Ʒ��Ϣ������
				if (conName.equals("����"))// ���������ơ�Ϊ�����ء�
					list = Dao.findForList(sql + "cd='" + content + "'");// ���ĳһ�����ء��ġ���Ʒ��Ϣ������
				if (conName.equals("���"))// ���������ơ�Ϊ�����
					list = Dao.findForList(sql + "gg='" + content + "'");// ���ĳһ����񡱵ġ���Ʒ��Ϣ������
			} else {
				if (conName.equals("��Ʒ����"))// ���������ơ�Ϊ����Ʒ���ơ�
					list = Dao.findForList(sql + "spname like '%" + content + "%'");// ��á���Ʒ���ơ��к��С��������ݡ��ġ���Ʒ��Ϣ������
				if (conName.equals("��Ӧ��ȫ��"))// ���������ơ�Ϊ����Ӧ��ȫ�ơ�
					list = Dao.findForList(sql + "gysname like '%" + content + "%'");// ��á���Ӧ��ȫ�ơ��к��С��������ݡ��ġ���Ʒ��Ϣ������
				if (conName.equals("����"))// ���������ơ�Ϊ�����ء�
					list = Dao.findForList(sql + "cd like '%" + content + "%'");// ��á����ء��к��С��������ݡ��ġ���Ʒ��Ϣ������
				if (conName.equals("���"))// ���������ơ�Ϊ�����
					list = Dao.findForList(sql + "gg like '%" + content + "%'");// ��á�����к��С��������ݡ��ġ���Ʒ��Ϣ������
			}
			return list;
		}
	}
}
