package project.kingstagram.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Like;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.post.dto.response.CountLikeDto;
import project.kingstagram.repository.LikeRepository;
import project.kingstagram.repository.PostRepository;
import project.kingstagram.repository.UsersRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UsersRepository usersRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likes(Long postId, Long userId){
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당하는 게시글을 찾을 수 없습니다."));
        Like like = new Like(post, user);
        likeRepository.save(like);
    }
    public void unLikes(Long postId, Long userId){
        likeRepository.deleteLike(postId,userId);
    }
    public CountLikeDto likesCount(Long postId){
        return new CountLikeDto(likeRepository.likeCount(postId));
    }
}
