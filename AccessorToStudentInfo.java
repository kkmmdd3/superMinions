import java.io.*;

public class AccessorToStudentInfo {

	public void decideAccessor() throws IOException {
		BufferedReader inputId = new BufferedReader(new InputStreamReader(System.in));
		boolean mainloop = true; 
		String professorId = "professor";
		String guestId = "guest";
		
		while(mainloop) {
			System.out.println("종료하려면 ID 입력란에 종료를 입력하세요.");
			System.out.println("로그인 ID 입력: ");
			String accessorId = inputId.readLine();
			
			if(accessorId.equals(professorId)) {
				System.out.println("패스워드 입력(초기 패스워드:12345678): ");
				Professor.processProfessorMainMenu();
			}
			else if(accessorId.equals(guestId)) {
				Guest.processGuestMenu();
			}
			else if(accessorId.equals("종료")) {
				System.out.println("전체 프로그램을 종료합니다."); 
				mainloop = false;
			}
			else {
				System.out.println("등록된 ID가 아닙니다.");
				System.out.println("로그인 화면으로 돌아갑니다.");
			}
		}	
	}	

	private static boolean isPasswordNotSet(int passwordSetState) {
		if(passwordSetState == -1)
			return true;
		else
			return false;
	} 
	
	public static void initializePasswordBeforeBegin(){
		try{
			File file = new File("password.txt");
			FileWriter fileWriter = new FileWriter(file,true);
			FileReader passwordfromfile = new FileReader("password.txt");
			int passwordSetState = passwordfromfile.read();
			
			if(isPasswordNotSet(passwordSetState)) {
				String initialpassword = "12345678";
				fileWriter.write(initialpassword);
				fileWriter.close();
			}
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws IOException {
		AccessorToStudentInfo accessor = new AccessorToStudentInfo();
		accessor.initializePasswordBeforeBegin();
		accessor.decideAccessor();
	}
}

