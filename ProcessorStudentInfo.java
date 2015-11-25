import java.io.*;
import java.util.*;

public class ProcessorStudentInfo {
	private static final int PRINT_LINE = 10;	// 한페이지당 출력할 줄 수 		 
	private static final int NAME = 1;		 
	private static final int PHONE_NUMBER = 2;	
	private static final int MAJOR = 3;	
	private static final int CHANG_CANCEL = 4;	 

	public void addStudentInfo(Hashtable<Integer, Person> stuentList) {
		Person student;
		System.out.print("학번 입력 : ");
		int id = inputNumber();
		boolean multipleIdCheck = searchExistIdInStudentInfo(stuentList, id);// 입력한 학번이 존재하는지 검사
		
		if(multipleIdCheck != false) {
			System.out.println("이미 존재하는 학번입니다.");
			return;
		}
		
		student = makeStudentInfo(id);// 사용자로부터 입력을 받아 주소록 추가
		stuentList.put(student.getId(), student);
		System.out.println("등록이 완료되었습니다.");

		return;
	}

	// 주소록에 추가할 사람 입력 받음
	private Person makeStudentInfo(int tempStudentId) {
		Person student = new Person();
		String tempStorageStudentInfo;
		student.setId(tempStudentId);
		tempStorageStudentInfo = inputString("이름 입력 : ");
		student.setName(tempStorageStudentInfo);
		tempStorageStudentInfo = inputString("휴대폰 번호 입력 : ");
		student.setPhoneNumber(tempStorageStudentInfo);
		tempStorageStudentInfo = inputString("전공 입력 : ");
		student.setDepartment(tempStorageStudentInfo);

		return student;
	}

	public void deleteStudentInfo(Hashtable<Integer, Person> studentList) {
		// 사용자로부터 학번을 입력 받음 
		System.out.print("학번 입력 : ");
		int id = inputNumber();

		// 입력한 학번이 존재하는지 검사
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);
		if(isIdinStudentInfo == false) {
			System.out.println("기록에 없는 학번입니다.");
			return;
		}

