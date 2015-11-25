import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

class Person {
	private String name; 
	private int id;				
	private String phonenumber;	
	private String department;			 

	public Person() {

	}
	
	public void setName(String studentname) { 
		name = studentname; 
	}
	
	public String getName() { 
		return name;
	}
	
	public void setId(int studentid) {	
		id = studentid;
	}
	
	public int getId() { 
		return id;
	}
	
	public void setPhoneNumber(String studentphone) { 
		phonenumber = studentphone;
	}
	
	public String getPhoneNumber() {
		return phonenumber;
	}
	
	public void setDepartment(String studentdepartment) { 
		department = studentdepartment; 
	}
	
	public String getDepartment() {
		return department;
	}

	// �ּҷ� ������ ��� 
	public void printPerson() {
		System.out.printf("%-6d \t%s \t %15s\t%s \n", id, name, phonenumber, department);
	}
}

public class Professor {
	private static final int PERSON_INSERT = 1;		// ��� �߰� 
	private static final int PERSON_DELETE = 2;		// ��� ���� 
	private static final int PERSON_CHANGE = 3;		// ��� ���� 
	private static final int NAME_SEARCH = 4;		// �̸� �˻�
	private static final int ALL_PRINT = 5;			// ��ü ��� 
	private static final int LOAD_FILE = 6;			// �ܺ� ���� �ε� 
	private static final int SAVE_FILE = 7;			// �ܺ� ���� ���� 
	private static final int PASSWORD_CHANGE = 8;	
	private static final int EXIT = 9;				// ���� 

	static void printProfessorMainMenu() {
		System.out.println("===========������ �޴�=========");
		System.out.println("       [1] �л� �߰�                   ");
		System.out.println("       [2] �л� ����                   ");
		System.out.println("       [3] �л� ����                   ");
		System.out.println("       [4] �̸� �˻�                   ");
		System.out.println("       [5] ��ü ���                   ");
		System.out.println("       [6] �ܺ� ���� �б�             ");
		System.out.println("       [7] �ܺ� ���� ����             ");
		System.out.println("       [8] password ����         ");
		System.out.println("       [9] ���α׷� ����              ");
		System.out.println("============================");
		System.out.println("studentInfo.txt ������ ������ �ִ��� ���� Ȯ�����ּ���");
		System.out.print(" �޴�����[��ȣ]: ");
	}

	static int selectProfessorMainMenu() {
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		printProfessorMainMenu();

		int menu = process.inputNumber();
		if(menu < PERSON_INSERT || menu > EXIT)	
			System.out.println("�ùٸ��� ���� �޴������Դϴ�.");

		return menu;
	}

	public static void processProfessorMainMenu() throws IOException {
		Hashtable<Integer, Person> studentList = new Hashtable<Integer, Person>(); // �ּҷ� ���
		boolean managerloop = true;
		boolean passloop = true;
		DatahandlerForFile datahandler = new DatahandlerForFile();
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		String filename = "studentInfo.txt";
		datahandler.readFromFile(studentList, filename);
		BufferedReader inputpassword = new BufferedReader(new InputStreamReader(System.in));
		
		while(passloop) {
			try {
				BufferedReader passwordinfile = new BufferedReader(new FileReader("password.txt"));
				int professorpassword = Integer.parseInt(inputpassword.readLine());
				String passwordinfiletostring = passwordinfile.readLine();  //password ���ϳ����� String ������ �о�´�.
				int passwordintegerform = Integer.parseInt(passwordinfiletostring,2); //2���� �������� password�� int�� 10������ ��ȯ�Ͽ� eword2�� ����
				
				if(professorpassword == passwordintegerform) {
					while(managerloop) {
						while(true) { 
							int menu = selectProfessorMainMenu();
							if(menu == EXIT) {
								System.out.println("������ �޴��� �����մϴ�:)");
								break;
							}

							switch(menu) {
							case PERSON_INSERT:
								process.addStudentInfo(studentList);
								break;
							case PERSON_DELETE:
								process.deleteStudentInfo(studentList);
								break;
							case PERSON_CHANGE:
								process.updateStudentInfo(studentList);
								break;
							case NAME_SEARCH:
								process.searchNameInStudentInfo(studentList);
								break;
							case ALL_PRINT:
								// ��ü ���
								process.printAllStudent(studentList);
								break;
							case LOAD_FILE:
								// �ܺ� ���� �ҷ����� 
								process.loadUserFile(studentList);
								process.printAllStudent(studentList);
								break;
							case SAVE_FILE:
								// �ܺ� ���� ���� 
								process.saveUserFile(studentList);
								break;
							case PASSWORD_CHANGE:
								// �ܺ� ���� ���� 
								Password.changePassword();
								break;
							}
							managerloop = false;
						}
						passloop = false; //managerloop�� ����������, passloop�� �������� ����Ŭ������ �̵��Ѵ�.
					}
				}
				else {
					System.out.println("**�н����尡 ��ġ���� �ʽ��ϴ�.");
					passloop = false;
				}
				passwordinfile.close(); //���� ����
			}
			catch(Exception e) {
				passloop = false;
			}
		}
		// ���� ����
		datahandler.processForFileSave(studentList, filename);
	}
}
