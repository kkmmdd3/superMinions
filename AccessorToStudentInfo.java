import java.io.*;

public class AccessorToStudentInfo {

	public void decideAccessor() throws IOException {
		BufferedReader inputId = new BufferedReader(new InputStreamReader(System.in));
		boolean mainloop = true; 
		String professorId = "professor";
		String guestId = "guest";
		
		while(mainloop) {
			System.out.println("�����Ϸ��� ID �Է¶��� ���Ḧ �Է��ϼ���.");
			System.out.println("�α��� ID �Է�: ");
			String accessorId = inputId.readLine();
			
			if(accessorId.equals(professorId)) {
				System.out.println("�н����� �Է�(�ʱ� �н�����:12345678): ");
				Professor.processProfessorMainMenu();
			}
			else if(accessorId.equals(guestId)) {
				Guest.processGuestMenu();
			}
			else if(accessorId.equals("����")) {
				System.out.println("��ü ���α׷��� �����մϴ�."); 
				mainloop = false;
			}
			else {
				System.out.println("��ϵ� ID�� �ƴմϴ�.");
				System.out.println("�α��� ȭ������ ���ư��ϴ�.");
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