		// 삭제할 대상 출력
		printIndividualStudent(studentList.get(id));
		if(!askForDeleteStudentInfo()) 
			return;
		// 목록 삭제
		studentList.remove(id);
	}

	// 삭제 여부 선택
	private boolean askForDeleteStudentInfo() {
		String deleteChecking;
		while (true) {
			System.out.print("삭제하시겠습니까?(Y/N) ");
			Scanner scan = new Scanner(System.in);
			deleteChecking = scan.nextLine();
			if(deleteChecking.charAt(0) == 'y' || deleteChecking.charAt(0) == 'Y')
				return true;
			else if(deleteChecking.charAt(0) == 'n' || deleteChecking.charAt(0) == 'N')
				return false;
		}
	}

	// 변경시
	public void updateStudentInfo(Hashtable<Integer, Person> studentList) {
		// 사용자로부터 학번을 입력 받음 
		System.out.print("학번 입력 : ");
		int id = inputNumber();
		// 입력한 학번이 존재하는지 검사
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);
		if(isIdinStudentInfo == false) {
			System.out.println("학생정보에 없는 학번입니다.");
			return;
		}

		// 변경할 대상 출력
		printIndividualStudent(studentList.get(id));
		changeStudentData(studentList, id);
	}

	// 주소록 데이터 변경
	private void changeStudentData(Hashtable<Integer, Person> studentList, int index) {
		String tempDataforChange = "";
		Person tempgetStudentInfo = studentList.get(index);

		while(true) {
			// 변경할 항목 선택
			int menu = Change_Menu();
			if(menu == CHANG_CANCEL) 
				break;

			switch(menu) {
			case NAME:
				tempDataforChange = inputString("이름 입력 : ");
				tempgetStudentInfo.setName(tempDataforChange);
				break;
			case PHONE_NUMBER:
				tempDataforChange = inputString("휴대폰 번호 입력 : ");
				tempgetStudentInfo.setPhoneNumber(tempDataforChange); 
				break;
			case MAJOR:
				tempDataforChange = inputString("전공 입력 : ");
				tempgetStudentInfo.setDepartment(tempDataforChange);
				break;
			default:
				System.out.println("다시 입력 해 주세요");
			}
		}

		return;
	}

	// 변경할 메뉴 선택 
	private int Change_Menu() {
		// 변경 항목 선택 메뉴 출력 
		Change_Menu_Print();
		// 사용자로부터 메뉴을 선택 받음 
		int menu = inputNumber();
		if(menu < NAME || menu > CHANG_CANCEL) 
			System.out.println("올바르지 않은 입력입니다.");

		return menu;
	}

	// 데이터 변경 항목 메뉴 출력 
	private void Change_Menu_Print() {
		System.out.println("=================");
		System.out.println("변경할 정보를 선택하세요");
		System.out.println("[1]이름");
		System.out.println("[2]휴대폰 번호");
		System.out.println("[3]전공");
		System.out.println("[4]취소");
		System.out.println("=================");
		System.out.print("  [번호선택]: ");
	}

	private boolean searchExistIdInStudentInfo(Hashtable<Integer, Person> studentList, int id) {
		Person existperson = studentList.get(id);
		
		if(existperson != null) 
			return true; 
		else 
			return false;
	}

	public void searchNameInStudentInfo(Hashtable<Integer, Person> studentList) {
		Hashtable<Integer, Person> searchedlist = new Hashtable<Integer, Person>();
		String tempstoringnameforsearch;
		tempstoringnameforsearch = inputString("검색할 이름 입력 : ");// 검색할 이름 입력
		searchedlist = makeSearchlistByName(studentList, tempstoringnameforsearch);// 주소록 데이터에서 이름을 검색
		
		if(searchedlist.size() == 0) {
			System.out.println("검색된 결과가 없습니다.");
			return;
		}

		printAllStudent(searchedlist);// 검색 결과 출력
	}

	// 주소록 데이터에서 해당하는 이름을 검색
	// 검색결과가 있을 경우 search_list에 결과를 넣어 반환 
	private Hashtable<Integer, Person> makeSearchlistByName(Hashtable<Integer, Person> studentList, String name) {
		Hashtable<Integer, Person> search_list = new Hashtable<Integer, Person>();
		Enumeration<Person> e = studentList.elements();

		//name이 있는지 검색 
		while(e.hasMoreElements()) {
			Person temp = e.nextElement();

			if((temp.getName()).lastIndexOf(name) != -1) search_list.put(temp.getId(), temp);
		}

		return search_list;
	}

	//정보 출력 - 개인
	private void printIndividualStudent(Person student) {
		Hashtable<Integer, Person> templistforprint = new Hashtable<Integer, Person>();
		templistforprint.put(student.getId(), student);
		printAllStudent(templistforprint);
	}

	// 정보 출력 - 리스트
	public void printAllStudent(Hashtable<Integer, Person> studentList) throws NoSuchElementException {
		Enumeration<Person> e = studentList.elements();
		while(e.hasMoreElements()) {
			System.out.println("학번\t\t이름\t\t전화번호\t\t전공");
			for(int j = 0; j < PRINT_LINE ; j++) {
				if(!e.hasMoreElements())
					break;
				Person temp = e.nextElement();
				temp.printPerson();
			}

			// 전부 출력을 하였거나 출력 메뉴에서 종료를 하도록 한 경우 
			if(!e.hasMoreElements() || !alarmforOverPage())
				break;
		}
	}

	// 출력시 메뉴 출력 함
	private boolean alarmforOverPage() {
		String temp;
		temp = inputString("다음 페이지는 아무 키나 누르세요.");
		
		if(temp.length() == 0) 
			return true;
		if(temp.charAt(0) == 'q' || temp.charAt(0) == 'Q') 
			return false;
		else 
			return true;
	}

	// 사용자로부터 파일명을 입력받아 파일을 오픈 
	public void loadUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerForFile datahandler = new DatahandlerForFile();
		String filename = inputString("파일명 입력 : ");
		// 지정된 파일을 오픈
		datahandler.readFromFile(studentList, filename);
	}

	// 사용자로부터 파일명을 입력받아 파일을 저장 
	public void saveUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerForFile datahandler = new DatahandlerForFile();
		String filename = inputString("파일명 입력 : ");
		
		datahandler.processForFileSave(studentList, filename);
		System.out.println("파일등록(확인: 파일 읽기 또는 전체출력)");
	}

	// 숫자 입력 함수
	public int inputNumber() {
		int input = 0;

		try {
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
		} 
		catch (InputMismatchException e) {
			System.out.print("숫자만 입력 해주세요: ");
			input = inputNumber();
		}

		return input;
	}

	// 문자 입력 함수
	public String inputString(String s) {
		String input_string = "";
		System.out.print(s);
		Scanner scan = new Scanner(System.in);
		input_string = scan.nextLine();

		return input_string;
	}
}
