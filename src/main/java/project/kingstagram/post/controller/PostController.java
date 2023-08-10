package project.kingstagram.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.post.dto.FileStore;
import project.kingstagram.post.dto.PostDto;
import project.kingstagram.post.dto.UploadFile;
import project.kingstagram.post.dto.response.PostAllDto;
import project.kingstagram.post.dto.request.PostCreateForm;
import project.kingstagram.post.dto.request.PostUpdateDto;
import project.kingstagram.post.dto.response.PostOneDto;
import project.kingstagram.post.dto.response.UserPostAllDto;
import project.kingstagram.post.service.PostService;
import project.kingstagram.post.service.S3Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
//    private final FileStore fileStore;

    @PostMapping(value = "/api/feed") //consumes 속성 : 사용자가 Request Body에 담는 타입 제한, 헤더에 꼭 application/json존재해야함 , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    public String createPost(@ModelAttribute PostCreateForm postCreateForm, @SessionAttribute Long userId) throws IOException {

        log.info("로그인한 사용자 : {}", userId);
        log.info("postCreateFormltoString() : {}",postCreateForm.toString());
        log.info("content : {}", postCreateForm.getPostContent());
        log.info("imageUrl : {}", postCreateForm.getImageUrl());

        if(postCreateForm.getImageUrl().isEmpty()){
            throw new IllegalArgumentException();
        }
        if(postCreateForm.getPostContent().isEmpty()){
            throw new IllegalArgumentException();
        }

//        UploadFile uploadFile = fileStore.storeFile(postCreateForm.getImageUrl());
//            log.info("사용자가 저장한 파일 : {}", uploadFile.getUploadFileName());
//            log.info("저장한 파일 : {} ", uploadFile.getStoreFileName());
//            String StoreFileName = uploadFile.getStoreFileName();
            postService.savePost(postCreateForm, userId);

        return "ok";
    }

    @DeleteMapping("/api/feed/{postId}")
    public String deletePost(@PathVariable Long postId, @SessionAttribute Long userId){
        //게시글이 유효한지 체크? => 서비스단에서 실행

        //게시글 삭제 & s3에서 이미지도 삭제해줘야함
        return postService.deletePost(postId, userId);// 리다이렉트로 경로를 보내줄 경우 : 프론트가 선택할 수 있음 묵살할건지 수용할건지
    }

    @PutMapping("/api/feed")
    public String updatePost(@RequestBody PostUpdateDto updateDto, @SessionAttribute Long userId){
    
        if(updateDto.getPostId()==null){
            throw new IllegalArgumentException();
        }
        if(updateDto.getPostContent().isEmpty()){
            throw new IllegalArgumentException();
        }
        log.info("postId : {}", updateDto.getPostId());
        log.info("post content : {}", updateDto.getPostContent());
        log.info("userId : {}", userId);
        return postService.updatePost(updateDto, userId);
    }

    //단건 조회
    @GetMapping("/api/feed/{postId}")
    public PostAllDto getPost(@PathVariable Long postId, @SessionAttribute Long userId){
        PostOneDto singlePost = postService.getSinglePost(postId);
        return PostAllDto.builder()
                .post(singlePost).build();

    }

    // 여러건 조회
    @GetMapping("/api/feeds")
    public UserPostAllDto getPostAll(@SessionAttribute Long userId, Pageable pageable){
        //사용자 id체크
        log.info("controlloer pageable number : {}",pageable.getPageNumber() );
        log.info("controlloer pageable size : {}",pageable.getPageSize() );
        // 사용자 id와 연관되어 있는 게시글들 전부 불러와보내줌
        return postService.getAllPost(userId, pageable);

    }

    @GetMapping("/test")
    public void test(@SessionAttribute Long userId){ //세션에 저장되어있는 사용자 아이디가져와 사용

        log.info("test userId = {}", userId);
    }

    @GetMapping("/login")
    public HttpStatus login(HttpServletRequest request){
        String jsessionId = request.getRequestedSessionId(); //얘가 세션임
        log.info("jsessionId : {}", jsessionId);
        if(jsessionId != null){ //이미 세션이 존재하는 사용자
            return HttpStatus.BAD_REQUEST;
        }
        //세션이 없을 경우 세션을 생성해준다
        HttpSession session = request.getSession(); //request에서 세션을 가져와서
        session.setAttribute("userId", 2); //사용자 아이디를 세션에 저장하기

        String sessionId = session.getId(); //JSESSIONID 자동 생성되는 세션id(JSESSIONID)
        log.info("sessionId={}", sessionId);
        //로그인할 때 세션을 request에서 가져와서 setAttr로 만들어주고 화면을 이동할 때마다 @SessionAttribute를 사용해 읽어와 유저인증!

        return HttpStatus.OK;
    }
}
