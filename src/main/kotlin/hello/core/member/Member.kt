package hello.core.member

class Member(
    var id: Long?,
    var name: String,
    var grade: Grade = Grade.BASIC,
) {
    constructor(name: String, grade: Grade) : this(null, name, grade)

    fun hello() {
        println("hello")
    }
}
