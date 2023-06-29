package hello.core.member

import hello.core.AppConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val appConfig = AppConfig()

internal class MemberServiceTest {
    var memberService: MemberService = appConfig.memberService()

    @Test
    fun Join() {
        // given
        val member = Member(1L, "memberA", Grade.VIP)

        // when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // then
        assertThat(member).isEqualTo(findMember)
    }
}