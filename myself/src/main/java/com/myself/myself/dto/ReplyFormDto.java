package com.myself.myself.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReplyFormDto {

    private Long id;

    @NotBlank(message = "내용을 작성하여 주십시오")
    private String content;
}
