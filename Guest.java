import java.io.*;
import java.util.*;

public class Guest {
	private static int selectGuestMenu() throws IOException {
		ProcessorForStudentInfo process = new ProcessorForStudentInfo();
		showGuestmenu();
		int menu = process.inputNumber();
		
		if(menu < 1 || menu > 3)	
			System.out.println("�ùٸ��� ���� �޴� �����Դϴ�.");
		return menu;
	}

	private static void showGuestmenu() throws IOException {
		System.out.println("======����� �޴�=======");
		System.out.println("[1]��� �˻�");
		System.out.println("[2]��ü ���");
		System.out.println("[3]����� �޴� ����");
		System.out.println("=====================");
		System.out.println("�޴� ����[����]:");
	}

	public static void processGuestMenu() throws IOException {
		Hashtable<Integer, Person> studentList = new Hashtable<Integer, Person>();
		DatahandlerInFile datahandler = new DatahandlerInFile();
		ProcessorForStudentInfo process = new ProcessorForStudentInfo();
		String filename = "studentInfo.txt";
		datahandler.readFromFile(studentList, filename);
		
		while(true) {
			int menu = selectGuestMenu();
			if(menu == 3) {
				System.out.println("����� �޴��� �����ϰ� �α��� ȭ������ ���ư��ϴ�.");
				break;
			}
			switch(menu) {
				case 1:
					process.searchNameInStudentInfo(studentList);
					break;
				case 2: 
					process.printAllStudent(studentList);
					break;
			}
		}
	}
}
