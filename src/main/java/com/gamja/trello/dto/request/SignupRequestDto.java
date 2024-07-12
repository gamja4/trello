package com.gamja.trello.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9~!@#$%^&*]{8,20}$",
            message = "비밀번호 규칙에 맞지 않습니다. (대소문자, 숫자 8자 이상)")
    private String password;
    @NotBlank(message = "닉네임은 공백일 수 없습니다.")
    private String nickname;
}
