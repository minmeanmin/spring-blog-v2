package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO requestDTO){
        Board board = findById(id);
        board.update(requestDTO);
    } // 더티체킹

    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    @Transactional
    public Board save(Board board){
        // 1. 비영속 객체
        em.persist(board); // board를 영속화 시키는 부분
        // 2. board -> 영속 객체
        return board; // 영속화된 board
    }

}
