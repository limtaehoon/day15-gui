package guiTest;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPannelTest2 extends JFrame implements ActionListener {
	JPanel p1,p2;
	JTextArea ta;
public JPannelTest2() {
	super("패널테스트");
	setLayout(new FlowLayout());
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 p1= new JPanel();//패널1
	 p2= new JPanel();//패널2
	 ta=new JTextArea(10,20);
	 JButton b1=new JButton("페널2보이기");
	JButton b2=new JButton("페널2안보이기");
	JButton b3=new JButton("페널1보이기");
	JButton b4=new JButton("페널1안보이기");
	setLayout(new FlowLayout());
	p1.add(b1);	p1.add(b2);
	p2.add(b3);	p2.add(b4);
	add(p1);
	add(p2);
	add(ta);
	p1.setBackground(Color.yellow);
	p2.setBackground(Color.GREEN);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	setSize(300,400);
	setVisible(true);
}
	public static void main(String[] args) {
		new JPannelTest2();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		ta.append(str+"\n");
		if(str.equals("페널2보이기")){
			p2.setVisible(true);
			
	}else if(str.equals("페널2안보이기")){
		p2.setVisible(false);
		
	}else if(str.equals("페널1보이기")){
		p1.setVisible(true);
	}else {
		p1.setVisible(false);	
		}
	}
}			
				
				
				
				
				
				

