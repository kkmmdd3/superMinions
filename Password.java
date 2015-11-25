import java.io.*;
import java.util.*;

class Password {
	static void changePassword() {
		BufferedReader newpassword = new BufferedReader(new InputStreamReader(System.in));
		int password = 0, passwordCheck = 0;
		boolean bool = true;
		
		while(bool) {
			try{
				System.out.print("\n새로운 패스워드 :(8자리 숫자 이내) \n>");
				password=Integer.parseInt(newpassword.readLine());
			}
			catch(IOException e) {
				System.out.println("정수를 입력하세요");
			}
			if(password >= 100000000) {
				System.out.println("\n※※1~8자리 패스워드를 입력하세요※※");
				bool=true;
			}
			else if(password < 100000000) { 
				try {
					System.out.print("패스워드 확인 : \n>");
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
						System.out.println("파일로 출력할 수 없습니다");
					}
					bool = false;
				}
				else {
					System.out.println("※※패스워드가 틀렸습니다.※※");
					bool = true;
				}
			}
			else {
				System.out.println("\n※※1~8자리 정수로 된 패스워드를 입력하세요※※");
				bool = true;
			}
		}
	}
}
