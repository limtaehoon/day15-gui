package guiTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gugudan extends JFrame implements ActionListener{
	JTextField dan;
	JTextArea ta;
	public Gugudan() {
		setTitle("������");
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new FlowLayout());
	JButton btn=new JButton("������");
	dan=new JTextField(10);
	ta=new JTextArea(10,20);
	btn.addActionListener(this);
	dan.addActionListener(this);
	setLayout(new FlowLayout());
	add(dan);
	add(btn);
	add(ta);
	
	setSize(300,400);
	setVisible(true);
	}

	public static void main(String[] args) {
		new Gugudan();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ta.setText("");
		try {
		int d=Integer.parseInt(dan.getText());//Integer ���� �����ͼ� for�� ����
		
		for(int i=0;i<10;i++) {
			ta.append(d+"*"+i+"="+d*i+"\n");
		}	
	}catch (NumberFormatException n) {
		dan.setText("���ڸ� �Է� �ϼ���");
	}
			
			
		
	}

}
