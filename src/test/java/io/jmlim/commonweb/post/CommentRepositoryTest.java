package io.jmlim.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void getComment() {
        // 이거로 가져옴
        commentRepository.getById(1l);

        System.out.println("==========================================");

        // 레이지로 가져옴
        commentRepository.findById(1l);

        /** 내가 원하는 패칭전략을 위와 같이 설정할 수 있음.*/
    }
}