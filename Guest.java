import java.io.*;
import java.util.*;

public class Guest {
	private static int selectGuestMenu() throws IOException {
		ProcessorForStudentInfo process = new ProcessorForStudentInfo();
		showGuestmenu();
		int menu = process.inputNumber();
		
		if(menu < 1 || menu > 3)	
			System.out.println("올바르지 않은 메뉴 권한입니다.");
		return menu;
	}

	private static void showGuestmenu() throws IOException {
		System.out.println("======사용자 메뉴=======");
		System.out.println("[1]사람 검색");
		System.out.println("[2]전체 출력");
		System.out.println("[3]사용자 메뉴 종료");
		System.out.println("=====================");
		System.out.println("메뉴 선택[숫자]:");
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
				System.out.println("사용자 메뉴를 종료하고 로그인 화면으로 돌아갑니다.");
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
