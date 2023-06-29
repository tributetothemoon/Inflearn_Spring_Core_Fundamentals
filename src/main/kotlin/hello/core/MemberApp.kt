package hello.core

import hello.core.member.Grade
import hello.core.member.Member

class MemberApp {
}

fun main(args: Array<String>) {
    val appConfig = AppConfig()
    val memberService = appConfig.memberService()
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)
    if (findMember != null) {
        println("findMember = ${findMember.name}")
    }

}