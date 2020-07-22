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
		
		cb1 = new JCheckBox("바나나선택");
		cb2 = new JCheckBox("오렌지 선택");
		//스크롤바 생성
		JScrollPane jsp = new JScrollPane(ta);
		
		p1.add(cb1); p1.add(cb2);
		
		cb1.addItemListener(this); //this는 addItemListener메소드 가서 사용
		//같은 클래스 안에 this로 객체 자신을 지칭함
		cb2.addItemListener(this);
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,jsp);
		
		setSize(400,500);
		setVisible(true);//화면에 보이기
		
		
	
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		//어떤체크박스가 선택되엇는지 구분
		//선택된 체크박스이 체크가 체크인지 아닌지 구분
	
	JCheckBox cb =(JCheckBox)e.getSource();
	
	if(cb.isSelected()) {
			ta.append(cb.getText()+"선택\n");
			
	}else {
		ta.append(cb.getText()+"선택해제\n");
	}
	
	
//	public void itemStateChanged(ItemEvent e) {
//		//어떤체크박스가 선택되엇는지 구분
//		//선택된 체크박스이 체크가 체크인지 아닌지 구분
//		JCheckBox cb =(JCheckBox)e.getSource();
//		if(cb==cb1) {
//			if(cb.isSelected()) {
//				ta.append("바나나선택\n");
//				
//		}else {
//			ta.append("바나나 선택해제/ㅜ");
//		}
//		}if(cb==cb2) {
//			if(cb.isSelected()) {
//				ta.append("오렌지선택\n");
//				
//		}else {
//			ta.append("오렌지 선택해제/ㅜ");
//		
//		
//		}

		}
	
	public static void main(String[] args) {
		new CheckBoxTest();
	}

}