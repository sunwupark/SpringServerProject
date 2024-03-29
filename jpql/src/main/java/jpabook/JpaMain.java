package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username, 'Hello', TRUE from Member m where m.memberType = jpabook.MemberType.ADMIN";
            List<Object[]> result = em.createQuery(query).getResultList();

//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                            .setFirstResult(0)
//                                    .setMaxResults(10)
//                                            .getResultList();
            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[0] = " + objects[1]);
                System.out.println("objects[0] = " + objects[2]);
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
