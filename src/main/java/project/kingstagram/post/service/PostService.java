package project.kingstagram.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.post.dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.post.dto.response.PostOneDto;
import project.kingstagram.post.dto.response.UserPostAllDto;
import project.kingstagram.post.dto.response.UserPostOneDto;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.user.dto.response.ToUserDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService{

    private final PostRepository postRepository;
    public Long savePost(PostDto postDto){
        Post post = postDto.toEntity();
        Post savePost = postRepository.save(post);
        return savePost.getPostId();
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    public void updatePost(Long postId, String postContent) {

        //조건절에 postId와 같은데이타의 postContent를 수정
        Post post = postRepository.findById(postId).orElseThrow();
        post.update(postContent);
    }

    @Transactional(readOnly = true)
    public PostOneDto getSinglePost(Long postId) {

        return postRepository.findAllByPostId(postId);

    }

    @Transactional(readOnly = true)
    public UserPostAllDto getAllPost(Long userId, Pageable pageable) {
        List<UserPostOneDto> allPostByUserId = postRepository.findAllPostByUserId(userId, pageable);
            return UserPostAllDto.builder()
                    .posts(allPostByUserId)
                    .build();

    }
}
