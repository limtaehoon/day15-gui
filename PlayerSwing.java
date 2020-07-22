package guiTest;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;





public class PlayerSwing extends JFrame{
	JTextField[] tf = new JTextField[6];
	Connection con;
	
	public PlayerSwing() {
	dbCon();
	setTitle("PlayerSwing");
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	setLayout(new GridLayout(2,2));//2행2열로  설정
	add(new panelPane());//1행1열생성 패널팬 생성 //패널팬 함수 밑에서 만듬
	
	JTextArea ta = new JTextArea();//TextArea를 ta로 지명 (전역)
	JScrollPane jsp = new JScrollPane(ta);//스크롤 생성하고 jsp로 지명 ta에 붙는 스크롤
	add(jsp);//1행2열위치에 스크롤생성 
	
	ta.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			try {
				int num = Integer.parseInt(ta.getSelectedText().trim());
				tf[0].setText(num+"");
				String sql = "select * from player where num="+num;
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
					tf[1].setText(rs.getString("name"));
					tf[2].setText(rs.getString("birth"));
					tf[3].setText(rs.getDouble("heigth")+"");
					tf[4].setText(rs.getDouble("weight")+"");
					tf[5].setText(rs.getString("kind"));
				}
				
				
				
			}catch(NullPointerException n) {
				JOptionPane.showMessageDialog(null,"번호를 입력");
			}catch (NumberFormatException n2) {
				JOptionPane.showMessageDialog(null,"번호를 입력");
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	
	JPanel p1 = new JPanel();//2행1열위치
	JButton insertBtn =new JButton("추가");//버튼만들기
	JButton viewBtn =new JButton("보기");
	JButton updateBtn =new JButton("수정");
	JButton deleteBtn =new JButton("삭제");
	
	p1.add(insertBtn);p1.add(viewBtn);//연결
	p1.add(updateBtn);p1.add(deleteBtn);
	add(p1);//2행1열
	
	//추가 
	insertBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "insert into player values(plater_seq.nextval,?,?,?,?,?)";
			//sql문 작성: 5개의 시퀀스 문자행  
			try {
				PreparedStatement ps 
							= con.prepareStatement(sql);//sql 세팅
				
				ps.setString(1,tf[1].getText()); //위에 ArryList 배열 1번에 1번? 대입
				ps.setString(2,tf[2].getText());//배열 2번에 2번? 대입
				ps.setDouble(3, Double.parseDouble(tf[3].getText()));//
				ps.setDouble(4, Double.parseDouble(tf[4].getText()));
				ps.setString(5,tf[5].getText());
				ps.executeUpdate();//작성될때마다 수정되기 
				clearText();//끝나면 Text clean
				viewBtn.doClick();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	});
	//보기 
	viewBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");//공백도 표함
			String sql ="select* from player order by num desc";
			//sql문 :player 테이블에 모든것을 보여줌 번호순으로 
			try {//
				Statement st = con.createStatement();//Statement 생성 
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					ta.append("번호:"+rs.getInt("num")+"\n");
					ta.append("이름:"+rs.getString("name")+"\n");
					ta.append("생일:"+rs.getString("birth")+"\n");
					ta.append("키:"+rs.getDouble("heigth")+"\n");
					ta.append("몸무게:"+rs.getDouble("weight")+"\n");
					ta.append("종목:"+rs.getString("kind")+"\n");
					ta.append("\n");
					
				}
		}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	//수정
	updateBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "update player set name =?,birth=?,"
					+"heigth=?,weight=?,kind=? where num=?";
			try {
				PreparedStatement ps
				=con.prepareStatement(sql);
				ps.setString(1,tf[1].getText());
				ps.setString(2,tf[2].getText());
				ps.setDouble(3,Double.parseDouble(tf[3].getText()));
				ps.setDouble(4,Double.parseDouble(tf[4].getText()));
				ps.setString(5,tf[5].getText());
				ps.setInt(6,Integer.parseInt(tf[0].getText()));
				ps.executeUpdate();
				clearText();
				viewBtn.doClick();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	});
	//삭제
	deleteBtn.addActionListener(new ActionListener() {
		
		@Override
	
		public void actionPerformed(ActionEvent e) {
			int result= JOptionPane.showConfirmDialog(null,"정말삭제할까요?",
					"confirm",JOptionPane.YES_NO_OPTION);
			if(JOptionPane.YES_OPTION==result) {//삭제
			int num = Integer.parseInt(tf[0].getText());
			try {
				String sql ="delete from player where num="+num;
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				clearText();
				viewBtn.doClick();
				
		}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
});
	
	JPanel p2 = new JPanel();//2행2열위치
	JComboBox<String> jcb = new JComboBox<String>();
	jcb.addItem("이름");
	jcb.addItem("종목");
	JTextField searchtf = new JTextField(10);
	JButton searchBtn = new JButton("검색");
	p2.add(jcb);p2.add(searchtf);p2.add(searchBtn);
	add(p2);//2행2열
	
	//검색버튼
	searchBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			
			
		jcb.getSelectedIndex();//index:위치 item:문자
		int idx = jcb.getSelectedIndex();
		String key = "";
		if(idx==0) {
			key = "name";
		}else if(idx==1) {
			key="kind";
		}
		String sql = "select * from player where "+key+" like '%"+searchtf.getText()+"%'";
		//key+" like '%" 띄어 쓰기 제대로 안하면 한문장으로 인식해서 오류남
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ta.append("번호:"+rs.getInt("num")+"\n");
				ta.append("이름:"+rs.getString("name")+"\n");
				ta.append("생일:"+rs.getString("birth")+"\n");
				ta.append("키:"+rs.getDouble("heigth")+"\n");
				ta.append("몸무게:"+rs.getDouble("weight")+"\n");
				ta.append("종목:"+rs.getString("kind")+"\n");
				ta.append("\n");
				
			}
	}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
});
	
	
	setSize(600,400);
	setVisible(true);


}
class panelPane extends JPanel{
	private String[] text= {"번호","이름","생일","키","몸무게","종목"};
	public panelPane() {
		setLayout(null);	
		for(int i=0;i<text.length;i++) {
			JLabel la =new JLabel(text[i]);
			la.setHorizontalAlignment(JLabel.RIGHT);//정렬
			la.setSize(50,20);
			la.setLocation(30,50+i*20);
			add(la);
			tf[i] = new JTextField(50);
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setSize(150,20);
			tf[i].setLocation(150,50+i*20);
			add(tf[i]);
		}
		tf[0].setEditable(false);
	}
	
  }
//디비연결
private void dbCon() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");//mysql 쓰면됨
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//localhost:나에게 깔려 있은 오라클 사용한다 
		//1521:연결 포트
		//xe:오라클 버전
		String user = "scott";
		String pwd = "1234";
		con = DriverManager.getConnection(url, user,pwd);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
	}
private void clearText() {
	for(int i=0;i<tf.length;i++) {
		tf[i].setText("");
	}
}
public static void main(String[] args) {
		new PlayerSwing();

	}
	
}
