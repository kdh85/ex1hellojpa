package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "productList")
//    private List<Member> memberList = new ArrayList<>();
    /*
    ManyToMany로 생성하면 중간맴핑 테이블을 JPA가 알아서 생성은 해주지만
    해당 생성테이블은 두테이블의 맵핑관련 FK컬럼이외의 정보를 추가,수정,삭제가 힘들다.
    이러한 문제때문에 중간관계 맵핑 테이블을 entitiy로 만들어서 각 테이블간에 oneToMany관계로
    직접 정의해서 사용할 것을 권장한다.
     */
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberList = new ArrayList<>();
}
