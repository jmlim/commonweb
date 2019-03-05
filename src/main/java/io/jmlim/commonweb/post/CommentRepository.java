package io.jmlim.commonweb.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /*
     * (기본값) FETCH: 설정한 엔티티 에트리뷰트는 EAGER 패치 나머지는 LAZY 패치.
     * post 는 EAGER 모드로 가져옴.
     * */
    /** 내가 원하는 패칭전략을 아래와 같이 설정할 수 있음.*/
    //@EntityGraph(value = "Comment.post")
    @EntityGraph(attributePaths = "post")
    Optional<Comment> getById(Long id);
}
