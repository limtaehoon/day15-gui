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
		JButton b1=new JButton("버튼1");
		JButton b2=new JButton("버튼2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tf1=new TextField(20);	
		JTextArea ta=new JTextArea(5,20);//여러개 쓰는거	
		TextArea tta=new TextArea(5,20);//여러개에 스크롤을 가짐
		setLayout(new FlowLayout());
			add(b1);
			add(b2);
			add(tf1);
			add(ta);
			add(tta);
			b1.addActionListener(this);
			b2.addActionListener(this);
			TextField tf1=new TextField(20);
			//크기 300 400
				setSize(300,400);
				//화면에 보이기 
				setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
	
	String str=e.getActionCommand();
	if(str.equals("버튼1")) {
		System.out.println("버튼1생성");
		getContentPane().setBackground(Color.blue);
		System.out.println(tf1.getText());
		
	}else if(str.equals("버튼2")) {
		System.out.println("버튼2생성");//39라인이랑 43~44라인 같은말 
		Container con=getContentPane();//이걸 위로올리고 con.~~로 써도됨
		con.setBackground(Color.CYAN);
		tf1.setText("");//get 가져오기 set ()내용 생성
	}
	
}
		
public static void main(String[] args) {
		
		new JPannelTest();
		
	}

	
}


