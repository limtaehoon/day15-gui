	package guiTest;
	
	import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
	
	public class PannelTest extends Frame implements ActionListener{
	TextField tf1;
		public PannelTest() {
	//super("GUI Test");
	setTitle("GUI Test");
	Button b1=new Button("버튼1");//큰도화지에 버튼을 만든 도화지를 띄움 도화지:컨테이너->배치관리자(ex)프레임),버튼:컴포넌트
	Button b2=new Button("버튼2");//프레임에 컨테이너 올린것
	Button b3=new Button("버튼3");//3생성
	tf1=new TextField(20);
	setLayout(new FlowLayout());
	add(b1);	add(b2);	add(b3);
	add(tf1);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);//3연결
		TextField tf1 =new TextField(20);
	
		setSize(300,400);//크기 할당
		setVisible(true);//보여주기
	}
	@Override
	public void actionPerformed(ActionEvent e) {//ActionEvent e 메개변수
	String str =e.getActionCommand();
	if(str.equals("버튼1")) {
		System.out.println("버튼1 이벤트 발생");
		setBackground(Color.YELLOW);
		System.out.println(tf1.getText());
	}
	else if(str.equals("버튼2")) {
		
		System.out.println("버튼2 이벤트 발생");//addActionListener는 actionPerformed를 가지고 있어야 된다
		setBackground(Color.BLUE);
	}
	else {
		System.out.println("버튼3 이벤트 발생");
		setBackground(Color.pink);
	}	
	}
	public static void main(String[] args) {
			new PannelTest();
			
		}
	}
