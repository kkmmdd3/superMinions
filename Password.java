import java.io.*;
import java.util.*;

class Password {
	public static String getPassword(){
		String password = "";
		String passwordCheck = "";
		BufferedReader newpassword = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			try{
				System.out.print("\n���ο� �н����� : \n>");
				password = newpassword.readLine();
				System.out.print("�н����� Ȯ�� : \n>");
				passwordCheck = newpassword.readLine();
			}
			catch(IOException e) {
				System.out.println("�ٽ� �н����带 �Է��ϼ���(����)");
			}
			
			if(password.equals(passwordCheck)) {
				break;
			}
			else
				System.out.println("�ء��н����尡 ��ġ���� �ʽ��ϴ�.�ء�");
		}
		return password;
	}
	
	private static void savePassword(String password){
		try {	
			FileWriter out = new FileWriter("password.txt");
			out.write(password);
			out.close();
		}
		catch(IOException ioe) {
			System.out.println("���Ϸ� ����� �� �����ϴ�");
		}
	}
	
	static void changePassword() {
		String password = "";
		
		password = getPassword();
		savePassword(password);
	}
}