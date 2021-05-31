package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")//DTYPE에 사용되는 값. 선언이 없으면 기본은 도메인 클래스명.
public class Book extends Item{

    private String author;

    private String isbn;
}
