package project.kingstagram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.kingstagram.Dto.PostDto;
import project.kingstagram.domain.Post;
import project.kingstagram.repository.PostRespository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRespository postRespository;

    public void savePost(PostDto postDto){
        Post post = postDto.toEntity();
        postRespository.save(post);
    }


}
