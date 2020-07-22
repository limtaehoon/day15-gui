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

public class CheckBoxTest2 extends JFrame{

	
	private JTextArea ta;

	
	public CheckBoxTest2() {
		setTitle("CheckBoxTest2");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel p1 = new JPanel();
		ta = new JTextArea();
		JCheckBox cb1 = new JCheckBox("�ٳ�������");
		JCheckBox cb2 = new JCheckBox("������ ����");
		//��ũ�ѹ� ����
		JScrollPane jsp = new JScrollPane(ta);
		
		p1.add(cb1); p1.add(cb2);
		itemEventH2 ih = new itemEventH2();//�ް����� ta�� �־� �� 
		//�����ڰ� ��� �ŰԺ����� �ִ� itemEventH Ŭ�������� ������ ����� ���� ������
		
		cb1.addItemListener(ih);
		cb2.addItemListener(ih);
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,jsp);//ta���� jsp�־��ָ� ��ũ�ѹٰ� ����
		
		setSize(400,500);
		setVisible(true);//ȭ�鿡 ���̱�
		
		
	
	}
	
	public static void main(String[] args) {
		new CheckBoxTest2();
	
	}
	class itemEventH2 implements ItemListener{
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			//�
		
		JCheckBox cb =(JCheckBox)e.getSource();
		
		if(cb.isSelected()) {
				ta.append(cb.getText()+"����\n");
		}else {
			ta.append(cb.getText()+"��������\n");
		}
	}
	}
}