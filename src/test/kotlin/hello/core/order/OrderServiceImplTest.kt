package hello.core.order

import hello.core.AppConfig
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val appConfig = AppConfig()

internal class OrderServiceImplTest {
    val memberService: MemberService = appConfig.memberService()
    val orderService: OrderService = appConfig.orderService()

    @Test
    internal fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)
        assertThat(order.discountPrice).isEqualTo(1000)
    }
}