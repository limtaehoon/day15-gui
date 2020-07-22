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
	
	setLayout(new GridLayout(2,2));//2��2����  ����
	add(new panelPane());//1��1������ �г��� ���� //�г��� �Լ� �ؿ��� ����
	
	JTextArea ta = new JTextArea();//TextArea�� ta�� ���� (����)
	JScrollPane jsp = new JScrollPane(ta);//��ũ�� �����ϰ� jsp�� ���� ta�� �ٴ� ��ũ��
	add(jsp);//1��2����ġ�� ��ũ�ѻ��� 
	
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
				JOptionPane.showMessageDialog(null,"��ȣ�� �Է�");
			}catch (NumberFormatException n2) {
				JOptionPane.showMessageDialog(null,"��ȣ�� �Է�");
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	
	JPanel p1 = new JPanel();//2��1����ġ
	JButton insertBtn =new JButton("�߰�");//��ư�����
	JButton viewBtn =new JButton("����");
	JButton updateBtn =new JButton("����");
	JButton deleteBtn =new JButton("����");
	
	p1.add(insertBtn);p1.add(viewBtn);//����
	p1.add(updateBtn);p1.add(deleteBtn);
	add(p1);//2��1��
	
	//�߰� 
	insertBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "insert into player values(plater_seq.nextval,?,?,?,?,?)";
			//sql�� �ۼ�: 5���� ������ ������  
			try {
				PreparedStatement ps 
							= con.prepareStatement(sql);//sql ����
				
				ps.setString(1,tf[1].getText()); //���� ArryList �迭 1���� 1��? ����
				ps.setString(2,tf[2].getText());//�迭 2���� 2��? ����
				ps.setDouble(3, Double.parseDouble(tf[3].getText()));//
				ps.setDouble(4, Double.parseDouble(tf[4].getText()));
				ps.setString(5,tf[5].getText());
				ps.executeUpdate();//�ۼ��ɶ����� �����Ǳ� 
				clearText();//������ Text clean
				viewBtn.doClick();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	});
	//���� 
	viewBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");//���鵵 ǥ��
			String sql ="select* from player order by num desc";
			//sql�� :player ���̺� ������ ������ ��ȣ������ 
			try {//
				Statement st = con.createStatement();//Statement ���� 
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					ta.append("��ȣ:"+rs.getInt("num")+"\n");
					ta.append("�̸�:"+rs.getString("name")+"\n");
					ta.append("����:"+rs.getString("birth")+"\n");
					ta.append("Ű:"+rs.getDouble("heigth")+"\n");
					ta.append("������:"+rs.getDouble("weight")+"\n");
					ta.append("����:"+rs.getString("kind")+"\n");
					ta.append("\n");
					
				}
		}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	//����
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
	//����
	deleteBtn.addActionListener(new ActionListener() {
		
		@Override
	
		public void actionPerformed(ActionEvent e) {
			int result= JOptionPane.showConfirmDialog(null,"���������ұ��?",
					"confirm",JOptionPane.YES_NO_OPTION);
			if(JOptionPane.YES_OPTION==result) {//����
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
	
	JPanel p2 = new JPanel();//2��2����ġ
	JComboBox<String> jcb = new JComboBox<String>();
	jcb.addItem("�̸�");
	jcb.addItem("����");
	JTextField searchtf = new JTextField(10);
	JButton searchBtn = new JButton("�˻�");
	p2.add(jcb);p2.add(searchtf);p2.add(searchBtn);
	add(p2);//2��2��
	
	//�˻���ư
	searchBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			
			
		jcb.getSelectedIndex();//index:��ġ item:����
		int idx = jcb.getSelectedIndex();
		String key = "";
		if(idx==0) {
			key = "name";
		}else if(idx==1) {
			key="kind";
		}
		String sql = "select * from player where "+key+" like '%"+searchtf.getText()+"%'";
		//key+" like '%" ��� ���� ����� ���ϸ� �ѹ������� �ν��ؼ� ������
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ta.append("��ȣ:"+rs.getInt("num")+"\n");
				ta.append("�̸�:"+rs.getString("name")+"\n");
				ta.append("����:"+rs.getString("birth")+"\n");
				ta.append("Ű:"+rs.getDouble("heigth")+"\n");
				ta.append("������:"+rs.getDouble("weight")+"\n");
				ta.append("����:"+rs.getString("kind")+"\n");
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
	private String[] text= {"��ȣ","�̸�","����","Ű","������","����"};
	public panelPane() {
		setLayout(null);	
		for(int i=0;i<text.length;i++) {
			JLabel la =new JLabel(text[i]);
			la.setHorizontalAlignment(JLabel.RIGHT);//����
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
//��񿬰�
private void dbCon() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");//mysql �����
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//localhost:������ ��� ���� ����Ŭ ����Ѵ� 
		//1521:���� ��Ʈ
		//xe:����Ŭ ����
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
