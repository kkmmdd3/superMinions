import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Professor {
	private static final int PERSON_INSERT = 1;
	private static final int PERSON_DELETE = 2;
	private static final int PERSON_CHANGE = 3;
	private static final int NAME_SEARCH = 4;
	private static final int ALL_PRINT = 5;
	private static final int LOAD_FILE = 6;
	private static final int SAVE_FILE = 7;
	private static final int PASSWORD_CHANGE = 8;	
	private static final int EXIT = 9;

	static void printProfessorMainMenu() {
		System.out.println("===========������ �޴�=========");
		System.out.println("       [1] �л� �߰�                   ");
		System.out.println("       [2] �л� ����                   ");
		System.out.println("       [3] �л� ����                   ");
		System.out.println("       [4] �̸� �˻�                   ");
		System.out.println("       [5] ��ü ���                   ");
		System.out.println("       [6] �ܺ� ���� �б�             ");
		System.out.println("       [7] �ٸ� �̸����� ���� ����   ");
		System.out.println("       [8] password ����         ");
		System.out.println("       [9] ���α׷� ����              ");
		System.out.println("============================");
		System.out.println("studentInfo.txt ������ ������ �ִ��� ���� Ȯ�����ּ���");
		System.out.print(" �޴�����[��ȣ]: ");
	}

	static int selectProfessorMainMenu() {
		ProcessorForStudentInfo process = new ProcessorForStudentInfo();
		printProfessorMainMenu();
		int menu = process.inputNumber();
		
		if(menu < PERSON_INSERT || menu > EXIT)	
			System.out.println("�ùٸ��� ���� �޴������Դϴ�.");
		return menu;
	}

	public static void processProfessorMainMenu() throws IOException {
		Hashtable<Integer, Person> studentList = new Hashtable<Integer, Person>();
		boolean managerloop = true;
		boolean passloop = true;
		String filename = "studentInfo.txt";
		DatahandlerInFile datahandler = new DatahandlerInFile();
		ProcessorForStudentInfo process = new ProcessorForStudentInfo();
		
		datahandler.readFromFile(studentList, filename);
		BufferedReader inputpassword = new BufferedReader(new InputStreamReader(System.in));
		
		while(passloop) {
			try {
				BufferedReader passwordinfile = new BufferedReader(new FileReader("password.txt"));
				String professorpassword = inputpassword.readLine();
				String passwordinfiletostring = passwordinfile.readLine();
			
				if(professorpassword.equals(passwordinfiletostring)) {
					while(managerloop) {
						int menu = selectProfessorMainMenu();
						runMenuCase(menu, process, studentList);
						if(menu == EXIT){
							managerloop = false;
							passloop = false;
						}
					}
				}
				else {
					System.out.println("**�н����尡 ��ġ���� �ʽ��ϴ�.");
					passloop = false;
				}
				passwordinfile.close();
			}
			catch(Exception e) {
				passloop = false;
			}
		}
		datahandler.saveFile(studentList, filename);
	}
	private static void runMenuCase(int menu, ProcessorForStudentInfo process, Hashtable<Integer, Person> studentList) throws IOException {
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
				process.printAllStudent(studentList);
				break;
			case LOAD_FILE:
				process.loadUserFile(studentList);
				process.printAllStudent(studentList);
				break;
			case SAVE_FILE:
				process.saveUserFileOtherName(studentList);
				break;
			case PASSWORD_CHANGE:
				Password.changePassword();
				break;
			case EXIT:
				System.out.println("������ �޴��� �����մϴ�:)");
				break;
		}
	}
}

