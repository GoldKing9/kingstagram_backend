package project.kingstagram.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.kingstagram.user.dto.response.UserLogInOutDTO;
@RestControllerAdvice
@Slf4j
public class SessionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            ServletRequestBindingException.class
    )
    public UserLogInOutDTO BadRequestException(final ServletRequestBindingException ex) {
        log.warn("error", ex);
        UserLogInOutDTO out = new UserLogInOutDTO();
        out.setResponseCode(-1);
        out.setResponseMessage("세션이 만료되었습니다. 다시 로그인 해주세요");
        return out;
    }
}
