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
	JMenu mfile = new JMenu("메뉴");
	
	JMenuItem mOpen = new JMenuItem("열기");
	JMenuItem mNew = new JMenuItem("새로열기");
	JMenuItem mSave = new JMenuItem("저장");
	JMenuItem mSaveAs = new JMenuItem("다른이름 저장");
	JMenuItem mExit = new JMenuItem("끝내기");

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
	//새로열기//취소버튼은 읽어오면 안됨
	mNew.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			setTitle("제목없음");
		}
	});
	//열기
	mOpen.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc =new JFileChooser();
			//fc.showOpenDialog(Meno.this);
			if(fc.showOpenDialog(Meno.this)
					==JFileChooser.CANCEL_OPTION)
				return;//취소버든이 아니면 수생 취소 버튼 리턴
			f = fc.getSelectedFile();
			setTitle(f.getName());
			fileREAD(f);
	
		}
	});
	//저장
	mSave.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(getTitle().equals("제목없음")) {
//			JFileChooser fc = new JFileChooser();
//			if(fc.showSaveDialog(Meno.this)
//			==JFileChooser.CANCEL_OPTION)
//				return;
//			f = fc.getSelectedFile();
//			fileSave(f);
//			setTitle(f.getName());
			mSaveAs.doClick();//같은 내용을 다른이름저장과 같은 형식임==doclick으로 사용 
			}else {//기존파일있음
				fileSave(f);
			}
	}});
	//새이름 저장
	mSaveAs.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			if(fc.showSaveDialog(Meno.this)
			==JFileChooser.CANCEL_OPTION)
				return;
			f = fc.getSelectedFile();
			//System.out.println("파일저장"+f);
			fileSave(f);
			setTitle(f.getName());
			
		}
	});
	//끝내기
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
		
		new Meno("제목없음");
	}
//파일 읽기 메소드
	private void fileREAD(File f) {
		FileReader fr;
		try {//파일 읽어오믐데 없으면 예외처리
			fr = new FileReader(f);
			//문자를 처리해주는 클래스에 한번에 뿌림
		StringWriter sw = new StringWriter();
		while(true) {
			int ch = fr.read();
			if(ch==-1)break;
			sw.write(ch);
		}
		ta.setText(sw.toString());
		sw.close();
		fr.close();
		} catch (FileNotFoundException e) {//파일 없음 오류
			
			e.printStackTrace();
		}catch (IOException e) {//파일 읽음 오류
				e.printStackTrace();
		}
		
		
	}
//파일 저장 메소드 

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
