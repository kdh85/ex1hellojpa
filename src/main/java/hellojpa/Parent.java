package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)//casecade는 영속성에 관련된 애들 같이 persist
    // orphanRemoval은 chlid list 객체에서 삭제한 인덱스 객체가 DB에서 자동으로 delete query가 나가도록 할지 말지 선택하는 옵션. true시 삭제함.
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }
}
