package project.kingstagram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.dto.postGet.CommentDto;
import project.kingstagram.dto.postGet.MiniDto;
import project.kingstagram.dto.postGet.PostAllDto;
import project.kingstagram.dto.postGet.PostOneDto;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.PostRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Long savePost(PostDto postDto){
        Post post = postDto.toEntity();
        Post savePost = postRepository.save(post);
        return savePost.getPostId();
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    @Transactional
    public void updatePost(Long postId, String postContent) {

        //조건절에 postId와 같은데이타의 postContent를 수정
        Post post = postRepository.findById(postId).orElseThrow();
        post.update(postContent);

    }

    public PostAllDto getSinglePost(Long postId) {
        List<CommentDto> commentDto = commentRepository.findAllByPostId(postId);
        MiniDto miniDto = postRepository.findAllByPostId(postId);
        PostOneDto postOneDto = PostOneDto.builder()
                .postContent(miniDto.getPostContent())
                .postId(miniDto.getPostId())
                .imageUrl(miniDto.getImageUrl())
                .likeCount(miniDto.getLikeCount())
                .userId(miniDto.getUserId())
                .userNickname(miniDto.getUserNickname())
                .comments(commentDto)
                .postTime(miniDto.getPostTime())
                .build();

        return PostAllDto
                .builder()
                .post(postOneDto)
                .build();

    }


}
