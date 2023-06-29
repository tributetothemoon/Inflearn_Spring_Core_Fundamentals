package hello.core.member

interface MemberService {
    fun join(member: Member): Unit
    fun findMember(memberId: Long): Member?
}