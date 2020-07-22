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
	List lst;//�ҷ��� list�� ���������� �����صθ� �ȵ� ���������� �����ϱ�
public Bank() {
	setTitle("Bank");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new GridLayout(1,2));//1�� 1��
	JPanel left = new JPanel();//���ʰ�
	left.setLayout(new GridLayout(5,1));//5��1��
	
	JPanel p1 = new JPanel();//����ȭ�� ù��°��
	p1.add(new JLabel("�̸�"));
	JTextField tfName = new JTextField(15);
	p1.add(tfName);
	
	JButton btn = new JButton("���� ����");//����ȭ�� �ι�°��
	
	JPanel p2 =new JPanel();
	p2.add(new JLabel("�ܾ�"));//����ȭ�� ����°��
	JTextField tjBalance = new JTextField(15);
	tjBalance.setEditable(false);//���� �ȵǰ�
	p2.add(tjBalance);
	
	JPanel p3 = new JPanel();//����ȭ�� �׹�°��
	JTextField tfMoney = new JTextField(15);
	p3.add(tfMoney);
	p3.add(new JLabel("��"));
	
	JPanel p4 = new JPanel();//����ȭ�� �ټ�°��
	JButton inputBtn =new JButton("����");
	JButton outputBtn =new JButton("���");
	JButton fileputBtn =new JButton("����");
	p4.add(inputBtn);p4.add(outputBtn);p4.add(fileputBtn);
	
	
	left.add(p1);left.add(btn);left.add(p2);left.add(p3);left.add(p4);
	//������ ȭ�� ���̱�
	 lst = new List();
	//����,������ ȭ�� ���̱�
	add(left); add(lst); 
	//���� ������ư
	btn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// tfName�� ������ �����ͼ� lst�� �߰��Ѵ� ��,������ �������
			if(tfName.getText().isEmpty())return;
			lst.add(tfName.getText().trim());
			hm.put(tfName.getText(),0);//�ʿ� ���� ���� ���� 0��
			tfName.setText("");
		}
	});
	//����Ʈ 
	lst.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// ����Ʈ���� ������ �̸��� tfName�� �ְ� �ܾ��� tfBalance�� �ֱ�
			String str = lst.getSelectedItem().trim();
			tfName.setText(str);
			tjBalance.setText(hm.get(str)+"");
		}
	});		
	//���ݹ�ư Ŭ��
	inputBtn.addActionListener(new ActionListener() {
		/*list ���� ������ ���� �ܾ׿� 
		 * tfMoney ��ŭ ���ؼ� 
		 * �ܾ�(tjBalance)�� �����ְ� �ʿ� ����
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				//String key = lst.getSelectedItem().trim(); �����ϳ� ��밡��   list��
				String key = tfName.getText();
				int balance = hm.get(key);//�����ܾ�
				int value = balance+Integer.parseInt(tfMoney.getText());//�����ܾ�
				tjBalance.setText(value+"");//ȭ�鿡�� �ܾ׼���
				hm.put(key,value);//hm���� ����
				tfMoney.setText("");//���Ǹ� ���� ���ݾ� �����
			
			
				}catch(NullPointerException N) {
						new MessegeBox("����", "���� �����ϼ���");
				//�����ڸ� ���� ����� ������  �޼��� �ڽ� ���
						//(����ó�� NullPointerException )
				}catch(NumberFormatException e2) {
					new MessegeBox("�Է¿���", "���ڸ� �Է��ϼ���");
				}//���ݶ��� ���ڰ� �ƴ� ���ڰ� ���ͼ� ����� ������ �ڽ����
					   //(���� ó�� NumberFormatException)
		}
	});
	//��ݹ�ư
	outputBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			String key = lst.getSelectedItem();
			int balance = hm.get(key);
			int value = balance-Integer.parseInt(tfMoney.getText());//�����ܾ�
			if(value<0) {
				new MessegeBox("�ܾ׺���!!",key+"�� �ܾ��� ����");//MessegeBox ȣ��
			return;
		   }
			tjBalance.setText(value+"");
		
			hm.put(key,value);
			tfMoney.setText("");//���Ǹ� ���� ���ݾ� �����
					
		
		}catch(NullPointerException N) {
			new MessegeBox("����", "���� �����ϼ���");
		
		}catch(NumberFormatException e2) {
			new MessegeBox("�Է¿���", "���ڸ� �Է��ϼ���");
		}		
		}
	
	});
	fileputBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//hm�� ���� >file
			File dir = new File("src\\guiTest");
			File file = new File(dir, "myBank.txt");
			try {
				FileWriter fw = new FileWriter(file);
				Set<String> set = hm.keySet();//key�� ����
				Iterator<String> it = set.iterator();
				
				while(it.hasNext()) {
					String key = it.next();//�ϳ��� key ���ϱ� 
					fw.write(key+" ");//�̸��������� 
					fw.write(hm.get(key)+"\n");//�ܾױ��ϱ�
				}
				fw.close();
			}catch (IOException e1) {
				new MessegeBox("����", "������  �����ϼ���");
					e1.printStackTrace();
			}catch(NumberFormatException e2) {
				new MessegeBox("�Է¿���", "���ڸ� �Է��ϼ���");
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
				Scanner sc = new Scanner(file);//�����о����
				while(sc.hasNext()) {
					String name = sc.next().trim();//�̸� 
					int money = sc.nextInt();//�ܾ�
					hm.put(name,money);//�ʿ� ���� 
					lst.add(name+"\n");//����Ʈ�� �̸� ����
				}
				sc.close();
			}catch (Exception e) {
				e.printStackTrace();//������ �κ� ���� 
			}

		}	

	public static void main(String[] args) {
		new Bank();
	}
}

	
		


