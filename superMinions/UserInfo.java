import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class UserInfo {
	String Id;
	String name;
	String Phone_number;
	String department;
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Vector v;
	StringTokenizer tok;
	UserInfo(String title) throws IOException{
		System.out.println(title);
		mainScreen();
	}
	
	void mainScreen() throws IOException{
		for(;;)
		{
			System.out.println("*********************************");
			System.out.println(" [1]등록 [2]수정 [3]삭제 [4]조회 [5]종료 ");
			System.out.println("*********************************");
			System.out.print("선택[숫자]: ");
			String str=br.readLine();
			if(str.equals(""))
			{
				System.out.println("입력값을 넣어주세요.");
				mainScreen();
			}
			char c= str.charAt(0);
			switch(c){
			  case '1':
				  //addUserInfo();
				  break;
			  case '2':
				  //updateUserInfo();
				  break;
			  case '3':
				  //deleteUserInfo();
				  break;
			  case '4':
				  //watchUserInfo();
				  break;
			  case '5':
				  //endUserInfo();
				  break;
			  default:
				  System.out.println("잘못 입력하셨습니다.");
				  System.out.println("");
				  break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		new UserInfo("<<<<<학생관리 프로그램>>>>>");
	}
}