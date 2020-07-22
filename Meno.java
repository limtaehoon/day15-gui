package guiTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Meno extends JFrame {
	JTextArea ta;
	File f;
	public Meno (String title) {
	setTitle(title);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JMenu mfile = new JMenu("�޴�");
	
	JMenuItem mOpen = new JMenuItem("����");
	JMenuItem mNew = new JMenuItem("���ο���");
	JMenuItem mSave = new JMenuItem("����");
	JMenuItem mSaveAs = new JMenuItem("�ٸ��̸� ����");
	JMenuItem mExit = new JMenuItem("������");

	mfile.add(mOpen);
	mfile.add(mNew);
	mfile.add(mSave);
	mfile.add(mSaveAs);
	mfile.add(mExit);
	
	JMenuBar mb = new JMenuBar();
	mb.add(mfile);
	setJMenuBar(mb);
	
	 ta = new JTextArea();
	//JScrollPane jsp = new JScrollPane(ta);
	JScrollPane jsp = new JScrollPane();
	jsp.setViewportView(ta);
	
	add(jsp);
	//���ο���//��ҹ�ư�� �о���� �ȵ�
	mNew.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			setTitle("�������");
		}
	});
	//����
	mOpen.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc =new JFileChooser();
			//fc.showOpenDialog(Meno.this);
			if(fc.showOpenDialog(Meno.this)
					==JFileChooser.CANCEL_OPTION)
				return;//��ҹ����� �ƴϸ� ���� ��� ��ư ����
			f = fc.getSelectedFile();
			setTitle(f.getName());
			fileREAD(f);
	
		}
	});
	//����
	mSave.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(getTitle().equals("�������")) {
//			JFileChooser fc = new JFileChooser();
//			if(fc.showSaveDialog(Meno.this)
//			==JFileChooser.CANCEL_OPTION)
//				return;
//			f = fc.getSelectedFile();
//			fileSave(f);
//			setTitle(f.getName());
			mSaveAs.doClick();//���� ������ �ٸ��̸������ ���� ������==doclick���� ��� 
			}else {//������������
				fileSave(f);
			}
	}});
	//���̸� ����
	mSaveAs.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			if(fc.showSaveDialog(Meno.this)
			==JFileChooser.CANCEL_OPTION)
				return;
			f = fc.getSelectedFile();
			//System.out.println("��������"+f);
			fileSave(f);
			setTitle(f.getName());
			
		}
	});
	//������
	mExit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	});
	
	setSize(500,400);
	setVisible(true);
	

}
	public static void main(String[] args) {
		
		new Meno("�������");
	}
//���� �б� �޼ҵ�
	private void fileREAD(File f) {
		FileReader fr;
		try {//���� �о���ʵ� ������ ����ó��
			fr = new FileReader(f);
			//���ڸ� ó�����ִ� Ŭ������ �ѹ��� �Ѹ�
		StringWriter sw = new StringWriter();
		while(true) {
			int ch = fr.read();
			if(ch==-1)break;
			sw.write(ch);
		}
		ta.setText(sw.toString());
		sw.close();
		fr.close();
		} catch (FileNotFoundException e) {//���� ���� ����
			
			e.printStackTrace();
		}catch (IOException e) {//���� ���� ����
				e.printStackTrace();
		}
		
		
	}
//���� ���� �޼ҵ� 

		private void fileSave(File f) {
			try {
			PrintStream ps = new PrintStream(f);
			ps.print(ta.getText());
			ps.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
