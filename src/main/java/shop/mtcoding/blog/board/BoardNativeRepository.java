package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void updateById(int  id, String title, String content, String username){
        Query query = em.createNativeQuery("update board_tb set title=?, content=?, username=? where id=?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        query.setParameter(4, id);

        query.executeUpdate();
    }

    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);

        return (Board) query.getSingleResult();
    }

    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return (List<Board>) query.getResultList();
    }

    @Transactional
    public Board save(Board board){
        // 1. 비영속 객체
        em.persist(board); // board를 영속화 시키는 부분
        // 2. board -> 영속 객체
        return board; // 영속화된 board
    }

    @Transactional
    public void deleteById(int id){
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}
