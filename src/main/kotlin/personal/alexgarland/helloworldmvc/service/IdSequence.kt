package personal.alexgarland.helloworldmvc.service

class IdSequence @JvmOverloads constructor(private var nextId: Int = 1) {
    
    fun getNextId() = nextId++

}
