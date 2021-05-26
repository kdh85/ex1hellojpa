package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");//persistence.xml에 persistence-unit name을 넣어줌.

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        try {
//1. 맴버 생성.
//            TestMember testMember = new TestMember();
//            testMember.setId(1L);
//            testMember.setName("HelloA");
//            entityManager.persist(testMember);//이때 영속성이 생성됨(entityManager와 영속성 관계가 1:1이기 때문에 생성시 하나 생김). 이시점에서 쿼리가 나가지않고 commit시 쿼리가 나간다.
//2. 맴버 조회
            //TestMember findMember = entityManager.find(TestMember.class, 1L);
            //findMember.setName("mew");
            //entityManager.detach(findMember);//영속성 컨텍스트에서 제외처리.
            //entityManager.clear();//영속성을 빈값으로 만듬.
            //entityManager.close();//영속성 닫음.

//3. 맴버 수정.
//            TestMember findMember = entityManager.find(TestMember.class, 1L);
//            findMember.setName("HelloJPA2");
//4. JPSL
//            List<TestMember> result = entityManager.createQuery("select m from TestMember as m",TestMember.class)
//                    .setFirstResult(0)//페이지 시작
//                    .setMaxResults(10)//페이지 끝
//                    .getResultList();//List형태로 데이터 리턴.flush가 동반된다.(JPQL)
//
//            for(TestMember testMember : result){
//                System.out.println(testMember.getName());
//            }

//            TestMember testMember1 = new TestMember(150L,"A");
//            TestMember testMember2 = new TestMember(151L,"B");
//
//            entityManager.persist(testMember1);
//            entityManager.persist(testMember2);
            //entityManager.flush();//강제로 쿼리 실행.

            TestMember testMember = new TestMember();
            testMember.setId(10L);
            testMember.setAge(10);
            testMember.setUsername("testA");
            testMember.setRoleType(RoleType.ADMIN);

            entityManager.persist(testMember);

            entityTransaction.commit();//flush를 내부에서 호출.(기본)
        }catch (Exception e){
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
