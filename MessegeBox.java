package guiTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessegeBox  extends JFrame{
	public MessegeBox(String title, String msg) {
		
		setTitle(title);
	setLayout(new FlowLayout());
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//¹Ù·Î ´ÝÈû 
	JButton closeBtn= new JButton("´Ý±â");
	JLabel lbl =new JLabel(msg);
	add(lbl);
	add(closeBtn);
	closeBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
	});
	setSize(300,100);
	setVisible(true);
	}
}
