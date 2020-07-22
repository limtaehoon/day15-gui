package guiTest;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.rmi.server.LoaderHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Bank extends JFrame {
	HashMap<String, Integer> hm = new HashMap<String,Integer>();
	List lst;//불러올 list를 지역변수로 설정해두면 안됨 전역변수로 설정하기
public Bank() {
	setTitle("Bank");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new GridLayout(1,2));//1행 1열
	JPanel left = new JPanel();//왼쪽거
	left.setLayout(new GridLayout(5,1));//5행1열
	
	JPanel p1 = new JPanel();//왼쪽화면 첫번째줄
	p1.add(new JLabel("이름"));
	JTextField tfName = new JTextField(15);
	p1.add(tfName);
	
	JButton btn = new JButton("계좌 생성");//왼쪽화면 두번째줄
	
	JPanel p2 =new JPanel();
	p2.add(new JLabel("잔액"));//왼쪽화면 세번째줄
	JTextField tjBalance = new JTextField(15);
	tjBalance.setEditable(false);//수정 안되게
	p2.add(tjBalance);
	
	JPanel p3 = new JPanel();//왼쪽화면 네번째줄
	JTextField tfMoney = new JTextField(15);
	p3.add(tfMoney);
	p3.add(new JLabel("원"));
	
	JPanel p4 = new JPanel();//왼쪽화면 다섯째줄
	JButton inputBtn =new JButton("예금");
	JButton outputBtn =new JButton("출금");
	JButton fileputBtn =new JButton("저장");
	p4.add(inputBtn);p4.add(outputBtn);p4.add(fileputBtn);
	
	
	left.add(p1);left.add(btn);left.add(p2);left.add(p3);left.add(p4);
	//오른쪽 화면 붙이기
	 lst = new List();
	//왼쪽,오른쪽 화면 붙이기
	add(left); add(lst); 
	//계좌 생성버튼
	btn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// tfName의 내용을 가져와서 lst에 추가한다 단,공백은 저장안함
			if(tfName.getText().isEmpty())return;
			lst.add(tfName.getText().trim());
			hm.put(tfName.getText(),0);//맵에 저장 계좌 생성 0원
			tfName.setText("");
		}
	});
	//리스트 
	lst.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// 리스트에서 선택한 이름을 tfName에 넣고 잔액은 tfBalance에 넣기
			String str = lst.getSelectedItem().trim();
			tfName.setText(str);
			tjBalance.setText(hm.get(str)+"");
		}
	});		
	//예금버튼 클릭
	inputBtn.addActionListener(new ActionListener() {
		/*list 에서 선택한 계좌 잔액에 
		 * tfMoney 만큼 더해서 
		 * 잔액(tjBalance)을 고쳐주고 맵에 저장
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				//String key = lst.getSelectedItem().trim(); 둘중하나 사용가능   list형
				String key = tfName.getText();
				int balance = hm.get(key);//기존잔액
				int value = balance+Integer.parseInt(tfMoney.getText());//수정잔액
				tjBalance.setText(value+"");//화면에서 잔액수정
				hm.put(key,value);//hm내용 수정
				tfMoney.setText("");//편의를 위해 에금액 지우기
			
			
				}catch(NullPointerException N) {
						new MessegeBox("오류", "계좌 선택하세요");
				//예금자를 몰라서 생기는 오류시  메세지 박스 띄움
						//(예외처리 NullPointerException )
				}catch(NumberFormatException e2) {
					new MessegeBox("입력오류", "숫자를 입력하세요");
				}//예금란에 숫자가 아닌 문자가 들어와서 생기는 오류시 박스띄움
					   //(예외 처리 NumberFormatException)
		}
	});
	//출금버튼
	outputBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			String key = lst.getSelectedItem();
			int balance = hm.get(key);
			int value = balance-Integer.parseInt(tfMoney.getText());//수정잔액
			if(value<0) {
				new MessegeBox("잔액부족!!",key+"님 잔액이 부족");//MessegeBox 호출
			return;
		   }
			tjBalance.setText(value+"");
		
			hm.put(key,value);
			tfMoney.setText("");//편의를 위해 에금액 지우기
					
		
		}catch(NullPointerException N) {
			new MessegeBox("오류", "계좌 선택하세요");
		
		}catch(NumberFormatException e2) {
			new MessegeBox("입력오류", "숫자를 입력하세요");
		}		
		}
	
	});
	fileputBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//hm의 내용 >file
			File dir = new File("src\\guiTest");
			File file = new File(dir, "myBank.txt");
			try {
				FileWriter fw = new FileWriter(file);
				Set<String> set = hm.keySet();//key의 집합
				Iterator<String> it = set.iterator();
				
				while(it.hasNext()) {
					String key = it.next();//하나의 key 구하기 
					fw.write(key+" ");//이름내보내기 
					fw.write(hm.get(key)+"\n");//잔액구하기
				}
				fw.close();
			}catch (IOException e1) {
				new MessegeBox("오류", "파일을  선택하세요");
					e1.printStackTrace();
			}catch(NumberFormatException e2) {
				new MessegeBox("입력오류", "숫자를 입력하세요");
					e2.printStackTrace();
			}
		
		}
		});
	
				setSize(500,300);
				setVisible(true);
				load();
		}
			
		private void load() {
			hm.clear();
			File dir = new File("src\\guiTest");
			File file = new File(dir,"myBank.txt");
			try {
				if(!file.exists()) {
					file.createNewFile();
					
				}
				Scanner sc = new Scanner(file);//파일읽어오기
				while(sc.hasNext()) {
					String name = sc.next().trim();//이름 
					int money = sc.nextInt();//잔액
					hm.put(name,money);//맵에 저장 
					lst.add(name+"\n");//리스트에 이름 수정
				}
				sc.close();
			}catch (Exception e) {
				e.printStackTrace();//오류난 부분 띄우기 
			}

		}	

	public static void main(String[] args) {
		new Bank();
	}
}

	
		


