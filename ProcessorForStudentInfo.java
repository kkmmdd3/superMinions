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
		temp = inputString("���� �������� �ƹ� Ű�� ��������.");
		
		if(temp.length() == 0) 
			return true;
		if(temp.charAt(0) == 'q' || temp.charAt(0) == 'Q') 
			return false;
		else 
			return true;
	}

	public void addStudentInfo(Hashtable<Integer, Person> studentList) {
		Person student;
		System.out.print("�й� �Է� : ");
		int id = inputNumber();
		boolean multipleIdCheck = searchExistIdInStudentInfo(studentList, id);
		
		if(multipleIdCheck != false) {
			System.out.println("�̹� �����ϴ� �й��Դϴ�.");
			return;
		}
		student = makeStudentInfo(id);
		studentList.put(student.getId(), student);
		System.out.println("����� �Ϸ�Ǿ����ϴ�.");
		return;
	}
	
	private Person makeStudentInfo(int tempStudentId) {
		Person student = new Person();
		String tempstorageStudentInfo;
		student.setId(tempStudentId);
		tempstorageStudentInfo = inputString("�̸� �Է� : ");
		student.setName(tempstorageStudentInfo);
		tempstorageStudentInfo = inputString("�޴��� ��ȣ �Է� : ");
		student.setPhoneNumber(tempstorageStudentInfo);
		tempstorageStudentInfo = inputString("���� �Է� : ");
		student.setDepartment(tempstorageStudentInfo);
		return student;
	}

	public void deleteStudentInfo(Hashtable<Integer, Person> studentList) {
		System.out.print("�й� �Է� : ");
		int id = inputNumber();
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);
		
		if(isIdinStudentInfo == false) {
			System.out.println("��Ͽ� ���� �й��Դϴ�.");
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
			System.out.print("�����Ͻðڽ��ϱ�?(Y/N) ");
			Scanner scan = new Scanner(System.in);
			deletechecking = scan.nextLine();
			
			if(deletechecking.charAt(0) == 'y' || deletechecking.charAt(0) == 'Y')
				return true;
			else if(deletechecking.charAt(0) == 'n' || deletechecking.charAt(0) == 'N')
				return false;
		}
	}

	public void updateStudentInfo(Hashtable<Integer, Person> studentList) {
		System.out.print("�й� �Է� : ");
		int id = inputNumber();
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);
		
		if(isIdinStudentInfo == false) {
			System.out.println("�л������� ���� �й��Դϴ�.");
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
					dataforChange = inputString("�̸� �Է� : ");
					getStudentInfo.setName(dataforChange);
					break;
				case PHONE_NUMBER:
					dataforChange = inputString("�޴��� ��ȣ �Է� : ");
					getStudentInfo.setPhoneNumber(dataforChange); 
					break;
				case MAJOR:
					dataforChange = inputString("���� �Է� : ");
					getStudentInfo.setDepartment(dataforChange);
					break;
				default:
					System.out.println("�ٽ� �Է� �� �ּ���");
			}
		}
		return;
	}

	private int checkInputnumberUpdateMenu() {
		printMenuForUpdateStudentData(); 
		int menu = inputNumber();
		if(menu < NAME || menu > CHANGE_CANCEL) 
			System.out.println("�ùٸ��� ���� �Է��Դϴ�.");
		return menu;
	}

	private void printMenuForUpdateStudentData() {
		System.out.println("====================");
		System.out.println("������ ������ �����ϼ���");
		System.out.println("[1]�̸�");
		System.out.println("[2]�޴��� ��ȣ");
		System.out.println("[3]����");
		System.out.println("[4]������ ������ ���̻����:���");
		System.out.println("====================");
		System.out.println("[��ȣ����]: ");
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
		tempstoringnameforsearch = inputString("�˻��� �̸� �Է� : ");
		completesearchinlist = makeSearchlistByName(studentList, tempstoringnameforsearch);
		
		if(completesearchinlist.size() == 0) {
			System.out.println("�˻��� ����� �����ϴ�.");
			return;
		}
		printAllStudent(completesearchinlist);
	}

	public void printAllStudent(Hashtable<Integer, Person> studentList) throws NoSuchElementException {
		Enumeration<Person> e = studentList.elements();
		
		while(e.hasMoreElements()) {
			System.out.println("�й�\t\t�̸�\t\t��ȭ��ȣ\t\t����");
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
		String filename = inputString("���ϸ� �Է� : ");
		datahandler.readFromFile(studentList, filename);
	}

	public void saveUserFileOtherName(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerInFile datahandler = new DatahandlerInFile();
		String filename = inputString("���ϸ� �Է� : ");
		
		datahandler.saveFile(studentList, filename);
		System.out.println("���ϵ��(Ȯ��: ���� �б� �Ǵ� ��ü���)");
	}

	public int inputNumber() {
		int input = 0;
		try {
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
		} 
		catch (InputMismatchException e) {
			System.out.print("���ڸ� �Է� ���ּ���: ");
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
