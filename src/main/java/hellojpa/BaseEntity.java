package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass//공통적인 속성을 정의하고 각 도메인들에게 속성만 공유하고 싶은것. 상속개념이 아니다. entity가 아녀서 해당 클래스명의 속성들로 직접 조회가 불가능.
@Getter
@Setter
public abstract class BaseEntity {

    private String createBy;

    private LocalDateTime createTime;

    private String modifiedBy;

    private LocalDateTime modifiedTime;
}
