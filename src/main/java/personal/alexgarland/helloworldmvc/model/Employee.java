package personal.alexgarland.helloworldmvc.model;

public class Employee {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}

	private int id;
	private String firstName;
	private String lastName;
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

	@Override public String toString() {
		return "Employee " + String.valueOf(id) + ": " + getFullName();
	}
	
	public Employee copyWithNewId(int newId) {
		return new Employee(newId, this.firstName, this.lastName, this.nickName);
	}
	
}
