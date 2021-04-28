package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void configurationTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 테스팅을 위한 getter 메소드를 쓰기 위해 구체 클래스로 받음. 원래 지양하는 방식이다.
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // 어노테이션을 통해 등록한 빈으로는 싱글톤으로 생성되는 것을 확인할 수 있다.
        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeeply() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // call AppConfig.memberService
        // call AppConfig.memberRepository
        // call AppConfig.orderService
        // call AppConfig.discountPolicy
        // Bean으로 등록된 메소드는 최초 실행 시 DI 컨테이너에 등록되며, 이후 호출되면 DI 컨테이너에서 리턴한다.
    }
}
