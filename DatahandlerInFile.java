import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DatahandlerInFile {
	public void readFromFile(Hashtable<Integer, Person> studentList, String filename) throws IOException {
		String linePerPage;

		try {
			File studentInfoFile = new File(filename);
			FileInputStream file = new FileInputStream(studentInfoFile);		
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
			
			while((linePerPage = bufferedReader.readLine()) != null) {	
				Person person = parseDataFromFile(linePerPage);
				studentList.put(person.getId(), person); 
			}		
			bufferedReader.close();
		} 
		catch(FileNotFoundException e) {
			System.out.println("등록된 파일이 없습니다.");
		}
	}

	private Person parseDataFromFile(String linePerPage) {
		Person studentRecord = new Person();
		int dataFieldCount = 1;
		String studentDataInOneLine = "";
		
		for(int i = 0; i < linePerPage.length(); i++) {
			if(linePerPage.charAt(i) != ',') {
				if(studentDataInOneLine.length() == 0 && linePerPage.charAt(i) == ' ')
					continue;
				studentDataInOneLine += linePerPage.charAt(i);
				continue;
			}
			setStudentInfo(dataFieldCount, studentRecord, studentDataInOneLine);
			studentDataInOneLine = "";
			dataFieldCount++;
		}
		if(dataFieldCount == 4)
			studentRecord.setDepartment(studentDataInOneLine);
		return studentRecord;
	}

	private void setStudentInfo(int dataFieldCount, Person studentRecord, String information){
		switch(dataFieldCount) {
			case 1:
				studentRecord.setName(information);
				break;
			case 2:
				studentRecord.setId(Integer.parseInt(information));
				break;
			case 3:
				studentRecord.setPhoneNumber(information);
				break;
		}
	}

	public void saveFile(Hashtable<Integer, Person> studentList, String filename) throws IOException {
		File textfile = new File(filename);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textfile));
		Enumeration<Person> studentElements = studentList.elements();
		
		while (studentElements.hasMoreElements()) {
			Person recievedData = studentElements.nextElement();
			bufferedWriter.write(recievedData.getName() + "," + recievedData.getId() + ",");
			bufferedWriter.write(recievedData.getPhoneNumber() + "," + recievedData.getDepartment());
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}
}
