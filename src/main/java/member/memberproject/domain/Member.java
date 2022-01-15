package member.memberproject.domain;

import javax.persistence.*;

@Entity//jpa관리 엔티티/ 객체 -관계형DB 맵핑하기 orm

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//pk//DB에 알아서 ID생성하는것을 identity전략
    private Long id;
//    @Column(name="username") 지금은 DB필드명이랑 같아서 안써도 되지만 필드명이 다를때는 ex 필드명이 username이면 위의 어노테이션 사용
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
