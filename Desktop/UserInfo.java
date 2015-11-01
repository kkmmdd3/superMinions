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
			System.out.println("       ************************************       ");
			System.out.println("          [1] 학생 정보 등록            ");
			System.out.println("          [2] 학생 정보 수정             ");
			System.out.println("          [3] 학생 정보 삭제           ");
			System.out.println("          [4] 학생 정보 조회             ");
			System.out.println("          [5] 프로그램 종료            ");
			System.out.println("       ************************************       ");
			System.out.println("      등록전! UserInfo.txt 파일이 디렉토리에 있는지 확인해주세요");
			System.out.print("      선택[숫자]:");
			String str=br.readLine();
			if(str.equals(""))
			{
				System.out.println("입력값을 넣어주세요.");
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
				  System.out.println("잘못 입력하셨습니다.");
				  System.out.println("");
				  break;
			}
		}
	} 
	
	void addUserInfo() throws IOException{
		v=userData();  //파일 내용저장 매소드
		HashMap test = new HashMap();
		System.out.print("학번: ");
		Id = br.readLine();
		for(int i=0; i<v.size(); i++){
			test = (HashMap)v.get(i);
			String idmulticheck = (String)test.get("Id");
			if(Id.equals(idmulticheck))
			{
				System.out.println("이미 등록되어 있는 학번입니다.");
				System.out.println("다시 등록해주세요");
				return;
			}
		}
		
		System.out.print("이름:");
		name = br.readLine();
		System.out.print("전공:");
		department = br.readLine();
		System.out.print("전화번호:");
		Phone_number = br.readLine();
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		if(Id.equals("") || name.equals("") || department.equals(""))
		{
			System.out.println("누락된 항목이 잇습니다.");
			System.out.println("다시 입력해주세요");
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
		System.out.println("수정할 학번을 입력하세요");
		Id = br.readLine();
		/*아이디 삭제*/
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
			System.out.println("해당 학번이 존재하지 않습니다.");
			updateUserInfo();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		HashMap modifyFile = new HashMap();
		fw = new FileWriter("UserInfo.txt", false);  //true: 이어쓰기 false: 덮어쓰기
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
		/*id 부터 재입력=수정작업 시작*/
		System.out.println("학번/이름/전공/전화번호 각각 Enter키 후 입력해주세요");
		System.out.println("단, 전화번호 외의 다른 정보도 수정가능합니다:).");
		Id = br.readLine();
		name = br.readLine();
		department = br.readLine();
		Phone_number = br.readLine();
		
		if(Id.equals("") || name.equals("") || department.equals("") || Phone_number.equals(""))
		{
			System.out.println("누락된 항목이 있습니다");
			System.out.println("다시 입력해주세요");
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
			System.out.println("수정 완료!");
		}
		v.clear();
	}
	
	void deleteUserInfo() throws IOException{
		v=userData();
		HashMap test = new HashMap();
		boolean idCheck = false;
		System.out.println("삭제할 학번을 입력하세요");
		Id = br.readLine();
		for(int i=0; i<v.size(); i++){
			test = (HashMap)v.get(i);
			String idtest = (String)test.get("Id");
			if(Id.equals(idtest))
			{
				System.out.println("아래의 삭제 정보를 확인해주세요.");
				System.out.println("학번 확인: "+ test.get("Id"));
				System.out.println("이 름: "+ test.get("name"));
				System.out.println("전 공: "+ test.get("department"));
				System.out.println("전화번호: "+ test.get("Phone_number"));
				idCheck=true;
				v.removeElementAt(i);
				System.out.println("삭제 완료!");
			}
		}
		if(!idCheck){
			System.out.println("해당하는 학번이 없습니다");
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
			System.out.println("학번: "+ idtest+'\t'+"이 름: "+ nametest+'\t'
					+ "전 공: "+departtest +'\t'+"전화번호: "+ Phonetest);
		}
		if(v.isEmpty())
		{
			System.out.println("자료가 존재하지 않습니다");
		}
		v.clear();
	}
	
	void endUserInfo() throws IOException{
		System.out.println("프로그램을 종료합니다");
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
		new UserInfo("              <<<<<학생관리 프로그램>>>>>>           ");
	}
}   
       
