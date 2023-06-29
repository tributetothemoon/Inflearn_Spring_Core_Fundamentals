package hello.core.order

import hello.core.AppConfig
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService

class OrderApp

fun main(args: Array<String>) {
    val appConfig = AppConfig()
    val memberService: MemberService = appConfig.memberService()
    val orderService: OrderService = appConfig.orderService()

    val memberId = 1L
    val member: Member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)
    println("order = ${order}")
    println("order.calculatePrice() = ${order.calculatePrice()}")
}