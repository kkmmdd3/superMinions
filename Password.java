import java.io.*;
import java.util.*;

class Password {
	static void changePassword() {
		BufferedReader newpassword = new BufferedReader(new InputStreamReader(System.in));
		int password = 0, passwordCheck = 0;
		boolean bool = true;
		
		while(bool) {
			try{
				System.out.print("\n���ο� �н����� :(8�ڸ� ���� �̳�) \n>");
				password=Integer.parseInt(newpassword.readLine());
			}
			catch(IOException e) {
				System.out.println("������ �Է��ϼ���");
			}
			if(password >= 100000000) {
				System.out.println("\n�ء�1~8�ڸ� �н����带 �Է��ϼ���ء�");
				bool=true;
			}
			else if(password < 100000000) { 
				try {
					System.out.print("�н����� Ȯ�� : \n>");
					passwordCheck=Integer.parseInt(newpassword.readLine());
				}
				catch(IOException e) {
					System.out.println(e);
				}
				if(password == passwordCheck) {
					try {	
						String convertbinarynumber=Integer.toBinaryString(passwordCheck);
						FileWriter out = new FileWriter("password.txt");

						out.write(convertbinarynumber);
						out.close();
					}
					catch(IOException ioe) {
						System.out.println("���Ϸ� ����� �� �����ϴ�");
					}
					bool = false;
				}
				else {
					System.out.println("�ء��н����尡 Ʋ�Ƚ��ϴ�.�ء�");
					bool = true;
				}
			}
			else {
				System.out.println("\n�ء�1~8�ڸ� ������ �� �н����带 �Է��ϼ���ء�");
				bool = true;
			}
		}
	}
}
