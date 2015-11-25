import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DatahandlerForFile {
	// ���Ϸκ��� �о����
	public void readFromFile(Hashtable<Integer, Person> studentList, String filename) throws IOException {
		String tempLinePerPage;

		try {
			// ���� ���� 
			File textfileWithStudentInfo = new File(filename);
			FileInputStream file = new FileInputStream(textfileWithStudentInfo);		
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));

			// �о���� ������ Person��ü�� ���� 
			while((tempLinePerPage = bufferedReader.readLine()) != null) {	
				Person p_temp = parseDataFromFile(tempLinePerPage);
				studentList.put(p_temp.getId(), p_temp);
			}		
			// ���� �ݱ� 
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			// ������ ���� ���
			System.out.println("��ϵ� ������ �����ϴ�.");
		}
	}

	// �о���� �ٴ����� , ������ �����Ͽ� ����
	private Person parseDataFromFile(String tempLinePerPage) {
		Person tempStorageForStudent = new Person();
		int count = 1;
		String temp = "";

		for(int i = 0; i < tempLinePerPage.length(); i++) {
			if(tempLinePerPage.charAt(i) != ',') {
				if(temp.length() == 0 && tempLinePerPage.charAt(i) == ' ')
					continue;
				temp += tempLinePerPage.charAt(i);
				continue;
			}

			switch(count) {
			case 1:
				tempStorageForStudent.setName(temp);
				//tempstoringstudent.setId(Integer.parseInt(temp));
				break;
			case 2:
				tempStorageForStudent.setId(Integer.parseInt(temp));
				//tempstoringstudent.setName(temp);
				break;
			case 3:
				tempStorageForStudent.setPhoneNumber(temp);
				break;
			}

			temp = "";
			count++;
		}

		if(count == 4)
			tempStorageForStudent.setDepartment(temp);
		return tempStorageForStudent;
	}

	// ���� ����
	public void processForFileSave(Hashtable<Integer, Person> studentList, String filename) throws IOException {
		// ���� ���� 
		File textfile = new File(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textfile));
		Enumeration<Person> e = studentList.elements();

		// ���Ͽ� ��� 
		while (e.hasMoreElements()) {
			Person temp = e.nextElement();

			bufferedWriter.write(temp.getName() + "," + temp.getId() + ",");
			bufferedWriter.write(temp.getPhoneNumber() + "," + temp.getDepartment());
			bufferedWriter.newLine();
		}
		// ���� �ݱ�
		bufferedWriter.close();
	}
}
