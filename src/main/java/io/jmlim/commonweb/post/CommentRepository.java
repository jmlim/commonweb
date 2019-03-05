package io.jmlim.commonweb.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    /*
     * (기본값) FETCH: 설정한 엔티티 에트리뷰트는 EAGER 패치 나머지는 LAZY 패치.
     * post 는 EAGER 모드로 가져옴.
     * */
    /** 내가 원하는 패칭전략을 아래와 같이 설정할 수 있음.*/
    //@EntityGraph(value = "Comment.post")
    @EntityGraph(attributePaths = "post")
    Optional<Comment> getById(Long id);

    /**
     * 모든 컬럼을 다 가지고 옴.
     */
    // List<Comment> findByPost_Id(Long id);

    /**
     * 정의된 컬럼만 가지고 옴.
     */
    List<CommentSummary> findByPost_Id(Long id);

    //  프로젝션을 적용할 클래스가 각각 다를 경우 오버라이딩이 안되므로.. 이렇게 사용, 단점은 타입을 지정해야함.
    <T> List<T> findByPost_Id(Long id, Class<T> type);
}
