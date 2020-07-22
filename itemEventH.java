package guiTest;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
//쓰는 이유 1:클래스 안에 클래스가 있어서 눈에 잘띔
//		 2:부모의 컴포먼트를 쓰기때문에 간결

public class itemEventH implements ItemListener{
	JTextArea ta;
	//매게변수가 있는 생성자
	public itemEventH(JTextArea ta) {
	this.ta=ta;
}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//어떤
	
	JCheckBox cb =(JCheckBox)e.getSource();
	
	if(cb.isSelected()) {
			ta.append(cb.getText()+"선택\n");
	}else {
		ta.append(cb.getText()+"선택해제\n");
	}
}
}