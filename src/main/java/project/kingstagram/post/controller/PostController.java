package project.kingstagram.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import project.kingstagram.post.service.PostService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileStore fileStore;


    @PostMapping(value = "/api/feed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //consumes 속성 : 사용자가 Request Body에 담는 타입 제한, 헤더에 꼭 application/json존재해야함
    public String createPost(@ModelAttribute PostCreateForm postCreateForm, @SessionAttribute int userId) throws IOException {
        System.out.println("userid : "+userId);
        if(postCreateForm.getImageUrl().isEmpty()){
            throw new IllegalArgumentException();
        }
        if(postCreateForm.getPostContent().isEmpty()){
            throw new IllegalArgumentException();
        }
        UploadFile uploadFile = fileStore.storeFile(postCreateForm.getImageUrl());
            log.info("사용자가 저장한 파일 : {}", uploadFile.getUploadFileName());
            log.info("저장한 파일 : {} ", uploadFile.getStoreFileName());
            String StoreFileName = uploadFile.getStoreFileName();

            PostDto postDto = new PostDto();
            postDto.setPostContent(postCreateForm.getPostContent());
            postDto.setImageUrl(StoreFileName);
            postService.savePost(postDto);

        return "ok";
    }

    @DeleteMapping("/api/feed/{postId}")
    public String deletePost(@PathVariable Long postId){
        //게시글이 유효한지 체크?

        //게시글 삭제
        postService.deletePost(postId);
        return "redirect:/api/feeds"; // 리다이렉트 사용 보류 - 코치님께 여쭤보기!, 결과 하록님께도 전달
    }

    @PutMapping("/api/feed")
    public String updatePost(@RequestBody PostUpdateDto updateDto){
    
        Long postId = updateDto.getPostId();
        log.info("postId ={}", postId);
        String postContent = updateDto.getPostContent();
        log.info("postContent = {}",postContent);
        if(postId == 0){
            throw new IllegalArgumentException();
        }
        if(updateDto.getPostContent().isEmpty()){
            throw new IllegalArgumentException();
        }

        postService.updatePost(postId, updateDto.getPostContent());

        return "update";
    }

    //단건 조회
    @GetMapping("/api/feed/{postId}")
    public PostAllDto getPost(@PathVariable Long postId, @SessionAttribute int userId){
        PostOneDto singlePost = postService.getSinglePost(postId);
        return PostAllDto.builder()
                .post(singlePost).build();

    }

    // 여러건 조회
//    @GetMapping("/api/feeds")
//    public String getPostAll(@SessionAttribute int userId){
//        //사용자 id체크
//
//        // 사용자 id와 연관되어 있는 게시글들 전부 불러와보내줌
//        postService.getAllPost(userId);
//
//    }

    @GetMapping("/test")
    public void test(@SessionAttribute int userId){ //세션에 저장되어있는 사용자 아이디가져와 사용

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
        session.setAttribute("userId", 1); //사용자 아이디를 세션에 저장하기

        String sessionId = session.getId(); //JSESSIONID 자동 생성되는 세션id(JSESSIONID)
        log.info("sessionId={}", sessionId);
        //로그인할 때 세션을 request에서 가져와서 setAttr로 만들어주고 화면을 이동할 때마다 @SessionAttribute를 사용해 읽어와 유저인증!

        return HttpStatus.OK;
    }
}
