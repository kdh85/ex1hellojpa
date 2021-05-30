package hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@TableGenerator(
        name = "test_member_table_seq_generator",
        table = "MY_SEQUENCES",
        pkColumnValue = "TEST_MEMBER_SEQ",allocationSize = 1
)
//@Table(uniqueConstraints = {
//        @UniqueConstraint(
//                columnNames = {"name"}
//        )
//})
@SequenceGenerator(name="test_member_seq_generator", sequenceName = "test_member_seq")
public class TestMember {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "test_member_seq_generator")
    @GeneratedValue(strategy = GenerationType.TABLE,
    generator = "test_member_table_seq_generator")
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
