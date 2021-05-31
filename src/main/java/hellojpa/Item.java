package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//싱글테이블을 할지 조인을 사용할지 전략을 골라야한다.
@DiscriminatorColumn//상속되는 도메인들에 해당하는 구분값을 컬럼으로 관리하기 위한 선언. 기본값은 DTYPE이고 들어가는 값은 각 도메인 클래스명.(변경가능)
@Getter
@Setter
public abstract class Item extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
