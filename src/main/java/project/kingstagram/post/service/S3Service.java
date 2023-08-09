package project.kingstagram.post.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@NoArgsConstructor
public class S3Service {

    private AmazonS3 s3Client;
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.region.static}")
    private String region;


    @PostConstruct //의존성 주입 시점 @Value 어노테이션의 값이 설정되어 있지 않아서 사용
    public void setS3Client(){ // 의존성 주입이 이루어진 후 초기화 수행 메서드, bean이 한번만 초기화 될 수 있도록 해줌 -> 이렇게 해주는 목적 : AmazonS3ClientBuilder를 통해 S3 Client를 가져와야하는데, 자격증명을 해줘야 S3 Client를 가져올 수 있으므로
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey); // accessKey, secretKey를 이용해 자격증명 객체를 얻음

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)) // 자격증명을 통해 s3 Client를 가져옴
                .withRegion(this.region) //region설정
                .build();

    }

    public String upload(MultipartFile file) throws IOException {


        String uploadFileName = getUuidFileName(file.getOriginalFilename());
        //업로드 하기 위해 사용되는 함수
        s3Client.putObject(new PutObjectRequest(bucket, uploadFileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead)); //외부에 공개할 이미지이므로, public read권한 추가
        return s3Client.getUrl(bucket, uploadFileName).toString(); // 업로드한 후 해당 URL을 DB에 저장할 수 있도록 컨트롤러로 URL반환
    }

    public String deleteFile(String uploadFilePath){
        String result = "success";

        try {
//            String keyName = uploadFilePath + "/" + uuidFileName; // 파일경로/년/월/일/파일.확장자

            boolean isObjectExist = s3Client.doesObjectExist(bucket, uploadFilePath);

            if (isObjectExist) {
                s3Client.deleteObject(bucket, uploadFilePath);

            } else {
                result = "file not found";
            }
        }catch (Exception e){
            log.debug("Delete File faild", e);
        }
        return result;
    }

    /**
     * UUID 파일명 반환
     */
    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    /**
     * 년/월/일 폴더명 반환 ? 이거 안쓰는듯
     */
    private String getFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/");
    }
}
