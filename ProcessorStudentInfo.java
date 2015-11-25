import java.io.*;
import java.util.*;

public class ProcessorStudentInfo {
	private static final int PRINT_LINE = 10;	// ���������� ����� �� �� 		 
	private static final int NAME = 1;		 
	private static final int PHONE_NUMBER = 2;	
	private static final int MAJOR = 3;	
	private static final int CHANG_CANCEL = 4;	 

	public void addStudentInfo(Hashtable<Integer, Person> stuentList) {
		Person student;
		System.out.print("�й� �Է� : ");
		int id = inputNumber();
		boolean multipleIdCheck = searchExistIdInStudentInfo(stuentList, id);// �Է��� �й��� �����ϴ��� �˻�
		
		if(multipleIdCheck != false) {
			System.out.println("�̹� �����ϴ� �й��Դϴ�.");
			return;
		}
		
		student = makeStudentInfo(id);// ����ڷκ��� �Է��� �޾� �ּҷ� �߰�
		stuentList.put(student.getId(), student);
		System.out.println("����� �Ϸ�Ǿ����ϴ�.");

		return;
	}

	// �ּҷϿ� �߰��� ��� �Է� ����
	private Person makeStudentInfo(int tempStudentId) {
		Person student = new Person();
		String tempStorageStudentInfo;
		student.setId(tempStudentId);
		tempStorageStudentInfo = inputString("�̸� �Է� : ");
		student.setName(tempStorageStudentInfo);
		tempStorageStudentInfo = inputString("�޴��� ��ȣ �Է� : ");
		student.setPhoneNumber(tempStorageStudentInfo);
		tempStorageStudentInfo = inputString("���� �Է� : ");
		student.setDepartment(tempStorageStudentInfo);

		return student;
	}

	public void deleteStudentInfo(Hashtable<Integer, Person> studentList) {
		// ����ڷκ��� �й��� �Է� ���� 
		System.out.print("�й� �Է� : ");
		int id = inputNumber();

		// �Է��� �й��� �����ϴ��� �˻�
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);
		if(isIdinStudentInfo == false) {
			System.out.println("��Ͽ� ���� �й��Դϴ�.");
			return;
		}

