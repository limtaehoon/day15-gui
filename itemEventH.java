package guiTest;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
//���� ���� 1:Ŭ���� �ȿ� Ŭ������ �־ ���� �߶�
//		 2:�θ��� ������Ʈ�� ���⶧���� ����

public class itemEventH implements ItemListener{
	JTextArea ta;
	//�ŰԺ����� �ִ� ������
	public itemEventH(JTextArea ta) {
	this.ta=ta;
}
	
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