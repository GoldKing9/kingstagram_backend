package project.kingstagram.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import java.util.List;
@Getter
public class ToUserAllDto {
    private List<ToUserDto> toUsers;
    @Builder
    public ToUserAllDto(List<ToUserDto> toUsers) {
        this.toUsers = toUsers;
    }
}
