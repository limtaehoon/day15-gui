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
		JCheckBox cb1 = new JCheckBox("바나나선택");
		JCheckBox cb2 = new JCheckBox("오렌지 선택");
		//스크롤바 생성
		JScrollPane jsp = new JScrollPane(ta);
		
		p1.add(cb1); p1.add(cb2);
		itemEventH2 ih = new itemEventH2();//메개변수 ta를 넣어 줌 
		//생성자가 없어서 매게변수가 있는 itemEventH 클래스에서 생성자 만들면 오류 없어짐
		
		cb1.addItemListener(ih);
		cb2.addItemListener(ih);
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,jsp);//ta말고 jsp넣어주면 스크롤바가 생김
		
		setSize(400,500);
		setVisible(true);//화면에 보이기
		
		
	
	}
	
	public static void main(String[] args) {
		new CheckBoxTest2();
	
	}
	class itemEventH2 implements ItemListener{
		
		
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
}