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

            Team team = new Team();
            team.setName("teamA");

            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("user1");
            member.setTeam(team);
            /*
            JPA에서는 테이블간에 FK관계를 가지는 컬럼에 대해서
            1:1, 1:N, N:M관계를 표현해야 하는데
            이때 단방향성인 연관 관계, 또는 양방향성 연관관계를 가지게 된다.
            하지만 실질적으로 JPA는 양방향이 없고 상호간에 단방향인 연관 관계를 가지게 되서,
            양방향일 경우에는 SET을 통해 DB가 호출될때 주인인 쪽의 domain에서 파라메터값이 set되야 하며
            반대로 종속적인 관계쪽에서는 read only화 되는 변수가 생기기 때문에
            해당 파라메터쪽에 SET을 해도 JPA에서 insert/update를 수행하지 않는다.
            그래서 주인인쪽 domain 파라메터에 set을 해주기만 하면 jpa는 정상적으로 작동하겠지만
            반대로 종속domain파라메터에는 jpa가 통신하지 않는 이상 해당 파라메터값이 null인 상태가 되서
            객체지향적으로는 문제가 생긴다. 이를 방지하기 위한 별도의 set을 해당 domain에 해야하는데
            깔금한 코딩처리를 위해 주인domain쪽에 SET을 겸하면서 종속 도메인 파라메터에 값을 같이 SET할 수 있도록
            별도의 메소드 생성을 권하고 있다.
            해당 SET메소드의 주체는 주인 또는 종속관계 둘중한군데에서만 구현해야 한다.
            EX)
            team.getMemberList().add(member);라는 종속 파라메터 셋팅을
            member.changeTeam(team); 을 통해서 해결.(주인)
                or
            team.addMembers(member); 을 통해서 해결.(종속)
            */
            member.changeTeam(team);
            //team.addMembers(member);
            entityManager.persist(member);


            entityManager.flush();
            entityManager.clear();

            //List<Member> findMembers = entityManager.createQuery("select m from Member m join fetch m.team",Member.class).getResultList();

            Child child = new Child();
            child.setName("c1");

            Parent parent = new Parent();
            parent.setName("p1");
            parent.addChild(child);

            entityManager.persist(parent);

            entityManager.flush();
            entityManager.clear();

            Parent parent1 =entityManager.find(Parent.class,parent.getId());
            parent1.getChildList().remove(0);
//            Member finMember = entityManager.find(Member.class,member.getId());
//            List<Member> memberList = finMember.getTeam().getMemberList();
//            for (Member member1 : memberList) {
//                System.out.println("member1 = " + member1.getUsername());
//            }

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

//            TestMember testMember = new TestMember();
//            //testMember.setId(10L);
//            testMember.setAge(10);
//            testMember.setUsername("testA");
//            testMember.setRoleType(RoleType.ADMIN);
//
//            entityManager.persist(testMember);

            entityTransaction.commit();//flush를 내부에서 호출.(기본)
        }catch (Exception e){
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
