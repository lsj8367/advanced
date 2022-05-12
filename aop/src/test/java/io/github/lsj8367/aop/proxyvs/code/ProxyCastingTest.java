package io.github.lsj8367.aop.proxyvs.code;

import io.github.lsj8367.aop.member.MemberService;
import io.github.lsj8367.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 동적 프록시
@SpringBootTest
@Import(ProxyDiAspect.class)
class ProxyCastingTest {

    @Autowired
    MemberService memberService;

    //JDK는 인터페이스 기반으로 bean 주입을 실행하기에 에러가 발생한다
    // CGLIB는 에러가 발생하지 않는데,
    // 구현체는 MemberService라는 인터페이스를 구현했기 때문에 상위타입으로
    // 치환이 될 수 있기 때문에 에러가 나지 않는다.
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
