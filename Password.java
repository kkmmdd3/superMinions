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
			if(password >= 100000000) {  //8자리 이내로 패스워드를 입력받기 위한 if문
				System.out.println("\n※※1~8자리 패스워드를 입력하세요※※");
				bool=true;
			}
			else if(password < 100000000) { //8자리 이내로 입력시 아래의 프로그래밍 실행
				try {
					System.out.print("패스워드 확인 : \n>");
					passwordCheck=Integer.parseInt(newpassword.readLine());  //패스워드를 정확히 입력하기 위한 확인작업
				}
				catch(IOException e) {
					System.out.println(e);
				}
				if(password == passwordCheck) { //처음 입력한 패스워드와 두번재 입력한 패스워드를 비교하여 같으면 실행.
					try {	
						String convertbinarynumber=Integer.toBinaryString(passwordCheck); //입력한 패스워드를 2진수 문자로 변환
						FileWriter out = new FileWriter("password.txt"); //password.txt파일을 새로 만들어서 연다
						out.write(convertbinarynumber); // 2진수로 변환한 문자를 파일에 쓴다.
						out.close();
					}
					catch(IOException ioe) {
						System.out.println("파일로 출력할 수 없습니다");
					}
					bool = false;
				}
				else {  // 처음입력한 패스워드와 두번째 입력한 패스워드가 다를때.
					System.out.println("※※패스워드가 틀렸습니다.※※");
					bool = true;
				}
			}
			else {
				System.out.println("\n※※1~8자리 정수로 된 패스워드를 입력하세요※※"); //8자리 이내로 입력하지 않을시,
				bool = true;
			}
		}
	}
}
