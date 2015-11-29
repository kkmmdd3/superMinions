import java.io.*;
import java.util.*;

class Password {
	public static String getPassword(){
		String password = "";
		String passwordCheck = "";
		BufferedReader newpassword = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			try{
				System.out.print("\n새로운 패스워드 : \n>");
				password = newpassword.readLine();
				System.out.print("패스워드 확인 : \n>");
				passwordCheck = newpassword.readLine();
			}
			catch(IOException e) {
				System.out.println("다시 패스워드를 입력하세요(오류)");
			}
			
			if(password.equals(passwordCheck)) {
				break;
			}
			else
				System.out.println("※※패스워드가 일치하지 않습니다.※※");
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
			System.out.println("파일로 출력할 수 없습니다");
		}
	}
	
	static void changePassword() {
		String password = "";
		
		password = getPassword();
		savePassword(password);
	}
}