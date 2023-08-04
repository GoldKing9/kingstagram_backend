package project.kingstagram.Dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {
    private String fileDir = "/Users/kyeongseon/file-store/";

    public String getFullPath(String filename){
        return fileDir+filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();//업로드한 파일명
        log.info("업로드한 파일명 = {}",originalFilename);
        String storeFileName = createStoreFileName(originalFilename);//고유 아이디값 생성
        log.info("고유한 파일명 = {}", storeFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }

    //서버 내부에서 관리하는 파일명 유일한 이름 생성
    private String createStoreFileName(String originalFilename){
        String ext = extraExt(originalFilename);//png
        String uuid = UUID.randomUUID().toString();//고유 아이디
        return uuid+"."+ext;

    }

    //확장자 별도 추출
    private String extraExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }


}
