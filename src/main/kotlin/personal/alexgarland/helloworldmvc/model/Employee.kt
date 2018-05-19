package personal.alexgarland.helloworldmvc.model

data class Employee(
        var id: Int = 0, var firstName: String = "", var lastName: String = "", var nickName: String = ""
) : Comparable<Employee> {

    val fullName: String
        get() {
            val mid = if (this.nickName === "") " " else " \"" + this.nickName + "\" "
            return this.firstName + mid + this.lastName
        }

    override fun compareTo(other: Employee): Int {
        return this.id - other.id
    }

}
