package hello.core.member

val store: MutableMap<Long?, Member> = HashMap()

class MemoryMemberRepository : MemberRepository {
    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(memberId: Long?): Member? {
        return store[memberId]
    }
}