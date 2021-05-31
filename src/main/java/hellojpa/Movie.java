package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")//DTYPE에 사용되는 값. 선언이 없으면 기본은 도메인 클래스명.
public class Movie extends Item{

    private String director;

    private String actor;
}
