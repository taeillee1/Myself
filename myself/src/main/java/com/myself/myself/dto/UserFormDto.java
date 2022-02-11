package com.myself.myself.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 3, max = 16, message = "비밀번호는 3자이상, 16자 이하로 입력해주세요")
    private String password;
}
