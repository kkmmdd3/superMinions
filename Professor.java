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
		System.out.println("===========관리자 메뉴=========");
		System.out.println("       [1] 학생 추가                   ");
		System.out.println("       [2] 학생 삭제                   ");
		System.out.println("       [3] 학생 변경                   ");
		System.out.println("       [4] 이름 검색                   ");
		System.out.println("       [5] 전체 출력                   ");
		System.out.println("       [6] 외부 파일 읽기             ");
		System.out.println("       [7] 다른 이름으로 파일 저장   ");
		System.out.println("       [8] password 변경         ");
		System.out.println("       [9] 프로그램 종료              ");
		System.out.println("============================");
		System.out.println("studentInfo.txt 파일이 폴더에 있는지 먼저 확인해주세요");
		System.out.print(" 메뉴선택[번호]: ");
	}

	static int selectProfessorMainMenu() {
		ProcessorForStudentInfo process = new ProcessorForStudentInfo();
		printProfessorMainMenu();
		int menu = process.inputNumber();
		
		if(menu < PERSON_INSERT || menu > EXIT)	
			System.out.println("올바르지 않은 메뉴선택입니다.");
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
					System.out.println("**패스워드가 일치하지 않습니다.");
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
				System.out.println("관리자 메뉴를 종료합니다:)");
				break;
		}
	}
}

