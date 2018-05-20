package personal.alexgarland.helloworldmvc.service.repository.inmemory

class IdSequence @JvmOverloads constructor(private var nextId: Int = 1) {
    
    fun getNextId() = nextId++

}
