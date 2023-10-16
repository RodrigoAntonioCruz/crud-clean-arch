package com.example.adapter.input.controller.dto.request;

import com.example.adapter.input.controller.dto.UserBaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

@JsonIgnoreProperties("id")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends UserBaseDTO {

}
