package guiTest;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class JPannelTest extends JFrame implements ActionListener {
	TextField tf1;
	public JPannelTest() {
		setTitle("GUI Test");	
		JButton b1=new JButton("��ư1");
		JButton b2=new JButton("��ư2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tf1=new TextField(20);	
		JTextArea ta=new JTextArea(5,20);//������ ���°�	
		TextArea tta=new TextArea(5,20);//�������� ��ũ���� ����
		setLayout(new FlowLayout());
			add(b1);
			add(b2);
			add(tf1);
			add(ta);
			add(tta);
			b1.addActionListener(this);
			b2.addActionListener(this);
			TextField tf1=new TextField(20);
			//ũ�� 300 400
				setSize(300,400);
				//ȭ�鿡 ���̱� 
				setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
	
	String str=e.getActionCommand();
	if(str.equals("��ư1")) {
		System.out.println("��ư1����");
		getContentPane().setBackground(Color.blue);
		System.out.println(tf1.getText());
		
	}else if(str.equals("��ư2")) {
		System.out.println("��ư2����");//39�����̶� 43~44���� ������ 
		Container con=getContentPane();//�̰� ���οø��� con.~~�� �ᵵ��
		con.setBackground(Color.CYAN);
		tf1.setText("");//get �������� set ()���� ����
	}
	
}
		
public static void main(String[] args) {
		
		new JPannelTest();
		
	}

	
}


