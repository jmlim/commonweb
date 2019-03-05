package io.jmlim.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

   /* @Test
    public void getComment() {
        // 이거로 가져옴
        commentRepository.getById(1l);

        System.out.println("==========================================");

        // 레이지로 가져옴
        commentRepository.findById(1l);

        */

    /**
     * 내가 원하는 패칭전략을 위와 같이 설정할 수 있음.
     *//*
    }*/
    @Test
    public void getComment() {

        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("spring data jpa projection");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost_Id(1l, CommentOnly.class).forEach(c -> {
            System.out.println("====================");
           // System.out.println(c.getVotes());
            System.out.println(c.getComment());
        });
    }
}