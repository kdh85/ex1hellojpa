package hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
//@Table(uniqueConstraints = {
//        @UniqueConstraint(
//                columnNames = {"name"}
//        )
//})
public class TestMember {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date caretDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    //@Transient 를쓰면 컬럼생성을 안한다.
    public TestMember() {
    }

    public TestMember(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
