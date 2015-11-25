import java.io.*;

public class AccessorToStudentInfo {

	public void decideAccessor() throws IOException {
		BufferedReader inputId = new BufferedReader(new InputStreamReader(System.in));
		boolean mainloop = true; //�� ���� false���Ǹ� ���� �����
		String professorId = "professor";
		String guestId = "guest";
		
		while(mainloop) {
			System.out.println("�����Ϸ��� ID �Է¶��� ���Ḧ �Է��ϼ���.");
			System.out.println("�α��� ID �Է�: ");
			String accessorId = inputId.readLine();
			
			if(accessorId.equals(professorId)) {
				System.out.println("�н����带 �Է�(�ʱ� �н�����:12345678): ");
				Professor.processProfessorMainMenu(); //�����ڸ�� Ŭ����
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
		try{   //�� try���� �ʱ� �н����尪�� �����ϴµ�, ���� �н����尡 �����Ǿ��ִٸ� �� ������ ���� ��Ų��.
			File file = new File("password.txt"); //password.txt ������ ����ϴ�.
			FileWriter fileWriter = new FileWriter(file,true); //������ ������ �־��ٸ� ������ ���� ��Ų��.
			FileReader isIn = new FileReader("password.txt"); //password.txt�� �о�´�.
			int c = isIn.read();  //password.txt ���Ͼȿ� ������ �ִٸ� read()�޼ҵ尡 -1�̻��� ���� c�� ��ȯ�� ���̴�.
			if(c == -1) {  //���� -1 ���� ��ȯ �Ͽ������ �Ʒ��� ������ �����Ѵ�.
				int initialpassword = 12345678;
				String password = Integer.toBinaryString(initialpassword);
				fileWriter.write(password);  //���Ͽ� �����͸� �ִ´�(�⺻�н����� ����)
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

