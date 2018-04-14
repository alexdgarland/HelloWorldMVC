package personal.alexgarland.helloworldmvc.model;

public class Employee implements Cloneable {

	private int id;
	private String lastName;
	private String firstName;
	private String nickName;
	
	public Employee() { }
	
	public Employee(int id, String firstName, String lastName, String nickName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
	}
	
	public Employee(int id, String firstName, String lastName) {
		this(id, firstName, lastName, "");
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFullName() {
		String mid = this.nickName == "" ? " " : " \"" + this.nickName + "\" ";
		return this.firstName + mid + this.lastName;
	}
	
	@Override
	public Employee clone() throws CloneNotSupportedException {
	   
		return (Employee)super.clone();
	   
	}
	
}
