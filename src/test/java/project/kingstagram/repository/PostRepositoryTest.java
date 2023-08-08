package project.kingstagram.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.kingstagram.domain.Comment;
import project.kingstagram.domain.Like;
import project.kingstagram.domain.Post;
import project.kingstagram.domain.Users;
import project.kingstagram.post.dto.response.PostOneDto;
import project.kingstagram.post.dto.response.UserPostOneDto;
import project.kingstagram.user.service.FollowService;

import java.util.List;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    FollowService followService;

    @BeforeEach
    void init(){
//        Users user1 = new Users();
//        user1.setUserNickname("닉네임 기본");
//        user1.setUserName("경선 기본");
//        user1.setUserPw("기본");
//        user1.setUserEmail("aaa@gmail.com");
////user1이 작성한 게시물 1
//        Post post1 = Post.builder()
//                .postContent("내용기본")
//                .user(user1).build();
//
//        usersRepository.save(user1);
//        postRepository.save(post1);
//
////user1이 작성한 게시물 2
//        Post post2 = Post.builder()
//                .postContent("내용기본")
//                .user(user1).build();
//
//        postRepository.save(post2);
////user1이 post1에 댓글을 달았다
//        Comment comment2 = Comment.builder()
//                .commentContent("1번 사용자의 게시물 댓글")
//                .user(user1)
//                .build();
//        commentRepository.save(comment2);
//        comment2.add(post1);
////user1이 post1에 좋아요를 누름
//        Like like2 = Like.builder()
//                        .post(post1)
//                        .user(user1)
//                        .build();
//        likeRepository.save(like2);
//
//        for (int i = 1; i <= 10; i++) {
//            Users user = new Users();
//            user.setUserNickname("k._.");
//            user.setUserName("경선" + i);
//            user.setUserPw("123");
//            user.setUserEmail("aaa@gmail.com");
//
//            Post post = Post.builder()
//                    .postContent("내용" + i)
//                    .user(user).build();
//
//            usersRepository.save(user);
//            postRepository.save(post);
//            //게시글 하나당 댓글 2개
//            Comment comment = Comment.builder()
//                    .commentContent(i + "번째 게시물의 댓글")
//                    .user(user)
//                    .build();
//
//            Comment comment1 = Comment.builder()
//                    .commentContent(i + "번째 게시물의 댓글" + i)
//                    .user(user)
//                    .build();
//
//            commentRepository.save(comment);
//            commentRepository.save(comment1);
//
//            comment.add(post); //연관관계 잊지말자 제발...
//            comment1.add(post);
//// 좋아요 1개, 작성자가 좋아요를 누름
//            Like like = Like.builder()
//                    .post(post)
//                    .user(user)
//                    .build();
//
//            likeRepository.save(like);
//        }
//
////        Like like = Like.builder()
////                .post(postRepository.findById(1L).get())
////                .build();
////
////        likeRepository.save(like);
//
//

        for(int i=1;i<=20;i++){
             Users users = Users.builder()
                     .userPw("123")
                     .userDescription("hello")
                     .userNickname("kyeong"+i)
                     .userEmail("abc@naver.com")
                     .build();

             Post post = Post.builder()
                     .postContent("post content"+i)
                     .imageUrl("aaa/bbb/ccc")
                     .user(users)
                     .build();

             usersRepository.save(users);
             postRepository.save(post);

            //같은 게시글에 같은 사용자가 3개의 댓글을 달았다 댓글수 : 3
             Comment comment = Comment.builder()
                     .commentContent(i+"번째 게시글의 "+i+"번째 댓글입니다")
                     .post(post)
                     .user(users)
                     .build();
             Comment comment1 = Comment.builder()
                     .commentContent(i+"번째 게시글의 "+(i+1)+"번째 댓글입니다")
                     .post(post)
                     .user(users)
                     .build();
             Comment comment2 = Comment.builder()
                     .commentContent(i+"번째 게시글의 "+(i+2)+"번째 댓글입니다")
                     .post(post)
                     .user(users)
                     .build();

             commentRepository.save(comment);
             commentRepository.save(comment1);
             commentRepository.save(comment2);

             comment.add(post);
             comment1.add(post);
             comment2.add(post);

            //자신이 작성한 게시글에 본인이 좋아요를 누름 좋아요 수 : 1
             Like like = Like.builder()
                     .post(post)
                     .user(users)
                     .build();
             likeRepository.save(like);
        }
        //팔로우 관계 맺어 주기
        for(long i=2; i<=13;i++){
            followService.makeFriend(i,1L);
        }



    }
    @Test
    @Rollback(value = false)
    @DisplayName("게시글 단건 조회")
    void findAllByPostId(){

        PostOneDto postOneDto = postRepository.findAllByPostId(1L);
        PostOneDto postOneDto2 = postRepository.findAllByPostId(2L);
            System.out.println(postOneDto.toString());
            System.out.println(postOneDto2.toString());

    }
    @Test
    @Rollback(value=false)
    @DisplayName("게시글 전체 조회(팔로우하는 사람과 나의 게시글)")
    void findAllPostByUserId(){ //좋아요 개수에 distinct를 해주는 이유 : 조인시 댓글의 수만큼 중복 발생
        Pageable pageable = PageRequest.of(0,10);
        List<UserPostOneDto> allPostByUserId = postRepository.findAllPostByUserId(1L, pageable);
        for (UserPostOneDto userPostOneDto : allPostByUserId) {
            System.out.println("user1의 게시글 : "+userPostOneDto);
        }
    }



}