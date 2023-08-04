package project.kingstagram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.Dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.repository.PostRespository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService{

    private final PostRespository postRespository;

    public Long savePost(PostDto postDto){
        Post post = postDto.toEntity();
        Post savePost = postRespository.save(post);
        return savePost.getPostId();

    }
    public void deletePost(Long postId){

        postRespository.deleteById(postId);
    }

    @Transactional
    public void updatePost(Long postId, String postContent) {

        //조건절에 postId와 같은데이타의 postContent를 수정
        Post post = postRespository.findById(postId).orElseThrow();
        post.update(postContent);

    }
}
