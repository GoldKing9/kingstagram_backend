package project.kingstagram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.Dto.*;
import project.kingstagram.service.PostService;


import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileStore fileStore;


    @GetMapping("/api/feeds")
    public String newPost(@ModelAttribute PostDto postDto){
        return "get feed";
    }


    @PostMapping(value = "/api/feed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //consumes 속성 : 사용자가 Request Body에 담는 타입 제한, 헤더에 꼭 application/json존재해야함
    public String createPost(@ModelAttribute PostCreateForm postCreateForm) throws IOException {

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
        return "redirect:/api/feeds";
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



}
