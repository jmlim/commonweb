package io.jmlim.commonweb.post;

/*
public interface CommentSummary {
    String getComment();

    int getUp();

    int getDown();

*/
/*
    // 이걸 쓰는 순간 쿼리 최적화 되지 않음. (모든 컬럼 다 조회)
    @Value("#{target.up + ' ' + target.down}")
    String getVotes();*//*

    // 쿼리가 최적화도 되고 커스터마이징 하게 나오게 할 수도 있고..
    // 이 방법을 추천함.
    default String getVotes() {
        return getUp() + " " + getDown();
    }
}
*/

// 클래스로 처리하는 방법.
// 장황해서 인터페이스가 나은것 같다라고 강의에서 설명.
public class CommentSummary {
    private String comment;

    private int up;

    private int down;

    public CommentSummary(String comment, int up, int down) {
        this.comment = comment;
        this.up = up;
        this.down = down;
    }

    public String getComment() {
        return comment;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public String getVotes() {
        return this.up + " " + this.down;
    }
}