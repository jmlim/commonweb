package io.jmlim.commonweb.post;

import org.springframework.data.jpa.domain.Specification;

public class CommentSpecs {
    public static Specification<Comment> isBest() {
        return (Specification<Comment>)
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.isTrue(root.get(Comment_.best));
    }

    public static Specification<Comment> isGood() {
        return (Specification<Comment>)
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Comment_.up), 10);
    }
}
