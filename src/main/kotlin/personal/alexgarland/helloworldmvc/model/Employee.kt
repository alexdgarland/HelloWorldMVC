package personal.alexgarland.helloworldmvc.model

class Employee : Comparable<Employee> {

    var id: Int = 0
    var firstName: String = ""
    var lastName: String = ""
    var nickName: String = ""

    val fullName: String
        get() {
            val mid = if (this.nickName === "") " " else " \"" + this.nickName + "\" "
            return this.firstName + mid + this.lastName
        }

    constructor()

    @JvmOverloads constructor(id: Int, firstName: String, lastName: String, nickName: String = "") {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.nickName = nickName
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + firstName.hashCode()
        result = prime * result + id
        result = prime * result + lastName.hashCode()
        result = prime * result + nickName.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (javaClass != other.javaClass)
            return false
        val other = other as Employee?
        if (firstName != other!!.firstName)
            return false
        if (id != other.id)
            return false
        if (lastName != other.lastName)
            return false
        if (nickName != other.nickName)
            return false
        return true
    }

    override fun toString(): String {
        return "Employee " + id.toString() + ": " + fullName
    }

    fun copyWithNewId(newId: Int): Employee {
        return Employee(newId, this.firstName, this.lastName, this.nickName)
    }

    override fun compareTo(other: Employee): Int {
        return this.id - other.id
    }

}
