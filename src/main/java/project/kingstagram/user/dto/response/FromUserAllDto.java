package project.kingstagram.user.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
public class FromUserAllDto {
    private List<FromUserDto> fromUsers;
    @Builder

    public FromUserAllDto(List<FromUserDto> fromUsers) {
        this.fromUsers = fromUsers;
    }
}
