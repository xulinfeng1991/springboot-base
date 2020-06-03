package com.parsec.sb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by AutoGenerateCode on 2019-09-04 10:19:16.
 *
 * @author parsec
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AdminLoginRequest", description = "登录入参对象")
public class AdminLoginRequest {

    @NotBlank(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名（用户名）")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "图片验证码文本不能为空")
    @ApiModelProperty(value = "图片验证码文本，如1234")
    private String captchaCode;

    @NotBlank(message = "图片验证码密文不能为空")
    @ApiModelProperty(value = "图片验证码密文，如oY6LIHHdYqVXXXXX")
    private String clientCode;

}