		// ������ ��� ���
		printIndividualStudent(studentList.get(id));
		if(!askForDeleteStudentInfo()) 
			return;
		// ��� ����
		studentList.remove(id);
	}

	// ���� ���� ����
	private boolean askForDeleteStudentInfo() {
		String deleteChecking;
		while (true) {
			System.out.print("�����Ͻðڽ��ϱ�?(Y/N) ");
			Scanner scan = new Scanner(System.in);
			deleteChecking = scan.nextLine();
			if(deleteChecking.charAt(0) == 'y' || deleteChecking.charAt(0) == 'Y')
				return true;
			else if(deleteChecking.charAt(0) == 'n' || deleteChecking.charAt(0) == 'N')
				return false;
		}
	}

	// �����
	public void updateStudentInfo(Hashtable<Integer, Person> studentList) {
		// ����ڷκ��� �й��� �Է� ���� 
		System.out.print("�й� �Է� : ");
		int id = inputNumber();
		// �Է��� �й��� �����ϴ��� �˻�
		boolean isIdinStudentInfo = searchExistIdInStudentInfo(studentList, id);
		if(isIdinStudentInfo == false) {
			System.out.println("�л������� ���� �й��Դϴ�.");
			return;
		}

		// ������ ��� ���
		printIndividualStudent(studentList.get(id));
		changeStudentData(studentList, id);
	}

	// �ּҷ� ������ ����
	private void changeStudentData(Hashtable<Integer, Person> studentList, int index) {
		String tempDataforChange = "";
		Person tempgetStudentInfo = studentList.get(index);

		while(true) {
			// ������ �׸� ����
			int menu = Change_Menu();
			if(menu == CHANG_CANCEL) 
				break;

			switch(menu) {
			case NAME:
				tempDataforChange = inputString("�̸� �Է� : ");
				tempgetStudentInfo.setName(tempDataforChange);
				break;
			case PHONE_NUMBER:
				tempDataforChange = inputString("�޴��� ��ȣ �Է� : ");
				tempgetStudentInfo.setPhoneNumber(tempDataforChange); 
				break;
			case MAJOR:
				tempDataforChange = inputString("���� �Է� : ");
				tempgetStudentInfo.setDepartment(tempDataforChange);
				break;
			default:
				System.out.println("�ٽ� �Է� �� �ּ���");
			}
		}

		return;
	}

	// ������ �޴� ���� 
	private int Change_Menu() {
		// ���� �׸� ���� �޴� ��� 
		Change_Menu_Print();
		// ����ڷκ��� �޴��� ���� ���� 
		int menu = inputNumber();
		if(menu < NAME || menu > CHANG_CANCEL) 
			System.out.println("�ùٸ��� ���� �Է��Դϴ�.");

		return menu;
	}

	// ������ ���� �׸� �޴� ��� 
	private void Change_Menu_Print() {
		System.out.println("=================");
		System.out.println("������ ������ �����ϼ���");
		System.out.println("[1]�̸�");
		System.out.println("[2]�޴��� ��ȣ");
		System.out.println("[3]����");
		System.out.println("[4]���");
		System.out.println("=================");
		System.out.print("  [��ȣ����]: ");
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
		tempstoringnameforsearch = inputString("�˻��� �̸� �Է� : ");// �˻��� �̸� �Է�
		searchedlist = makeSearchlistByName(studentList, tempstoringnameforsearch);// �ּҷ� �����Ϳ��� �̸��� �˻�
		
		if(searchedlist.size() == 0) {
			System.out.println("�˻��� ����� �����ϴ�.");
			return;
		}

		printAllStudent(searchedlist);// �˻� ��� ���
	}

	// �ּҷ� �����Ϳ��� �ش��ϴ� �̸��� �˻�
	// �˻������ ���� ��� search_list�� ����� �־� ��ȯ 
	private Hashtable<Integer, Person> makeSearchlistByName(Hashtable<Integer, Person> studentList, String name) {
		Hashtable<Integer, Person> search_list = new Hashtable<Integer, Person>();
		Enumeration<Person> e = studentList.elements();

		//name�� �ִ��� �˻� 
		while(e.hasMoreElements()) {
			Person temp = e.nextElement();

			if((temp.getName()).lastIndexOf(name) != -1) search_list.put(temp.getId(), temp);
		}

		return search_list;
	}

	//���� ��� - ����
	private void printIndividualStudent(Person student) {
		Hashtable<Integer, Person> templistforprint = new Hashtable<Integer, Person>();
		templistforprint.put(student.getId(), student);
		printAllStudent(templistforprint);
	}

	// ���� ��� - ����Ʈ
	public void printAllStudent(Hashtable<Integer, Person> studentList) throws NoSuchElementException {
		Enumeration<Person> e = studentList.elements();
		while(e.hasMoreElements()) {
			System.out.println("�й�\t\t�̸�\t\t��ȭ��ȣ\t\t����");
			for(int j = 0; j < PRINT_LINE ; j++) {
				if(!e.hasMoreElements())
					break;
				Person temp = e.nextElement();
				temp.printPerson();
			}

			// ���� ����� �Ͽ��ų� ��� �޴����� ���Ḧ �ϵ��� �� ��� 
			if(!e.hasMoreElements() || !alarmforOverPage())
				break;
		}
	}

	// ��½� �޴� ��� ��
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

	// ����ڷκ��� ���ϸ��� �Է¹޾� ������ ���� 
	public void loadUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerForFile datahandler = new DatahandlerForFile();
		String filename = inputString("���ϸ� �Է� : ");
		// ������ ������ ����
		datahandler.readFromFile(studentList, filename);
	}

	// ����ڷκ��� ���ϸ��� �Է¹޾� ������ ���� 
	public void saveUserFile(Hashtable<Integer, Person> studentList) throws IOException {
		DatahandlerForFile datahandler = new DatahandlerForFile();
		String filename = inputString("���ϸ� �Է� : ");
		
		datahandler.processForFileSave(studentList, filename);
		System.out.println("���ϵ��(Ȯ��: ���� �б� �Ǵ� ��ü���)");
	}

	// ���� �Է� �Լ�
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

	// ���� �Է� �Լ�
	public String inputString(String s) {
		String input_string = "";
		System.out.print(s);
		Scanner scan = new Scanner(System.in);
		input_string = scan.nextLine();

		return input_string;
	}
}
