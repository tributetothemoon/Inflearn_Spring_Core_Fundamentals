package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // Vanilla Java Code
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // Spring Code
        // Bean 어노테이션으로 붙은 메소드를 DI 컨테이너에 넣고 관리하기 시작한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 첫번째 인자에 Bean 어노테이션으로 등록한 메소드 명을 작성한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);
    }
}
