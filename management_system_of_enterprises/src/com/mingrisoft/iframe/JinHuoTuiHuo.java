package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import com.mingrisoft.*;
import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.*;

public class JinHuoTuiHuo extends JInternalFrame {// ����-�˻��ڲ�����

	private final JTable table;// ���ģ��
	private final JTextField thsj = new JTextField();// ���˻�ʱ�䡱�ı���
	private JComboBox jsr = null;// �������ˡ������б�
	private final JComboBox jsfs = new JComboBox();// �����㷽ʽ�������б�
	private final JTextField lian = new JTextField();// ����ϵ�ˡ��ı���
	private final JComboBox gys = new JComboBox();// ����Ӧ�̡������б�
	private final JTextField piaoHao = new JTextField();// ���˻�Ʊ�š��ı���
	private final JTextField pzs = new JTextField("0");// ��Ʒ���������ı���
	private final JTextField hpzs = new JTextField("0");// ����Ʒ�������ı���
	private final JTextField hjje = new JTextField("0");// ���ϼƽ��ı���
	private final JTextField ysjl = new JTextField();// ���˻�ԭ���ı���
	private final JTextField czy = new JTextField();// ������Ա���ı���
	private Date thsjDate;// ���˻�ʱ�䡱
	private JComboBox sp;// ����Ʒ�������б�

