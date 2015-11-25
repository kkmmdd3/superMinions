import java.io.*;
import java.util.*;

public class ProcessorStudentInfo {
	private static final int PRINT_LINE = 10;	 
	private static final int NAME = 1;		 
	private static final int PHONE_NUMBER = 2;	
	private static final int MAJOR = 3;	
	private static final int CHANG_CANCEL = 4;	 

	public void addStudentInfo(Hashtable<Integer, Person> stuentList) {
		Person student;
		System.out.print("학번 입력 : ");
		int id = inputNumber();
		boolean multipleIdCheck = searchExistIdInStudentInfo(stuentList, id);
		
		if(multipleIdCheck != false) {
			System.out.println("이미 존재하는 학번입니다.");
			return;
		}
		
		student = makeStudentInfo(id);
		stuentList.put(student.getId(), student);
		System.out.println("등록이 완료되었습니다.");

		return;
	}

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
		System.out.print("학번 입력 : ");
		int id = inputNumber();
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);

		if(isIdinStudentInfo == false) {
			System.out.println("기록에 없는 학번입니다.");
			return;
		}

		printIndividualStudent(studentList.get(id));
		if(!askForDeleteStudentInfo()) 
			return;

		studentList.remove(id);
	}

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

	public void updateStudentInfo(Hashtable<Integer, Person> studentList) { 
		System.out.print("학번 입력 : ");
		int id = inputNumber();
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);

		if(isIdinStudentInfo == false) {
			System.out.println("학생정보에 없는 학번입니다.");
			return;
		}

		printIndividualStudent(studentList.get(id));
		changeStudentData(studentList, id);
	}

	private void changeStudentData(Hashtable<Integer, Person> studentList, int index) {
		String tempDataforChange = "";
		Person tempgetStudentInfo = studentList.get(index);

		while(true) {
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

	private int Change_Menu() {
		Change_Menu_Print();
		int menu = inputNumber();

		if(menu < NAME || menu > CHANG_CANCEL) 
			System.out.println("올바르지 않은 입력입니다.");

		return menu;
	}

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
		tempstoringnameforsearch = inputString("검색할 이름 입력 : ");
		searchedlist = makeSearchlistByName(studentList, tempstoringnameforsearch);
		
		if(searchedlist.size() == 0) {
			System.out.println("검색된 결과가 없습니다.");
			return;
		}

		printAllStudent(searchedlist);
	}

	private Hashtable<Integer, Person> makeSearchlistByName(Hashtable<Integer, Person> studentList, String name) {
		Hashtable<Integer, Person> search_list = new Hashtable<Integer, Person>();
		Enumeration<Person> e = studentList.elements();

		while(e.hasMoreElements()) {
			Person temp = e.nextElement();

			if((temp.getName()).lastIndexOf(name) != -1) search_list.put(temp.getId(), temp);
		}

		return search_list;
	}

	private void printIndividualStudent(Person student) {
		Hashtable<Integer, Person> templistforprint = new Hashtable<Integer, Person>();
		templistforprint.put(student.getId(), student);
		printAllStudent(templistforprint);
	}

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

			if(!e.hasMoreElements() || !alarmforOverPage())
				break;
		}
	}

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

	public void loadUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerForFile datahandler = new DatahandlerForFile();
		String filename = inputString("파일명 입력 : ");

		datahandler.readFromFile(studentList, filename);
	}


	public void saveUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerForFile datahandler = new DatahandlerForFile();
		String filename = inputString("파일명 입력 : ");
		
		datahandler.processForFileSave(studentList, filename);
		System.out.println("파일등록(확인: 파일 읽기 또는 전체출력)");
	}

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

	public String inputString(String s) {
		String input_string = "";
		System.out.print(s);
		Scanner scan = new Scanner(System.in);
		input_string = scan.nextLine();

		return input_string;
	}
}
