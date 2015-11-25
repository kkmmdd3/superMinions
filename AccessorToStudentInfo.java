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
				System.out.println("패스워드를 입력(초기 패스워드:12345678): ");
				Professor.processProfessorMainMenu();
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
		try{
			File file = new File("password.txt");
			FileWriter fileWriter = new FileWriter(file,true);
			FileReader isIn = new FileReader("password.txt");
			int c = isIn.read();

			if(c == -1) {
				int initialpassword = 12345678;
				String password = Integer.toBinaryString(initialpassword);
				fileWriter.write(password);
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

