	package guiTest;
	
	import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
	
	public class underbarTest_inner extends Frame implements ActionListener{
	TextField tf1;
		public underbarTest_inner() {
	//super("GUI Test");
	setTitle("GUI Test");
	Button b1=new Button("��ư1");//ū��ȭ���� ��ư�� ���� ��ȭ���� ��� ��ȭ��:�����̳�->��ġ������(ex)������),��ư:������Ʈ
	Button b2=new Button("��ư2");//�����ӿ� �����̳� �ø���
	Button b3=new Button("��ư3");//3����
	tf1=new TextField(20);
	setLayout(new FlowLayout());
	add(b1);	add(b2);	add(b3);
	add(tf1);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);//3����
		TextField tf1 =new TextField(20);
	
		setSize(300,400);//ũ�� �Ҵ�
		setVisible(true);//�����ֱ�
	}
	@Override
	public void actionPerformed(ActionEvent e) {//ActionEvent e �ް�����
	String str =e.getActionCommand();
	if(str.equals("��ư1")) {
		System.out.println("��ư1 �̺�Ʈ �߻�");
		setBackground(Color.YELLOW);
		System.out.println(tf1.getText());
	}
	else if(str.equals("��ư2")) {
		
		System.out.println("��ư2 �̺�Ʈ �߻�");//addActionListener�� actionPerformed�� ������ �־�� �ȴ�
		setBackground(Color.BLUE);
	}
	else {
		System.out.println("��ư3 �̺�Ʈ �߻�");
		setBackground(Color.pink);
	}	
	}
	public static void main(String[] args) {
			new underbarTest_inner();
			
		}
	}
