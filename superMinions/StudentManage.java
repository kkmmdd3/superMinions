
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
				System.out.println("1 �л� ���� �Է� (add)");
				System.out.println("2 �л� ���� ��� (view all)");
				System.out.println("3 �л� ���� �˻� (view)");
				System.out.println("4 ���� ���� (save)");
				System.out.println("5 ���� ���� (open)");
				System.out.println("6 ���� (exit)");
				System.out.println("------------------------------");
				System.out.print("�޴� �Է�:");
				menu = scan.nextInt();
			}while(menu < 1 || menu > 6);
			switch(menu){
			case 1:
				if(i == 10)
					System.out.println("���̻� �Է��� �� �����ϴ�");
				else{
					System.out.println("[�л� ���� �Է�]");
					bc[i] = new Student();
					scan.nextLine();  // �ڹ� Scanner�� ������ ���ʿ��� ���๮�ڸ� �����ϱ� ���� �ӽ÷� ���� �ڵ���
					System.out.print("�й�:");
					bc[i].id = scan.nextLine();
					System.out.print("�̸�:");
					bc[i].name = scan.nextLine();
					System.out.print("�а�:");
					bc[i].department = scan.nextLine();
					System.out.print("����ȣ:");
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
				System.out.print("�˻��ϰ��� �ϴ� �л��̸��� �Է��ϼ���: ");
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
				System.out.println("���Ͽ� ����Ǿ����ϴ�");
				break;
			case 5:
				System.out.println("���� �б� ���");
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("movie.txt"));
			    i = ois.readInt();
				for(int j = 0; j<i; j++){
					bc[j] = (Student)ois.readObject();
					System.out.println(bc[j].id +"/"+bc[j].name+"/"+bc[j].department+"/"+bc[j].phoneNum);
				}
				break;
			case 6:
				System.out.println("����");
			}
		}while(menu != 6);
	}
}