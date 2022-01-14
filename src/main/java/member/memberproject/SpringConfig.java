package member.memberproject;

import member.memberproject.repository.JdbcMemberRepository;
import member.memberproject.repository.JdbcTemplateMemberRepository;
import member.memberproject.repository.MemberRepository;
import member.memberproject.repository.MemoryMemberRepository;
import member.memberproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //spring boot 가 자동으로 DI를 해준다


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);//이렇게  @을 사용하면 메모리데이터에서 데이터베이스로 변경이 쉽다/다형성활용을 활용한 스프링사용 장점
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
