package project.kingstagram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.kingstagram.Dto.FileStore;
import project.kingstagram.Dto.PostDto;
import project.kingstagram.Dto.PostForm;
import project.kingstagram.Dto.UploadFile;
import project.kingstagram.service.PostService;


import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileStore fileStore;


    @GetMapping("/api/feed")
    public String newPost(@ModelAttribute PostDto postDto){
        return "get feed";
    }


    @PostMapping(value = "/api/feed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //consumes 속성 : 사용자가 Request Body에 담는 타입 제한, 헤더에 꼭 application/json존재해야함
    public String createPost(@ModelAttribute PostForm postForm) throws IOException {

        if(postForm.getImageUrl().isEmpty()){
            throw new IllegalArgumentException();
        }
        if(postForm.getPostContent().isEmpty()){
            throw new IllegalArgumentException();
        }
        UploadFile uploadFile = fileStore.storeFile(postForm.getImageUrl());
            log.info("사용자가 저장한 파일 : {}", uploadFile.getUploadFileName());
            log.info("저장한 파일 : {} ", uploadFile.getStoreFileName());
            String StoreFileName = uploadFile.getStoreFileName();

            PostDto postDto = new PostDto();
            postDto.setPostContent(postForm.getPostContent());
            postDto.setImageUrl(StoreFileName);
            postService.savePost(postDto);

        return "ok";
    }



}
