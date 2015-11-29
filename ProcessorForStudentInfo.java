import java.io.*;
import java.util.*;

public class ProcessorForStudentInfo {
	private static final int LINE_PER_PAGE = 10;	 
	private static final int NAME = 1;		 
	private static final int PHONE_NUMBER = 2;	
	private static final int MAJOR = 3;	
	private static final int CHANGE_CANCEL = 4;	
	
	private Hashtable<Integer, Person> makeSearchlistByName(Hashtable<Integer, Person> students, String name) {
		Hashtable<Integer, Person> searchlist = new Hashtable<Integer, Person>();
		Enumeration<Person> studentList = students.elements();
		
		while(studentList.hasMoreElements()) {
			Person stud = studentList.nextElement();
			if((stud.getName()).lastIndexOf(name) != -1) 
				searchlist.put(stud.getId(), stud);
		}
		return searchlist;
	}

	private void printIndividualStudent(Person student) {
		Hashtable<Integer, Person> templistforprint = new Hashtable<Integer, Person>();
		
		templistforprint.put(student.getId(), student);
		printAllStudent(templistforprint);
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

	public void addStudentInfo(Hashtable<Integer, Person> studentList) {
		Person student;
		System.out.print("학번 입력 : ");
		int id = inputNumber();
		boolean multipleIdCheck = searchExistIdInStudentInfo(studentList, id);
		
		if(multipleIdCheck != false) {
			System.out.println("이미 존재하는 학번입니다.");
			return;
		}
		student = makeStudentInfo(id);
		studentList.put(student.getId(), student);
		System.out.println("등록이 완료되었습니다.");
		return;
	}
	
	private Person makeStudentInfo(int tempStudentId) {
		Person student = new Person();
		String tempstorageStudentInfo;
		student.setId(tempStudentId);
		tempstorageStudentInfo = inputString("이름 입력 : ");
		student.setName(tempstorageStudentInfo);
		tempstorageStudentInfo = inputString("휴대폰 번호 입력 : ");
		student.setPhoneNumber(tempstorageStudentInfo);
		tempstorageStudentInfo = inputString("전공 입력 : ");
		student.setDepartment(tempstorageStudentInfo);
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
		String deletechecking;
		while (true) {
			System.out.print("삭제하시겠습니까?(Y/N) ");
			Scanner scan = new Scanner(System.in);
			deletechecking = scan.nextLine();
			
			if(deletechecking.charAt(0) == 'y' || deletechecking.charAt(0) == 'Y')
				return true;
			else if(deletechecking.charAt(0) == 'n' || deletechecking.charAt(0) == 'N')
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
		String dataforChange = "";
		Person getStudentInfo = studentList.get(index);
		
		while(true) {
			int menu = checkInputnumberUpdateMenu();
			
			if(menu == CHANGE_CANCEL) 
				break;
			switch(menu) {
				case NAME:
					dataforChange = inputString("이름 입력 : ");
					getStudentInfo.setName(dataforChange);
					break;
				case PHONE_NUMBER:
					dataforChange = inputString("휴대폰 번호 입력 : ");
					getStudentInfo.setPhoneNumber(dataforChange); 
					break;
				case MAJOR:
					dataforChange = inputString("전공 입력 : ");
					getStudentInfo.setDepartment(dataforChange);
					break;
				default:
					System.out.println("다시 입력 해 주세요");
			}
		}
		return;
	}

	private int checkInputnumberUpdateMenu() {
		printMenuForUpdateStudentData(); 
		int menu = inputNumber();
		if(menu < NAME || menu > CHANGE_CANCEL) 
			System.out.println("올바르지 않은 입력입니다.");
		return menu;
	}

	private void printMenuForUpdateStudentData() {
		System.out.println("====================");
		System.out.println("변경할 정보를 선택하세요");
		System.out.println("[1]이름");
		System.out.println("[2]휴대폰 번호");
		System.out.println("[3]전공");
		System.out.println("[4]변경할 정보가 더이상없음:취소");
		System.out.println("====================");
		System.out.println("[번호선택]: ");
	}

	private boolean searchExistIdInStudentInfo(Hashtable<Integer, Person> studentList, int id) {
		Person existperson = studentList.get(id);
		if(existperson != null) 
			return true; 
		else 
			return false;
	}

	public void searchNameInStudentInfo(Hashtable<Integer, Person> studentList) {
		Hashtable<Integer, Person> completesearchinlist = new Hashtable<Integer, Person>();
		String tempstoringnameforsearch;
		tempstoringnameforsearch = inputString("검색할 이름 입력 : ");
		completesearchinlist = makeSearchlistByName(studentList, tempstoringnameforsearch);
		
		if(completesearchinlist.size() == 0) {
			System.out.println("검색된 결과가 없습니다.");
			return;
		}
		printAllStudent(completesearchinlist);
	}

	public void printAllStudent(Hashtable<Integer, Person> studentList) throws NoSuchElementException {
		Enumeration<Person> e = studentList.elements();
		
		while(e.hasMoreElements()) {
			System.out.println("학번\t\t이름\t\t전화번호\t\t전공");
			for(int j = 0; j < LINE_PER_PAGE ; j++) {
				if(!e.hasMoreElements())
					break;
				Person temp = e.nextElement();
				temp.printPerson();
			}

			if(!e.hasMoreElements() || !alarmforOverPage())
				break;
		}
	}

	public void loadUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerInFile datahandler = new DatahandlerInFile();
		String filename = inputString("파일명 입력 : ");
		datahandler.readFromFile(studentList, filename);
	}

	public void saveUserFileOtherName(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerInFile datahandler = new DatahandlerInFile();
		String filename = inputString("파일명 입력 : ");
		
		datahandler.saveFile(studentList, filename);
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
		String inputstring = "";
		System.out.print(s);
		Scanner scan = new Scanner(System.in);
		inputstring = scan.nextLine();
		return inputstring;
	}
}
