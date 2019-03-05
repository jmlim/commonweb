package io.jmlim.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.jmlim.commonweb.post.CommentSpecs.isBest;
import static io.jmlim.commonweb.post.CommentSpecs.isGood;

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

    @Test
    public void specs() {
        // 클라이언트가 매우 간단해짐.
        // 단 여러가지 조합이 들어갈 시 테스트를 철저히 해야한다는 점..
        Page<Comment> page = commentRepository
                .findAll(isBest().or(isGood()), PageRequest.of(0, 10));
        /**
         *    select
         *         comment0_.id as id1_0_,
         *         comment0_.best as best2_0_,
         *         comment0_.comment as comment3_0_,
         *         comment0_.down as down4_0_,
         *         comment0_.post_id as post_id6_0_,
         *         comment0_.up as up5_0_
         *     from
         *         comment comment0_
         *     where
         *         comment0_.best=1
         *         or comment0_.up>=10 limit ?
         * */

    }

    @Test
    public void qbe() {
        Comment prove = new Comment();
        prove.setBest(true);

        //조건을 유연하게 만들수도 없음..
        // 커멘트가 포스트가 언제 이상 작성되었나.. 이런쿼리 못만들고 되게 제한적임.
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnorePaths("up", "down");
        Example<Comment> example = Example.of(prove, exampleMatcher);
        commentRepository.findAll(example);
        /*
        * select
            comment0_.id as id1_0_,
            comment0_.best as best2_0_,
            comment0_.comment as comment3_0_,
            comment0_.down as down4_0_,
            comment0_.post_id as post_id6_0_,
            comment0_.up as up5_0_
        from
            comment comment0_
        where
            comment0_.best=?
        * */
    }
}