package project.kingstagram;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //허용할 출처
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE") //허용할 HTTP method
//                .allowCredentials(true) //쿠키 인증 요청 허용
                .maxAge(3600);  // 브러우저 캐시를 이용, Access-Control-Max-Age 헤더에 캐시될 시간 명시 -> Preflight요청을 캐싱시켜 최적화, 원하는 시간만큼 pre-flight 리퀘스트 캐싱 초단위, 60분

    }
}
