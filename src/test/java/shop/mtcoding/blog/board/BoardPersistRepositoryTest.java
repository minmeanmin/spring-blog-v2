package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired // IOC에 있는걸 DI 해주는 역할
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test(){
        //given
        int id = 1;
        String title = "제목 수정1";

        //when
        Board board = boardPersistRepository.findById(id); // board는 영속화된 상태
        board.setTitle(title); // PC에 있는 값을 변경한 것
        em.flush();

    } // 더티 체킹

    @Test
    public void save_test(){
        //given
        Board board = new Board("제목5", "내용 5", "ssar");
        //when
        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);

        //then
    }

    @Test
    public void findById_test(){
        //given
        int id = 1;

        //when
        Board board = boardPersistRepository.findById(id);
        em.clear(); //PC의 객체를 지우고, (트랜잭션 종료 시) 삭제 쿼리를 전송함.
        boardPersistRepository.findById(id);
        //System.out.println("findById_test"+board);

        //then
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }

//    @Test
//    public void deleteByIdv2_test(){
//        //given
//        int id = 1;
//
//        //when
//        boardPersistRepository.deleteByIdv2(id); // 쿼리가 안 날라간다.(transaction이 종료되지 않아서)
//
//        // 이 라인 쿼리?
//        em.flush(); // 버퍼에 있는 쿼리를 즉시 전송하는 역할
//    }

    @Test
    public void deleteById_test(){
        //given
        int id = 1;

        //when
        boardPersistRepository.deleteById(id);
    }

}