	public JinHuoTuiHuo() {// ����-�˻��ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ�����
		setMaximizable(true);// �������
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ����-�˻��ڲ�������Թر�
		getContentPane().setLayout(new GridBagLayout());// ���ý���-�˻��ڲ�����Ĳ���Ϊ���񲼾�
		setTitle("����-�˻�");// ���ý���-�˻��ڲ�����ı���
		setBounds(50, 50, 700, 400);// ���ý���-�˻��ڲ������λ�úͿ��
		// ���˻�Ʊ�š���ǩ�롰�˻�Ʊ�š��ı���
		setupComponet(new JLabel("�˻�Ʊ�ţ�"), 0, 0, 1, 0, false);
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 140, true);
		// ����Ӧ�̡���ǩ�롰��Ӧ�̡������б�
		setupComponet(new JLabel("��Ӧ�̣�"), 2, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(160, 21));
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();// ����Ӧ�̡������б��ѡ���¼�
			}
		});
		setupComponet(gys, 3, 0, 1, 1, true);
		// ����ϵ�ˡ���ǩ�롰��ϵ�ˡ��ı���
		setupComponet(new JLabel("��ϵ�ˣ�"), 4, 0, 1, 0, false);
		lian.setFocusable(false);
		lian.setPreferredSize(new Dimension(80, 21));
		setupComponet(lian, 5, 0, 1, 0, true);
		// �����㷽ʽ����ǩ�롰���㷽ʽ�������б�
		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		jsfs.addItem("�ֽ�");
		jsfs.addItem("֧Ʊ");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);
		// ���˻�ʱ�䡱��ǩ�롰�˻�ʱ�䡱�ı���
		setupComponet(new JLabel("�˻�ʱ�䣺"), 2, 1, 1, 0, false);
		thsj.setFocusable(false);
		setupComponet(thsj, 3, 1, 1, 1, true);
		// �������ˡ���ǩ�롰�����ˡ������б�
		setupComponet(new JLabel("�����ˣ�"), 4, 1, 1, 0, false);
		setupComponet(getJsrComboBox(), 5, 1, 1, 1, true);
		// ����Ʒ�������б�
		sp = new JComboBox();
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TbKucun info = (TbKucun) sp.getSelectedItem();
				if (info != null && info.getId() != null) {// ���ѡ����Ч�͸��±��
					updateTable();
				}
			}
		});
		// ���ģ��
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		initTable();// ��ʼ�����
		table.addContainerListener(new computeInfo());// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);
		// ��Ʒ����������ǩ�롰Ʒ���������ı���
		setupComponet(new JLabel("Ʒ��������"), 0, 3, 1, 0, false);
		pzs.setFocusable(false);
		setupComponet(pzs, 1, 3, 1, 1, true);
		// ����Ʒ��������ǩ�롰��Ʒ�������ı���
		setupComponet(new JLabel("��Ʒ������"), 2, 3, 1, 0, false);
		hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 3, 1, 1, true);
		// ���ϼƽ���ǩ�롰�ϼƽ��ı���
		setupComponet(new JLabel("�ϼƽ�"), 4, 3, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 5, 3, 1, 1, true);
		// ���˻�ԭ�򡱱�ǩ�롰�˻�ԭ���ı���
		setupComponet(new JLabel("�˻�ԭ��"), 0, 4, 1, 0, false);
		setupComponet(ysjl, 1, 4, 1, 1, true);
		// ��������Ա����ǩ�롰������Ա���ı���
		setupComponet(new JLabel("������Ա��"), 2, 4, 1, 0, false);
		czy.setFocusable(false);
		czy.setText(MainFrame.getCzyStateLabel().getText());
		setupComponet(czy, 3, 4, 1, 1, true);
		// ����ӡ���ť
		JButton tjButton = new JButton("���");
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ʼ���˻�Ʊ��
				initPiaoHao();
				// ֹͣ���Ԫ�ı༭
				stopTableCellEditing();
				// �������л��������У��Ͳ����������
				for (int i = 0; i < table.getRowCount(); i++) {
					TbKucun info = (TbKucun) table.getValueAt(i, 0);
					if (info == null || info.getId().isEmpty())
						return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();// ����Ĭ�ϵı��ģ�Ͷ���
				model.addRow(new Vector());// ��Ĭ�ϵı��ģ�Ͷ�����ӿ���
				initSpBox();// ��ʼ������Ʒ�������б�
			}
		});
		setupComponet(tjButton, 4, 4, 1, 1, false);
		// ���˻�����ť
		JButton rkButton = new JButton("�˻�");
		rkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ֹͣ���Ԫ�ı༭
				stopTableCellEditing();
				// �������
				clearEmptyRow();
				String hpzsStr = hpzs.getText();// ��Ʒ����
				String pzsStr = pzs.getText();// Ʒ����
				String jeStr = hjje.getText();// �ϼƽ��
				String jsfsStr = jsfs.getSelectedItem().toString();// ���㷽ʽ
				String jsrStr = jsr.getSelectedItem() + "";// ������
				String czyStr = czy.getText();// ����Ա
				String rkDate = thsjDate.toLocaleString();// ���ʱ��
				String ysjlStr = ysjl.getText().trim();// �˻�ԭ��
				String id = piaoHao.getText();// Ʊ��
				String gysName = gys.getSelectedItem().toString();// ��Ӧ������
				if (jsrStr == null || jsrStr.isEmpty()) {// �������ˡ������б����ڻ򡰾����ˡ������б�Ϊ��
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "����д������");
					return;
				}
				if (ysjlStr == null || ysjlStr.isEmpty()) {// ���˻�ԭ���ı��򲻴��ڻ��˻�ԭ���ı���Ϊ��
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "��д�˻�ԭ��");
					return;
				}
				if (table.getRowCount() <= 0) {// ���ģ�͵�����С�ڵ���0
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "����˻���Ʒ");
					return;
				}
				TbRkthMain rkthMain = new TbRkthMain(id, pzsStr, jeStr, ysjlStr, gysName, rkDate, czyStr, jsrStr, jsfsStr);// �����˻�����
				Set<TbRkthDetail> set = rkthMain.getTbRkthDetails();// �洢�����˻���ϸ��Ϣ�ļ���
				int rows = table.getRowCount();// ��ñ��ģ���е�����
				for (int i = 0; i < rows; i++) {
					TbKucun kucun = (TbKucun) table.getValueAt(i, 0);// �����Ϣ
					String djStr = (String) table.getValueAt(i, 6);// ����
					String slStr = (String) table.getValueAt(i, 7);// �������
					Double dj = Double.valueOf(djStr);// ��String�͵ĵ���ת��ΪDouble��
					Integer sl = Integer.valueOf(slStr);// ��String�͵Ŀ������ת��ΪInteger��
					TbRkthDetail detail = new TbRkthDetail();// �����˻���ϸ��Ϣ
					detail.setSpid(kucun.getId());// �����˻����
					detail.setTbRkthMain(rkthMain.getRkthId());// �����˻�����
					detail.setDj(dj);// ����
					detail.setSl(sl);// ����
					set.add(detail);// ��ӽ����˻���ϸ��Ϣ
				}
				boolean rs = Dao.insertRkthInfo(rkthMain);// �Ƿ�ɹ���������Ϣ
				if (rs) {
					JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "�˻����");// ������ʾ��
					DefaultTableModel dftm = new DefaultTableModel();// ����Ĭ�ϵı��ģ�Ͷ���
					table.setModel(dftm);// ����������ģ������Ϊdftm
					initTable();// ��ʼ�����
					pzs.setText("0");// ���á�Ʒ���������ı����е�����Ϊ0
					hpzs.setText("0");// ���á���Ʒ�������ı����е�����Ϊ0
					hjje.setText("0");// ���á��ϼƽ��ı����е�����Ϊ0
				}
			}
		});
		setupComponet(rkButton, 5, 4, 1, 1, false);
		addInternalFrameListener(new initTasks());// ��Ӵ������������ɳ�ʼ��
	}

	/**
	 * ��á������ˡ������б�ķ���
	 * 
	 * @return �������ˡ������б�
	 */
	private JComboBox getJsrComboBox() {
		if (jsr == null) {// ����������ˡ������б�����
			jsr = new JComboBox();// �����������ˡ������б�
			List<List> czyList = Dao.getJsrs();// ��ñ����õľ����˼���
			for (List<String> list : czyList) {// ���������õľ����˼���
				String id = list.get(0);// �����˱��
				String name = list.get(1);// ����������
				Item item = new Item(id, name);// ���ݱ�����
				item.setId(id + "");// �������
				item.setName(name);// ������Ϣ
				jsr.addItem(item);// �򡰾����ˡ������б�����Ӿ�����
			}
		}
		return jsr;
	}

	private void initTable() {// ��ʼ�����
		String[] columnNames = { "��Ʒ����", "��Ʒ���", "����", "��λ", "���", "��װ", "����", "����" };// ��ͷ
		((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);// �������ģ�Ͷ�����������ӱ�ͷ
		TableColumn column = table.getColumnModel().getColumn(0);// �Ա��ģ��������Ϊ0����������
		final DefaultCellEditor editor = new DefaultCellEditor(sp);// ������Ԫ��༭��
		editor.setClickCountToStart(2);// ��ʼ�༭��Ԫ������ĵ�������Ϊ2��
		column.setCellEditor(editor);// �༭�������е�Ԫ��ʱ���õı༭��
	}

	private void initSpBox() {// ��ʼ������Ʒ�������б�
		List list = new ArrayList();// �����洢�����Ϣ�ļ���
		ResultSet set = Dao.query("select * from tb_kucun where id in(select id from tb_spinfo where gysName='"
				+ gys.getSelectedItem() + "')");// �����Ʒ��Ϣ�Ľ����
		sp.removeAllItems();// �Ƴ�����Ʒ�������б��е�ѡ��
		sp.addItem(new TbKucun());// ����Ʒ�������б������ѡ��
		for (int i = 0; table != null && i < table.getRowCount(); i++) {
			TbKucun tmpInfo = (TbKucun) table.getValueAt(i, 0);// ��ÿ����Ϣ
			if (tmpInfo != null && tmpInfo.getId() != null)// ��������Ϣ����Ʒ��Ŷ���Ϊ��
				list.add(tmpInfo.getId());// ������Ϣ�ļ����������Ʒ���
		}
		try {
			while (set.next()) {// �ƶ���ļ�¼ָ��ָ��һ����Ч�ļ�¼
				TbKucun kucun = new TbKucun();// �����Ϣ
				kucun.setId(set.getString("id").trim());// ��Ʒ���
				if (list.contains(kucun.getId()))// ���������Ѵ���ͬ����Ʒ����Ʒ�������оͲ��ٰ�������Ʒ
					continue;
				kucun.setSpname(set.getString("spname").trim());// ��Ʒ����
				kucun.setCd(set.getString("cd").trim());// ����
				kucun.setJc(set.getString("jc").trim());// ��Ʒ���
				kucun.setDw(set.getString("dw").trim());// ��Ʒ������λ
				kucun.setGg(set.getString("gg").trim());// ��Ʒ���
				kucun.setBz(set.getString("bz").trim());// ��װ
				kucun.setDj(Double.valueOf(set.getString("dj").trim()));// ����
				kucun.setKcsl(Integer.valueOf(set.getString("kcsl").trim()));// �������
				sp.addItem(kucun);// ����Ʒ�������б�����ӿ����Ϣ
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {// �������λ�ò���ӵ�������
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();// �����������ƶ���
		gridBagConstrains.gridx = gridx;// �������λ������ĺ�������Ϊgridx
		gridBagConstrains.gridy = gridy;// �������λ���������������Ϊgridy
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);// ����˴˵ļ��
		if (gridwidth > 1)// ����������������1
			gridBagConstrains.gridwidth = gridwidth;// ����������������Ϊgridwidth
		if (ipadx > 0)// ����������Ĵ�С����0
			gridBagConstrains.ipadx = ipadx;// ��������������Ĵ�С
		if (fill)// ���ռ�ݿհ�����
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
		getContentPane().add(component, gridBagConstrains);// ������
	}

	private void doGysSelectAction() {// ����Ӧ�̡������б��ѡ���¼�
		Item item = (Item) gys.getSelectedItem();// ��ñ�ѡ�еĹ�Ӧ��
		TbGysinfo gysInfo = Dao.getGysInfo(item);// ��ȡָ����Ӧ����Ϣ
		lian.setText(gysInfo.getLian());// ���á���ϵ�ˡ��ı����е��ı�����
	}

	private final class computeInfo implements ContainerListener {// ���¼��м���Ʒ����������Ʒ�������ϼƽ��
		public void componentRemoved(ContainerEvent e) {
			// �������
			clearEmptyRow();
			int rows = table.getRowCount();// ��ñ��ģ���е�����
			int count = 0;// ����Ʒ������
			double money = 0.0;// ���ϼƽ�
			TbKucun column = null;// �������Ϣ����ʵ��
			if (rows > 0)// ���ģ���е���������0
				column = (TbKucun) table.getValueAt(rows - 1, 0);// Ϊ�������Ϣ����ʵ����ֵ
			if (rows > 0 && (column == null || column.getId().isEmpty()))// ���ģ���е���������0�ҡ���Ʒ��Ϣ����ʵ�������ڻ���Ʒ���Ϊ��
				rows--;// ���ģ���е�������һ
			// �����Ʒ�����ͽ��
			for (int i = 0; i < rows; i++) {
				String column7 = (String) table.getValueAt(i, 7);// ��ñ���С�������
				String column6 = (String) table.getValueAt(i, 6);// ��ñ���С����ۡ�
				int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer.parseInt(column7);// ��String���͵ġ�������ת��Ϊint��
				Double c6 = (column6 == null || column6.isEmpty()) ? 0 : Double.valueOf(column6);// ��String���͵ġ����ۡ�ת��ΪDouble��
				count += c7;// �����Ʒ����
				money += c6 * c7;// ����ϼƽ��
			}
			pzs.setText(rows + "");// ���á�Ʒ���������ı����е��ı�����
			hpzs.setText(count + "");// ���á���Ʒ�������ı����е��ı�����
			hjje.setText(money + "");// ���á��ϼƽ��ı����е��ı�����
		}

		public void componentAdded(ContainerEvent e) {
		}
	}

	private final class initTasks extends InternalFrameAdapter {// ����ĳ�ʼ������
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();// �����˻�ʱ���߳�
			initGysField();// ��ʼ����Ӧ���ֶ�
			initPiaoHao();// ��ʼ�����˻�Ʊ�š�
			initSpBox();// ��ʼ������Ʒ�������б�
		}

		private void initGysField() {// ��ʼ����Ӧ���ֶ�
			List gysInfos = Dao.getGysInfos();// ������й�Ӧ����Ϣ�ļ���
			for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {// �������й�Ӧ����Ϣ�ļ���
				List list = (List) iter.next();// ��õ�������һ������
				Item item = new Item();// ���ݱ�����
				item.setId(list.get(0).toString().trim());// �������
				item.setName(list.get(1).toString().trim());// ������Ϣ
				gys.addItem(item);// �򡰹�Ӧ�̡������б������ѡ��
			}
			doGysSelectAction();
		}

		private void initTimeField() {// �����˻�ʱ���߳�
			new Thread(new Runnable() {
				public void run() {
					try {
						while (true) {
							thsjDate = new Date();// �������ڶ���
							thsj.setText(thsjDate.toLocaleString());// ���á��˻�ʱ�䡱�ı����е��ı�����
							Thread.sleep(1000);// �߳�����1��
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();// �����߳�
		}
	}

	private void initPiaoHao() {// ��ʼ�����˻�Ʊ�š�
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());// ʹ��ϵͳʱ��ֵ����һ�����ڶ���
		String maxId = Dao.getRkthMainMaxId(date);// ��ȡ�˻����ID
		piaoHao.setText(maxId);// ���á��˻�Ʊ�š��ı����е��ı�����
	}

	private synchronized void updateTable() {// ������Ʒ�������ѡ�񣬸��±��ǰ�е�����
		TbKucun kucun = (TbKucun) sp.getSelectedItem();// ��á���Ʒ�������б��б�ѡ�е�ѡ��
		int row = table.getSelectedRow();// ��ñ��ģ���б�ѡ�е���
		if (row >= 0 && kucun != null) {// ���ģ���б�ѡ�е��д��ڵ���0�ҡ���Ʒ�������б��б�ѡ�е�ѡ�Ϊ��
			table.setValueAt(kucun.getId(), row, 1);// ��Ʒ���
			table.setValueAt(kucun.getCd(), row, 2);// ����
			table.setValueAt(kucun.getDw(), row, 3);// ��Ʒ������λ
			table.setValueAt(kucun.getGg(), row, 4);// ��Ʒ���
			table.setValueAt(kucun.getBz(), row, 5);// ��װ
			table.setValueAt(kucun.getDj().toString(), row, 6);// ����
			table.setValueAt(kucun.getKcsl().toString(), row, 7);// �������
			table.editCellAt(row, 7);// ��������ɱ༭
		}
	}

	private synchronized void clearEmptyRow() {// �������
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();// ���Ĭ�ϵı��ģ��
		for (int i = 0; i < table.getRowCount(); i++) {
			TbKucun kucun = (TbKucun) table.getValueAt(i, 0);// ��ÿ����Ϣ
			// �����ϢΪ��
			if (kucun == null || kucun.getId() == null || kucun.getId().isEmpty()) {
				dftm.removeRow(i);// �Ƴ�Ĭ�ϵı��ģ���еĸ���
			}
		}
	}
	/**
	 * �÷�������ֹͣ���Ԫ�ı༭
	 */
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();// �������Ԫ�༭��
		if (cellEditor != null)// ���Ԫ�༭������
			cellEditor.stopCellEditing();// ֹͣ���Ԫ�ı༭
	}
}
