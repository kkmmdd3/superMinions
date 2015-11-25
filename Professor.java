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

	// 주소록 데이터 출력 
	public void printPerson() {
		System.out.printf("%-6d \t%s \t %15s\t%s \n", id, name, phonenumber, department);
	}
}

public class Professor {
	private static final int PERSON_INSERT = 1;		// 사람 추가 
	private static final int PERSON_DELETE = 2;		// 사람 삭제 
	private static final int PERSON_CHANGE = 3;		// 사람 변경 
	private static final int NAME_SEARCH = 4;		// 이름 검색
	private static final int ALL_PRINT = 5;			// 전체 출력 
	private static final int LOAD_FILE = 6;			// 외부 파일 로드 
	private static final int SAVE_FILE = 7;			// 외부 파일 저장 
	private static final int PASSWORD_CHANGE = 8;	
	private static final int EXIT = 9;				// 종료 

	static void printProfessorMainMenu() {
		System.out.println("===========관리자 메뉴=========");
		System.out.println("       [1] 학생 추가                   ");
		System.out.println("       [2] 학생 삭제                   ");
		System.out.println("       [3] 학생 변경                   ");
		System.out.println("       [4] 이름 검색                   ");
		System.out.println("       [5] 전체 출력                   ");
		System.out.println("       [6] 외부 파일 읽기             ");
		System.out.println("       [7] 외부 파일 저장             ");
		System.out.println("       [8] password 변경         ");
		System.out.println("       [9] 프로그램 종료              ");
		System.out.println("============================");
		System.out.println("studentInfo.txt 파일이 폴더에 있는지 먼저 확인해주세요");
		System.out.print(" 메뉴선택[번호]: ");
	}

	static int selectProfessorMainMenu() {
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		printProfessorMainMenu();

		int menu = process.inputNumber();
		if(menu < PERSON_INSERT || menu > EXIT)	
			System.out.println("올바르지 않은 메뉴선택입니다.");

		return menu;
	}

	public static void processProfessorMainMenu() throws IOException {
		Hashtable<Integer, Person> studentList = new Hashtable<Integer, Person>(); // 주소록 목록
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
				String passwordinfiletostring = passwordinfile.readLine();  //password 파일내용을 String 형으로 읽어온다.
				int passwordintegerform = Integer.parseInt(passwordinfiletostring,2); //2진수 문자형인 password를 int형 10진수로 변환하여 eword2에 저장
				
				if(professorpassword == passwordintegerform) {
					while(managerloop) {
						while(true) { 
							int menu = selectProfessorMainMenu();
							if(menu == EXIT) {
								System.out.println("관리자 메뉴를 종료합니다:)");
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
								// 전체 출력
								process.printAllStudent(studentList);
								break;
							case LOAD_FILE:
								// 외부 파일 불러오기 
								process.loadUserFile(studentList);
								process.printAllStudent(studentList);
								break;
							case SAVE_FILE:
								// 외부 파일 저장 
								process.saveUserFile(studentList);
								break;
							case PASSWORD_CHANGE:
								// 외부 파일 저장 
								Password.changePassword();
								break;
							}
							managerloop = false;
						}
						passloop = false; //managerloop를 빠져나오고, passloop도 빠져나와 상위클래스로 이동한다.
					}
				}
				else {
					System.out.println("**패스워드가 일치하지 않습니다.");
					passloop = false;
				}
				passwordinfile.close(); //파일 닫음
			}
			catch(Exception e) {
				passloop = false;
			}
		}
		// 파일 저장
		datahandler.processForFileSave(studentList, filename);
	}
}
