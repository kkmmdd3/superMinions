import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DatahandlerForFile {
	// 파일로부터 읽어들임
	public void readFromFile(Hashtable<Integer, Person> studentList, String filename) throws IOException {
		String tempLinePerPage;

		try {
			// 파일 오픈 
			File textfileWithStudentInfo = new File(filename);
			FileInputStream file = new FileInputStream(textfileWithStudentInfo);		
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));

			// 읽어들인 파일을 Person객체로 저장 
			while((tempLinePerPage = bufferedReader.readLine()) != null) {	
				Person p_temp = parseDataFromFile(tempLinePerPage);
				studentList.put(p_temp.getId(), p_temp);
			}		
			// 파일 닫기 
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			// 파일이 없을 경우
			System.out.println("등록된 파일이 없습니다.");
		}
	}

	// 읽어들인 줄단위를 , 단위로 구분하여 저장
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

	// 파일 저장
	public void processForFileSave(Hashtable<Integer, Person> studentList, String filename) throws IOException {
		// 파일 열기 
		File textfile = new File(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textfile));
		Enumeration<Person> e = studentList.elements();

		// 파일에 기록 
		while (e.hasMoreElements()) {
			Person temp = e.nextElement();

			bufferedWriter.write(temp.getName() + "," + temp.getId() + ",");
			bufferedWriter.write(temp.getPhoneNumber() + "," + temp.getDepartment());
			bufferedWriter.newLine();
		}
		// 파일 닫기
		bufferedWriter.close();
	}
}
