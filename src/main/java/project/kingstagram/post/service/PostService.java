package project.kingstagram.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Users;
import project.kingstagram.post.dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.post.dto.request.PostCreateForm;
import project.kingstagram.post.dto.request.PostUpdateDto;
import project.kingstagram.post.dto.response.PostOneDto;
import project.kingstagram.post.dto.response.UserPostAllDto;
import project.kingstagram.post.dto.response.UserPostOneDto;
import project.kingstagram.repository.CommentRepository;
import project.kingstagram.repository.FollowRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;
import project.kingstagram.user.dto.response.ToUserDto;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService{

    private final PostRepository postRepository;
    private final S3Service s3Service;
    private final UsersRepository usersRepository;
    public String savePost(PostCreateForm postCreateForm, Long userId) throws IOException {

        Users users = usersRepository.findById(userId).orElseThrow();
        String imgPath = s3Service.upload(postCreateForm.getImageUrl());
        log.info("imgPath : {}", imgPath );


        Post post = Post.builder()
                .imageUrl(imgPath)
                .postContent(postCreateForm.getPostContent())
                .user(users)
                .build();
        postRepository.save(post);
        return "ok";

    }

    public String deletePost(Long postId, Long userId){
        //게시글이 존재하는가?
        Post post = postRepository.findById(postId).orElseThrow();

        //사용자가 작성한 게시물인가
        if(post.getUser().getUserId().equals(userId)){
            String imagePath = postRepository.findImageUrlByUSerId(postId);
            log.info("post service imagePath : {}", imagePath);
            String imageKeyName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
            log.info("post service imageKeyName : {}", imageKeyName);

            String deleteResult = s3Service.deleteFile(imageKeyName);
            postRepository.deleteById(postId);
            log.info("게시글 정상 삭제");
            return deleteResult;

        }
        log.info("사용자가 작성한 게시글 아님");
        return "error";
    }

    public String updatePost(PostUpdateDto postUpdateDto, Long userId) {
        Long postId = postUpdateDto.getPostId();
        //조건절에 postId와 같은데이타의 postContent를 수정
        Post post = postRepository.findById(postId).orElseThrow();
        if(post.getUser().getUserId().equals(userId)){
            post.update(postUpdateDto.getPostContent());
            log.info("게시글을 작성한 사용자 글 수정 성공");
            return "ok";
        }
        log.info("게시글을 작성한 사용자가 아님");
        return "error";
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
