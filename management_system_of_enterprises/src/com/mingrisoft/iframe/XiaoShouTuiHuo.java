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

public class XiaoShouTuiHuo extends JInternalFrame {// �����˻��ڲ�����
	
	private final JTable table;// ���ģ��
	private final JTextField thsj = new JTextField();// ���˻�ʱ�䡱�ı���
	private JComboBox jsr = null;// �������ˡ������б�
	private final JComboBox jsfs = new JComboBox();// �����㷽ʽ�������б�
	private final JTextField lian = new JTextField();// ����ϵ�ˡ��ı���
	private final JComboBox kehu = new JComboBox();// ���ͻ��������б�
	private final JTextField piaoHao = new JTextField();// ������Ʊ�š��ı���
	private final JTextField pzs = new JTextField("0");// ��Ʒ���������ı���
	private final JTextField hpzs = new JTextField("0");// ����Ʒ�������ı���
	private final JTextField hjje = new JTextField("0");// ���ϼƽ��ı���
	private final JTextField ysjl = new JTextField();// �����ս��ۡ��ı���
	private final JTextField czy = new JTextField();// ������Ա���ı���
	private Date thsjDate;// �˻�����
	private JComboBox sp;// ����Ʒ�������б�

	public XiaoShouTuiHuo() {// �����˻��ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ�����
		setMaximizable(true);// �������
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر����۵��ڲ�����
		getContentPane().setLayout(new GridBagLayout());// ���������˻��ڲ�����Ĳ���Ϊ���񲼾�
		setTitle("�����˻�");// ���������˻��ڲ�����ı���
		setBounds(50, 50, 700, 400);// ���������˻��ڲ������λ�úͿ��
		// ������Ʊ�š���ǩ�͡�����Ʊ�š��ı���
		setupComponet(new JLabel("����Ʊ�ţ�"), 0, 0, 1, 0, false);
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 140, true);
		// ���ͻ�����ǩ�͡��ͻ��������б�
		setupComponet(new JLabel("�ͻ���"), 2, 0, 1, 0, false);
		kehu.setPreferredSize(new Dimension(160, 21));
		kehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doKhSelectAction();// ���ͻ��������б��ѡ���¼�
			}
		});
		setupComponet(kehu, 3, 0, 1, 1, true);
		// ����ϵ�ˡ���ǩ�͡���ϵ�ˡ��ı���
		setupComponet(new JLabel("��ϵ�ˣ�"), 4, 0, 1, 0, false);
		lian.setFocusable(false);
		lian.setPreferredSize(new Dimension(80, 21));
		setupComponet(lian, 5, 0, 1, 0, true);
		// �����㷽ʽ����ǩ�͡����㷽ʽ�������б�
		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		jsfs.addItem("�ֽ�");
		jsfs.addItem("֧Ʊ");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);
		// ���˻�ʱ�䡱��ǩ�͡��˻�ʱ�䡱�ı���
		setupComponet(new JLabel("�˻�ʱ�䣺"), 2, 1, 1, 0, false);
		thsj.setFocusable(false);
		setupComponet(thsj, 3, 1, 1, 1, true);
		// �������ˡ���ǩ�͡������ˡ������б�
		setupComponet(new JLabel("�����ˣ�"), 4, 1, 1, 0, false);
		setupComponet(getJsrComboBox(), 5, 1, 1, 1, true);
		// ����Ʒ�������б�
		sp = new JComboBox();
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TbSpinfo info = (TbSpinfo) sp.getSelectedItem();// ��ñ�ѡ�е���Ʒ��Ϣ
				if (info != null && info.getId() != null) {
					updateTable();// ���ѡ����Ч�͸��±��
				}
			}
		});
		// ���ģ��
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		initTable();// ��ʼ�����ģ��
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);
		// ��Ʒ����������ǩ�͡�Ʒ���������ı���
		setupComponet(new JLabel("Ʒ��������"), 0, 3, 1, 0, false);
		pzs.setFocusable(false);
		setupComponet(pzs, 1, 3, 1, 1, true);
		// ����Ʒ��������ǩ�͡���Ʒ�������ı���
		setupComponet(new JLabel("��Ʒ������"), 2, 3, 1, 0, false);
		hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 3, 1, 1, true);
		// ���ϼƽ���ǩ�͡��ϼƽ��ı���
		setupComponet(new JLabel("�ϼƽ�"), 4, 3, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 5, 3, 1, 1, true);
		// �����ս��ۡ���ǩ�͡����ս��ۡ��ı���
		setupComponet(new JLabel("���ս��ۣ�"), 0, 4, 1, 0, false);
		setupComponet(ysjl, 1, 4, 1, 1, true);
		// ��������Ա����ǩ�͡�������Ա���ı���
		setupComponet(new JLabel("������Ա��"), 2, 4, 1, 0, false);
		czy.setFocusable(false);
		czy.setText(MainFrame.getCzyStateLabel().getText());
		setupComponet(czy, 3, 4, 1, 1, true);
		// ����ӡ���ť
		JButton tjButton = new JButton("���");
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ʼ��Ʊ��
				initPiaoHao();
				// ֹͣ���Ԫ�ı༭
				stopTableCellEditing();
				// �������л��������У��Ͳ����������
				for (int i = 0; i < table.getRowCount(); i++) {
					TbSpinfo info = (TbSpinfo) table.getValueAt(i, 0);
					if (info == null || info.getId().isEmpty())
						return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();// ����Ĭ�ϵı��ģ�Ͷ���
				model.addRow(new Vector());// ��Ĭ�ϵı��ģ�Ͷ�����ӿ���
				initSpBox();// ��ʼ����Ʒ�����б�
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
				String ysjlStr = ysjl.getText().trim();// ���ս���
				String id = piaoHao.getText();// Ʊ��
				String gysName = kehu.getSelectedItem().toString();// ��Ӧ������
				if (jsrStr == null || jsrStr.isEmpty()) {// �������ˡ�Ϊ��
					JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "����д������");
					return;
				}
				if (ysjlStr == null || ysjlStr.isEmpty()) {// �����ս��ۡ�Ϊ��
					JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "��д���ս���");
					return;
				}
				if (table.getRowCount() <= 0) {// ���ģ�͵�����С�ڵ���0
					JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "����˻���Ʒ");
					return;
				}
				TbXsthMain xsthMain = new TbXsthMain(id, pzsStr, jeStr, ysjlStr, gysName, rkDate, czyStr, jsrStr, jsfsStr);// �����˻�����
				Set<TbXsthDetail> set = xsthMain.getTbXsthDetails();// ��������˻���ϸ��Ϣ�ļ���
				int rows = table.getRowCount();// ��ñ��ģ���е�����
				for (int i = 0; i < rows; i++) {
					TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);// ��Ʒ��Ϣ
					String djStr = (String) table.getValueAt(i, 6);// ����
					String slStr = (String) table.getValueAt(i, 7);// �������
					Double dj = Double.valueOf(djStr);// ��String�͵ĵ���ת��ΪDouble��
					Integer sl = Integer.valueOf(slStr);// ��String�͵Ŀ������ת��ΪInteger��
					TbXsthDetail detail = new TbXsthDetail();// �����˻���ϸ��Ϣ
					detail.setSpid(spinfo.getId());// �����˻����
					detail.setTbXsthMain(xsthMain.getXsthId());// �����˻�����
					detail.setDj(dj);// ����
					detail.setSl(sl);// ����
					set.add(detail);// �������˻���ϸ��Ϣ��ӵ������˻���ϸ��Ϣ�ļ���
				}
				boolean rs = Dao.insertXsthInfo(xsthMain);// ��������˻���Ϣ
				if (rs) {
					JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "�˻����");// ������ʾ��
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
		// ��Ӵ������������ɳ�ʼ��
		addInternalFrameListener(new initTasks());
	}

	/**
	 * ��ʼ��"������"�����б�ķ���
	 * 
	 * @return "������"�����б�
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

	// ��ʼ�����ģ��
	private void initTable() {
		String[] columnNames = { "��Ʒ����", "��Ʒ���", "��Ӧ��", "����", "��λ", "���", "����", "����", "��װ", "����", "��׼�ĺ�" };// ��ͷ
		((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);// �������ģ�Ͷ�����������ӱ�ͷ
		TableColumn column = table.getColumnModel().getColumn(0);// �Ա��ģ��������Ϊ0����������
		final DefaultCellEditor editor = new DefaultCellEditor(sp);// ������Ԫ��༭��
		editor.setClickCountToStart(2);// ��ʼ�༭��Ԫ������ĵ�������Ϊ2��
		column.setCellEditor(editor);// �༭�������е�Ԫ��ʱ���õı༭��
	}

	// ��ʼ����Ʒ�����б�
	private void initSpBox() {
		List list = new ArrayList();// ������Ʒ��Ϣ�ļ���
		ResultSet set = Dao.query("select * from tb_spinfo where id in (select id from tb_kucun)");// ����п�����Ʒ��Ϣ�Ľ����
		sp.removeAllItems();// �Ƴ�����Ʒ�������б��е�ѡ��
		sp.addItem(new TbSpinfo());// ����Ʒ�������б��������Ʒ��Ϣ
		for (int i = 0; table != null && i < table.getRowCount(); i++) {
			TbSpinfo tmpInfo = (TbSpinfo) table.getValueAt(i, 0);// �����Ʒ��Ϣ
			if (tmpInfo != null && tmpInfo.getId() != null)// �����Ʒ��Ϣ����Ʒ��Ŷ���Ϊ��
				list.add(tmpInfo.getId());// ����Ʒ��Ϣ�ļ����������Ʒ���
		}
		try {
			while (set.next()) {// �ƶ���ļ�¼ָ��ָ��һ����Ч�ļ�¼
				TbSpinfo spinfo = new TbSpinfo();// ��Ʒ��Ϣ
				spinfo.setId(set.getString("id").trim());// ��Ʒ���
				// ���������Ѵ���ͬ����Ʒ����Ʒ�������оͲ��ٰ�������Ʒ
				if (list.contains(spinfo.getId()))
					continue;
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
				sp.addItem(spinfo);// ����Ʒ�������б��������Ʒ��Ϣ
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	// �ͻ�ѡ��ʱ������ϵ���ֶ�
	private void doKhSelectAction() {
		Item item = (Item) kehu.getSelectedItem();// ��ñ�ѡ�еĿͻ�
		TbKhinfo khInfo = Dao.getKhInfo(item);// ��ȡָ���ͻ���Ϣ
		lian.setText(khInfo.getLian());// ���á���ϵ�ˡ��ı����е��ı�����
	}

	// ����Ʒ����������Ʒ�������ϼƽ��
	private final class computeInfo implements ContainerListener {
		public void componentRemoved(ContainerEvent e) {
			// �������
			clearEmptyRow();
			int rows = table.getRowCount();// ��ñ��ģ���е�����
			int count = 0;// ����Ʒ������
			double money = 0.0;// ���ϼƽ�
			TbSpinfo column = null;// ��Ʒ��Ϣ��ʵ��
			if (rows > 0)
				column = (TbSpinfo) table.getValueAt(rows - 1, 0);// Ϊ����Ʒ��Ϣ����ʵ����ֵ
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

	// ����ĳ�ʼ������
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();// �����˻�ʱ���߳�
			initKehuField();// ��ʼ���ͻ��ֶ�
			initPiaoHao();// ��ʼ��������Ʊ�š�
			initSpBox();// ��ʼ����Ʒ�����б�
		}

		private void initKehuField() {// ��ʼ���ͻ��ֶ�
			List keHuInfo = Dao.getKhInfos();// ������пͻ���Ϣ�ļ���
			for (Iterator iter = keHuInfo.iterator(); iter.hasNext();) {// �������пͻ���Ϣ�ļ���
				List list = (List) iter.next();// ��õ�������һ������
				Item item = new Item();// ���ݱ�����
				item.setId(list.get(0).toString().trim());// �������
				item.setName(list.get(1).toString().trim());// ������Ϣ
				kehu.addItem(item);// �򡰿ͻ��������б������ѡ��
			}
			doKhSelectAction();
		}
		// �����˻�ʱ���߳�
		private void initTimeField() {
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
			}).start();
		}
	}
	// ��ʼ������Ʊ��
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());// ʹ��ϵͳʱ��ֵ����һ�����ڶ���
		String maxId = Dao.getXsthMainMaxId(date);// ��ȡ���������˻����
		piaoHao.setText(maxId);// ���á�����Ʊ�š��ı����е��ı�����
	}

	// ������Ʒ�������ѡ�񣬸��±��ǰ�е�����
	private synchronized void updateTable() {
		TbSpinfo spinfo = (TbSpinfo) sp.getSelectedItem();// ��á���Ʒ�������б��б�ѡ�е�ѡ��
		Item item = new Item();// ���ݱ�����
		item.setId(spinfo.getId());// �������
		TbKucun kucun = Dao.getKucun(item);// ��ȡ�����Ʒ��Ϣ
		int row = table.getSelectedRow();// ��ñ��ģ���б�ѡ�е���
		if (row >= 0 && spinfo != null) {
			table.setValueAt(spinfo.getId(), row, 1);// ��Ʒ���
			table.setValueAt(spinfo.getGysname(), row, 2);// ��Ӧ������
			table.setValueAt(spinfo.getCd(), row, 3);// ����
			table.setValueAt(spinfo.getDw(), row, 4);// ��Ʒ������λ
			table.setValueAt(spinfo.getGg(), row, 5);// ��Ʒ���
			table.setValueAt(kucun.getDj() + "", row, 6);// ����
			table.setValueAt(kucun.getKcsl() + "", row, 7);// �������
			table.setValueAt(spinfo.getBz(), row, 8);// ��װ
			table.setValueAt(spinfo.getPh(), row, 9);// ����
			table.setValueAt(spinfo.getPzwh(), row, 10);// ��׼�ĺ�
			table.editCellAt(row, 7);// ��������ɱ༭
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

	// ֹͣ���Ԫ�ı༭
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();// �������Ԫ�༭��
		if (cellEditor != null)// ���Ԫ�༭������
			cellEditor.stopCellEditing();// ֹͣ���Ԫ�ı༭
	}
}
