import java.io.*;
import java.util.*;

public class Guest {
	static int selectGuestMenu() throws IOException {
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		showGuestmenu();
		int menu = process.inputNumber();

		if(menu < 1 || menu > 3)	
			System.out.println("올바르지 않은 메뉴선택입니다.");

		return menu;
	}

	static void showGuestmenu() throws IOException {
		System.out.println("======사용자 메뉴=======");
		System.out.println("[1]사람 검색");
		System.out.println("[2]전체 출력");
		System.out.println("[3]사용자 메뉴 종료");
		System.out.println("=====================");
		System.out.println("메뉴 선택[숫자]:");
	}

	static void processGuestMenu() throws IOException {
		Hashtable<Integer, Person> studentList = new Hashtable<Integer, Person>(); // 주소록 목록
		DatahandlerForFile datahandler = new DatahandlerForFile();
		ProcessorStudentInfo process = new ProcessorStudentInfo();
		String filename = "studentInfo.txt";

		datahandler.readFromFile(studentList, filename);

		while(true) {
			int menu = selectGuestMenu();

			if(menu == 3) 
				break;

			switch(menu) {
			case 1:
				process.searchNameInStudentInfo(studentList);
				break;
			case 2:
				process.printAllStudent(studentList);
				break;
			case 3:
				break;
			}
		}
	}
}
