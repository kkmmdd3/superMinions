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
			if(password >= 100000000) {  //8�ڸ� �̳��� �н����带 �Է¹ޱ� ���� if��
				System.out.println("\n�ء�1~8�ڸ� �н����带 �Է��ϼ���ء�");
				bool=true;
			}
			else if(password < 100000000) { //8�ڸ� �̳��� �Է½� �Ʒ��� ���α׷��� ����
				try {
					System.out.print("�н����� Ȯ�� : \n>");
					passwordCheck=Integer.parseInt(newpassword.readLine());  //�н����带 ��Ȯ�� �Է��ϱ� ���� Ȯ���۾�
				}
				catch(IOException e) {
					System.out.println(e);
				}
				if(password == passwordCheck) { //ó�� �Է��� �н������ �ι��� �Է��� �н����带 ���Ͽ� ������ ����.
					try {	
						String convertbinarynumber=Integer.toBinaryString(passwordCheck); //�Է��� �н����带 2���� ���ڷ� ��ȯ
						FileWriter out = new FileWriter("password.txt"); //password.txt������ ���� ���� ����
						out.write(convertbinarynumber); // 2������ ��ȯ�� ���ڸ� ���Ͽ� ����.
						out.close();
					}
					catch(IOException ioe) {
						System.out.println("���Ϸ� ����� �� �����ϴ�");
					}
					bool = false;
				}
				else {  // ó���Է��� �н������ �ι�° �Է��� �н����尡 �ٸ���.
					System.out.println("�ء��н����尡 Ʋ�Ƚ��ϴ�.�ء�");
					bool = true;
				}
			}
			else {
				System.out.println("\n�ء�1~8�ڸ� ������ �� �н����带 �Է��ϼ���ء�"); //8�ڸ� �̳��� �Է����� ������,
				bool = true;
			}
		}
	}
}
