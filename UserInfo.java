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

	//보기 기능 [조회]
	void watchUserInfo() throws IOException{
		v = userData();
		HashMap test = new HashMap();
		for(int i=0; i<v.size(); i++)
		{
			test = (HashMap)v.get(i);
			String idtest = (String)test.get("Id");
			String nametest = (String)test.get("name");
			String departtest = (String)test.get("department");
			String Phonetest = (String)test.get("Phone_number");
			System.out.println("학번: "+ idtest + "이 름: "+ nametest
					+ "전 공: "+departtest +"전화번호: "+ Phone_number);
		}
		if(v.isEmpty())
		{
			System.out.println("자료가 존재하지 않습니다");
		}
		System.out.println("size: "+v.size());
		v.clear();
	}
	
	public static void main(String[] args) throws IOException
	{
		new UserInfo("<<<<<학생관리 프로그램>>>>>");
	}
}