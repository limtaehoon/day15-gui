package guiTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CheckBoxTest extends JFrame implements ItemListener{

	
	private JTextArea ta;
	private JCheckBox cb1;
	private JCheckBox cb2; 
	
	public CheckBoxTest() {
		setTitle("CheckBoxTest");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel p1 = new JPanel();
		ta = new JTextArea();
		
		cb1 = new JCheckBox("�ٳ�������");
		cb2 = new JCheckBox("������ ����");
		//��ũ�ѹ� ����
		JScrollPane jsp = new JScrollPane(ta);
		
		p1.add(cb1); p1.add(cb2);
		
		cb1.addItemListener(this); //this�� addItemListener�޼ҵ� ���� ���
		//���� Ŭ���� �ȿ� this�� ��ü �ڽ��� ��Ī��
		cb2.addItemListener(this);
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,jsp);
		
		setSize(400,500);
		setVisible(true);//ȭ�鿡 ���̱�
		
		
	
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		//�üũ�ڽ��� ���õǾ����� ����
		//���õ� üũ�ڽ��� üũ�� üũ���� �ƴ��� ����
	
	JCheckBox cb =(JCheckBox)e.getSource();
	
	if(cb.isSelected()) {
			ta.append(cb.getText()+"����\n");
			
	}else {
		ta.append(cb.getText()+"��������\n");
	}
	
	
//	public void itemStateChanged(ItemEvent e) {
//		//�üũ�ڽ��� ���õǾ����� ����
//		//���õ� üũ�ڽ��� üũ�� üũ���� �ƴ��� ����
//		JCheckBox cb =(JCheckBox)e.getSource();
//		if(cb==cb1) {
//			if(cb.isSelected()) {
//				ta.append("�ٳ�������\n");
//				
//		}else {
//			ta.append("�ٳ��� ��������/��");
//		}
//		}if(cb==cb2) {
//			if(cb.isSelected()) {
//				ta.append("����������\n");
//				
//		}else {
//			ta.append("������ ��������/��");
//		
//		
//		}

		}
	
	public static void main(String[] args) {
		new CheckBoxTest();
	}

}