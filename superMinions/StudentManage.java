
import java.util.Scanner;
import java.io.*;

class Student implements Serializable {
	public String id;
	public String name;
	public String department;
	public String phoneNum;
}

public class StudentManage {
	public static void main(String[] args) throws Exception{
		Student[] bc = new Student[10];
		int i=0;
		Scanner scan = new Scanner(System.in);
		int menu=0;	
		do{
			do{
				System.out.println("------------------------------");
				System.out.println("1 학생 정보 입력 (add)");
				System.out.println("2 학생 정보 출력 (view all)");
				System.out.println("3 학생 정보 검색 (view)");
				System.out.println("4 파일 저장 (save)");
				System.out.println("5 파일 열기 (open)");
				System.out.println("6 종료 (exit)");
				System.out.println("------------------------------");
				System.out.print("메뉴 입력:");
				menu = scan.nextInt();
			}while(menu < 1 || menu > 6);
			switch(menu){
			case 1:
				if(i == 10)
					System.out.println("더이상 입력할 수 없습니다");
				else{
					System.out.println("[학생 정보 입력]");
					bc[i] = new Student();
					scan.nextLine();  // 자바 Scanner의 문제로 불필요한 개행문자를 제거하기 위해 임시로 넣은 코드임
					System.out.print("학번:");
					bc[i].id = scan.nextLine();
					System.out.print("이름:");
					bc[i].name = scan.nextLine();
					System.out.print("학과:");
					bc[i].department = scan.nextLine();
					System.out.print("폰번호:");
					bc[i].phoneNum = scan.nextLine();
					i++;
				}
				break;
			case 2:
				for(int j = 0; j<i; j++){
					System.out.println(j+1+":"+bc[j].id +"/"+bc[j].name+"/"+bc[j].department+"/"+bc[j].phoneNum);
				}
				break;
			case 3:
				scan.nextLine();
				System.out.print("검색하고자 하는 학생이름을 입력하세요: ");
				String title = scan.nextLine();

				for(int j = 0; j<i; j++){
					if(title.equals(bc[j].name)){
						System.out.println(bc[j].id +"/"+bc[j].name+"/"+bc[j].department+"/"+bc[j].phoneNum);
						break;
					}				
				}
				break;
			case 4:
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("movie.txt"));
			    oos.writeInt(i);
				for(int j = 0; j < i; j++)
					oos.writeObject(bc[j]);
				oos.close();
				System.out.println("파일에 저장되었습니다");
				break;
			case 5:
				System.out.println("파일 읽기 결과");
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("movie.txt"));
			    i = ois.readInt();
				for(int j = 0; j<i; j++){
					bc[j] = (Student)ois.readObject();
					System.out.println(bc[j].id +"/"+bc[j].name+"/"+bc[j].department+"/"+bc[j].phoneNum);
				}
				break;
			case 6:
				System.out.println("종료");
			}
		}while(menu != 6);
	}
}