package com.landingis.api.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @ApiModelProperty(value = "User name", example = "johndoe", required = true)
    @NotEmpty(message = "User name cannot be empty")
    private String username;

    @ApiModelProperty(value = "Password", example = "Secure@123", required = true)
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
