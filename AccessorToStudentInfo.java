import java.io.*;

public class AccessorToStudentInfo {

	public void decideAccessor() throws IOException {
		BufferedReader inputId = new BufferedReader(new InputStreamReader(System.in));
		boolean mainloop = true; //이 값이 false가되면 메인 종료됨
		String professorId = "professor";
		String guestId = "guest";
		
		while(mainloop) {
			System.out.println("종료하려면 ID 입력란에 종료를 입력하세요.");
			System.out.println("로그인 ID 입력: ");
			String accessorId = inputId.readLine();
			
			if(accessorId.equals(professorId)) {
				System.out.println("패스워드를 입력(초기 패스워드:12345678): ");
				Professor.processProfessorMainMenu(); //관리자모드 클래스
			}
			else if(accessorId.equals(guestId)) {
				Guest.processGuestMenu();
			}
			else if(accessorId.equals("종료")) {
				System.out.println("종료됨."); 
				mainloop = false;
			}
		}	
	}	

	public static void main(String[] args) throws IOException {
		try{   //이 try문은 초기 패스워드값을 설정하는데, 만약 패스워드가 설정되어있다면 그 내용을 유지 시킨다.
			File file = new File("password.txt"); //password.txt 파일을 만듭니다.
			FileWriter fileWriter = new FileWriter(file,true); //파일이 기존에 있었다면 내용을 유지 시킨다.
			FileReader isIn = new FileReader("password.txt"); //password.txt를 읽어온다.
			int c = isIn.read();  //password.txt 파일안에 내용이 있다면 read()메소드가 -1이상의 값을 c에 반환할 것이다.
			if(c == -1) {  //만약 -1 값을 반환 하였더라면 아래의 내용을 실행한다.
				int initialpassword = 12345678;
				String password = Integer.toBinaryString(initialpassword);
				fileWriter.write(password);  //파일에 데이터를 넣는다(기본패스워드 역할)
				fileWriter.close();
			}
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
		AccessorToStudentInfo accessor = new AccessorToStudentInfo();
		accessor.decideAccessor();
	}
}

