package io.jmlim.commonweb.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /*@GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id) {
        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.get();
        return post.getTitle();
    }*/

    /**
     * id 를 받아 Post 로 컨버팅 해줌. 위의 과정 생략이 가능.
     * DomainClassConverter 가 자동으로 해준다.
     */
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }

   /* @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }*/

    @GetMapping("/posts")
    public PagedResources<Resource<Post>> getPosts(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Post> all = postRepository.findAll(pageable);
        return assembler.toResource(all);
    }
}

