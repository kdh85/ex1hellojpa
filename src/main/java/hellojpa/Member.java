package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)//지연로딩. 앤티티를 프록시 객체로 조회하도록.
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team){
        this.team = team;
        team.getMemberList().add(this);
    }

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> productList = new ArrayList<>();
    /*
    ManyToMany로 생성하면 중간맴핑 테이블을 JPA가 알아서 생성은 해주지만
    해당 생성테이블은 두테이블의 맵핑관련 FK컬럼이외의 정보를 추가,수정,삭제가 힘들다.
    이러한 문제때문에 중간관계 맵핑 테이블을 entitiy로 만들어서 각 테이블간에 oneToMany관계로
    직접 정의해서 사용할 것을 권장한다.
     */
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> productList = new ArrayList<>();
}
