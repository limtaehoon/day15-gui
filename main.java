package guiTest;

import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("�� �Է�");
	try {
		int dan=sc.nextInt();
		
		for(int i=1;i<10;i++) {
			System.out.println(dan+"*"+i+"="+dan*i);
		
		}
	}catch (NumberFormatException  n) {
		System.out.println("�����Է�");
	}
	}
	}


