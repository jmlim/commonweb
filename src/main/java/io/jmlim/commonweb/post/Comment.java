package io.jmlim.commonweb.post;

import javax.persistence.*;

// 코멘트 포스트의 포스트의 연관관계는 EAGER 로 가져옴.
/** 내가 원하는 패칭전략을 아래와 같이 설정할 수 있음.*/
@NamedEntityGraph(name = "Comment.post",
        attributeNodes = @NamedAttributeNode("post"))
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    // One 으로 끝나는 건 기본값이 Eager 임.
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
