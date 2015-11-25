import java.io.*;
import java.util.*;

public class Guest {
	// ����ڷκ��� ���� �޴� ������ ���� 
	static int selectGuestMenu() throws IOException {
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		showGuestmenu();
		// ����ڷκ��� �޴� ������ ����
		int menu = process.inputNumber();
		if(menu < 1 || menu > 3)	
			System.out.println("�ùٸ��� ���� �޴������Դϴ�.");

		return menu;
	}

	static void showGuestmenu() throws IOException {
		System.out.println("======����� �޴�=======");
		System.out.println("[1]��� �˻�");
		System.out.println("[2]��ü ���");
		System.out.println("[3]����� �޴� ����");
		System.out.println("=====================");
		System.out.println("�޴� ����[����]:");
	}

	static void processGuestMenu() throws IOException {
		Hashtable<Integer, Person> studentList = new Hashtable<Integer, Person>(); // �ּҷ� ���
		DatahandlerForFile datahandler = new DatahandlerForFile();
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		String filename = "studentInfo.txt";

		// ������ �о���� �Լ�
		datahandler.readFromFile(studentList, filename);

		while(true) {
			// ����ڷκ��� �޴��� ���� ���� 
			int menu = selectGuestMenu();
			if(menu == 3) 
				break;

			switch(menu) {
			case 1:
				process.searchNameInStudentInfo(studentList);
				break;
			case 2: // ��ü ���
				process.printAllStudent(studentList);
				break;
			case 3: // ���ֵ� ��.
				break;
			}
		}
	}
}
