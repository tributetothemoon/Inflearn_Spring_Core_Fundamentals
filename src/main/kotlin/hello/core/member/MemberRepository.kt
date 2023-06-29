package hello.core.member

interface MemberRepository {
    fun save(member: Member): Unit
    fun findById(memberId: Long?): Member?
}