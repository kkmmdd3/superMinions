
public class Person {
	private String name; 
	private int id;				
	private String phonenumber;	
	private String department;			 

	public Person() {

	}
	
	public void setName(String studentname) { 
		name = studentname; 
	}
	
	public String getName() { 
		return name;
	}
	
	public void setId(int studentid) {	
		id = studentid;
	}
	
	public int getId() { 
		return id;
	}
	
	public void setPhoneNumber(String studentphone) { 
		phonenumber = studentphone;
	}
	
	public String getPhoneNumber() {
		return phonenumber;
	}
	
	public void setDepartment(String studentdepartment) { 
		department = studentdepartment; 
	}
	
	public String getDepartment() {
		return department;
	}

	public void printPerson() {
		System.out.printf("%-6d \t%s \t %15s\t%s \n", id, name, phonenumber, department);
	}
}