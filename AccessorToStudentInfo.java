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
				System.out.println("�н����带 �Է�(�ʱ� �н�����:12345678): ");
				Professor.processProfessorMainMenu();
			}
			else if(accessorId.equals(guestId)) {
				Guest.processGuestMenu();
			}
			else if(accessorId.equals("����")) {
				System.out.println("�����."); 
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

