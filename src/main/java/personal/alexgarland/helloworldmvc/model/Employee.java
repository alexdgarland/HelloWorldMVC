package personal.alexgarland.helloworldmvc.model;

public class Employee {

	private String id;
	private String lastName;
	private String firstName;
	private String nickName;
	
	public Employee(String id, String firstName, String lastName, String nickName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
}
