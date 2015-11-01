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
			System.out.println(" [1]��� [2]���� [3]���� [4]��ȸ [5]���� ");
			System.out.println("*********************************");
			System.out.print("����[����]: ");
			String str=br.readLine();
			if(str.equals(""))
			{
				System.out.println("�Է°��� �־��ּ���.");
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
				  System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				  System.out.println("");
				  break;
			}
		}
	}

	//���� ��� [��ȸ]
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
			System.out.println("�й�: "+ idtest + "�� ��: "+ nametest
					+ "�� ��: "+departtest +"��ȭ��ȣ: "+ Phone_number);
		}
		if(v.isEmpty())
		{
			System.out.println("�ڷᰡ �������� �ʽ��ϴ�");
		}
		System.out.println("size: "+v.size());
		v.clear();
	}
	
	public static void main(String[] args) throws IOException
	{
		new UserInfo("<<<<<�л����� ���α׷�>>>>>");
	}
}