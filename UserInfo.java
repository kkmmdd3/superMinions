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
				  addUserInfo();
				  break;
			  case '2':
				  updateUserInfo();
				  break;
			  case '3':
				  deleteUserInfo();
				  break;
			  case '4':
				  watchUserInfo();
				  break;
			  case '5':
				  endUserInfo();
				  break;
			  default:
				  System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				  System.out.println("");
				  break;
			}
		}
	}
	
	void addUserInfo() throws IOException{
		v=userData();  //���� �������� �żҵ�
		HashMap test = new HashMap();
		System.out.print("�й�: ");
		Id = br.readLine();
		for(int i=0; i<v.size(); i++){
			test = (HashMap)v.get(i);
			String idtest = (String)test.get("Id");
			if(Id.equals(test))
			{
				System.out.println("�̹� ��ϵǾ� �ִ� �й��Դϴ�.");
				System.out.println("�ٽ� �Է����ּ���");
				return;
			}
		}
		
		System.out.print("�̸�: ");
		name = br.readLine();
		System.out.print("����: ");
		department = br.readLine();
		System.out.print("��ȭ��ȣ('-'���� �Է�): ");
		Phone_number = br.readLine();
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		if(Id.equals("") || name.equals("") || department.equals(""))
		{
			System.out.println("������ �׸��� �ս��ϴ�.");
			System.out.println("�ٽ� �Է����ּ���");
			return;
		}
		else{
			fw = new FileWriter("UserInfo.txt", true);
			bw = new BufferedWriter(fw);
			bw.write(Id);
			bw.write(",");
			bw.write(name);
			bw.write(",");
			bw.write(department);
			bw.write(",");
			bw.write(Phone_number);
			bw.newLine();
			bw.flush();
			bw.close();
		}
	}
	
	void updateUserInfo() throws IOException
	{
		v=userData();
		HashMap test = new HashMap();
		boolean idCheck = false;
		System.out.println("������ �й��� �����ϼ���");
		Id = br.readLine();
		/*���̵� ����*/
		for(int i=0; i<v.size(); i++){
			test = (HashMap)v.get(i);
			String idtest = (String)test.get("Id");
			if(Id.equals(idtest))
			{
				idCheck = true;
				v.removeElementAt(i);
			}
		}
		if(!idCheck){
			System.out.println("�ش� �й��� �������� �ʽ��ϴ�.");
			updateUserInfo();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		HashMap modifyFile = new HashMap();
		fw = new FileWriter("UserInfo.txt", false);  //true: �̾�� false: �����
		bw = new BufferedWriter(fw);
		for(int i=0; i<v.size(); i++)
		{
			modifyFile = (HashMap)v.get(i);
			String Id = (String)modifyFile.get("Id");
			String name = (String)modifyFile.get("name");
			String department = (String)modifyFile.get("department");
			String Phone_number = (String)modifyFile.get("Phone_number");
			bw.write(Id);
			bw.write(",");
			bw.write(name);
			bw.write(",");
			bw.write(department);
			bw.write(",");
			bw.write(Phone_number);
			bw.newLine();
			bw.flush();
		}
		/* id ���� ���Է�=�����۾� ����*/
		System.out.println("������ �й��� �Է��ϼ���");
		Id = br.readLine();
		System.out.println("������ �̸��� �Է��ϻ���");
		name = br.readLine();
		System.out.println("������ ������ �Է��ϻ���");
		department = br.readLine();
		System.out.println("������ ��ȭ��ȣ�� �Է��ϻ���");
		Phone_number = br.readLine();
		
		if(Id.equals("") || name.equals("") || department.equals("") || Phone_number.equals("")  )
		{
			System.out.println("������ �׸��� �ֽ��ϴ�");
			System.out.println("�ٽ� �Է����ּ���");
			addUserInfo();
		}
		else{
			fw = new FileWriter("UserInfo.txt", true);
			bw = new BufferedWriter(fw);
			bw.write(Id);
			bw.write(",");
			bw.write(name);
			bw.write(",");
			bw.write(department);
			bw.write(",");
			bw.write(Phone_number);
			bw.newLine();
			bw.flush();
		}
		v.clear();
	}
	
	void deleteUserInfo() throws IOException{
		v=userData();
		HashMap test = new HashMap();
		boolean idCheck = false;
		System.out.print("������ �й��� �Է��ϼ���: ");
		Id = br.readLine();
		for(int i=0; i<v.size(); i++){
			test = (HashMap)v.get(i);
			String idtest = (String)test.get("Id");
			if(Id.equals(idtest))
			{
				System.out.println("�й� Ȯ��: "+ test.get("Id"));
				System.out.println("�� ��: "+ test.get("name"));
				System.out.println("�� ��: "+ test.get("department"));
				System.out.println("��ȭ��ȣ: "+ test.get("Phone_number"));
				idCheck=true;
				v.removeElementAt(i);
			}
		}
		if(!idCheck){
			System.out.println("�ش��ϴ� �й��� �����ϴ�");
			deleteUserInfo();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		HashMap modifyFile = new HashMap();
		fw = new FileWriter("UserInfo.txt", false);
		bw = new BufferedWriter(fw);
		for(int i=0; i<v.size(); i++)
		{
			modifyFile = (HashMap)v.get(i);
			String Id = (String)modifyFile.get("Id");
			String name = (String)modifyFile.get("name");
			String department = (String)modifyFile.get("department");
			String Phone_number = (String)modifyFile.get("Phone_number");
			bw.write(Id);
			bw.write(",");
			bw.write(name);
			bw.write(",");
			bw.write(department);
			bw.write(",");
			bw.write(Phone_number);
			bw.newLine();
			bw.flush();
		}
		v.clear();
	}
	
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
			System.out.println("�й�: "+ idtest + " �� ��: "+ nametest
					+ " �� ��: "+departtest +" ��ȭ��ȣ: "+ Phonetest);
		}
		if(v.isEmpty())
		{
			System.out.println("�ڷᰡ �������� �ʽ��ϴ�");
		}
		System.out.println("size: "+v.size());
		v.clear();
	}
	
	void endUserInfo() throws IOException{
		System.out.println("���α׷��� �����մϴ�");
		System.exit(0);
	}
	
	Vector userData() throws IOException{
		FileReader fr = null;
		BufferedReader br = null;
		Vector v= new Vector();
		fr = new FileReader("UserInfo.txt");
		br = new BufferedReader(fr);
		String message;
		while((message = br.readLine())!=null)
		{
			HashMap map = new HashMap();
			tok = new StringTokenizer(message, ",");
			while(tok.hasMoreTokens()){
				Id = tok.nextToken();
				name = tok.nextToken();
				department = tok.nextToken();
				Phone_number = tok.nextToken();
				map.put("Id", Id);
				map.put("name", name);
				map.put("department", department);
				map.put("Phone_number", Phone_number);
				
			}
			v.addElement(map);
		}
		return v;
	}
	
	public static void main(String[] args) throws IOException
	{
		new UserInfo("<<<<<�л����� ���α׷�>>>>>");
	}
}


