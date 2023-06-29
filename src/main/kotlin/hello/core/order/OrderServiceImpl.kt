package hello.core.order

import hello.core.discount.DiscountPolicy
import hello.core.member.MemberRepository
import hello.core.member.exception.NoSuchMemberException

class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy
) : OrderService {
    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId) ?: throw NoSuchMemberException()
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}