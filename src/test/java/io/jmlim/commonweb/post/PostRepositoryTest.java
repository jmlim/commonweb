package io.jmlim.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);

        // 이것을 붙이지 않으면 insert 쿼리 날아가지 않음.
        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("jpa");
        // 엔티티의 @Id 프로퍼티를 찾는다.
        // 해당 프로퍼티가 null이면 Transient 상태로 판단하고 id가 null이 아니면 Detached 상태로 판단한다.
        /**
         *  리턴받은 인스턴스를 사용하여 실수를 방지하는것이 좋다.
         */
        Post savedPost = postRepository.save(post);//persist

        assertThat(entityManager.contains(post)).isTrue(); // 영속화 상태
        assertThat(entityManager.contains(savedPost)).isTrue(); // 영속화 상태
        assertThat(savedPost == post).isTrue();

        Post postUpdate = new Post();
        postUpdate.setId(post.getId()); // 아이디가 있는 상태이므로 머지가 됨.
        postUpdate.setTitle("hibernate");
        Post updatedPost = postRepository.save(postUpdate);//merge
        /**
         * 머지의 내부 로직: 전달받은 파라미터(postUpdate)의 복사본을 만들어서
         * 그 복사본을 가지고 데이터베이스 영속화를 하고 이 복사본은 persist 상태를 만들지 않음.
         * 리턴받은 updatedPost가 영속화가 되고 postUpdate는 영속화 되지 않음.
         */

        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(entityManager.contains(postUpdate)).isFalse(); //영속화 되지 않음.
        assertThat(updatedPost == postUpdate).isFalse();

        postUpdate.setTitle("이거 바꾼다고 업데이트 안됨");
        updatedPost.setTitle("업데이터 되는놈");

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        /**
         *  꼬옥 리턴받은 인스턴스를 사용하여 실수를 방지하는것이 좋다.
         */

    }

    @Test
    public void findByTitleStartsWith() {
        savePost();

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size()).isEqualTo(1);
    }

    private void savePost() {
        Post post = new Post();
        post.setTitle("Spring Data Jpa");
        postRepository.save(post);
    }

    @Test
    public void findByTitle() {
        savePost();
        // Sort 는 그 안에서 사용한 프로퍼티 또는  alias 가 엔티티에 없는 경우에는 예외가 발생.
        // List<Post> all = postRepository.findByTitle("Spring Data Jpa", Sort.by("title"));
        // JpaSort.unsafe() 를 사용하면 함수 호출을 할 수 있음.
        List<Post> all = postRepository.findByTitle("Spring Data Jpa", JpaSort.unsafe("LENGTH(title)"));
        assertThat(all.size()).isEqualTo(1);
    }
}