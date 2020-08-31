package top.cocoawork.monitor.service.api.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ApiModel(value = "UserDto", description = "用户信息")
public class UserDto extends BaseModelDto implements Serializable {

    private Long id;

    @Size(max = 20,message = "用户名长度在20位以内")
    @NotBlank(message = "用户名不能为空")
    @NotNull(message = "用户名不能为空")
    private String username;

    @Size(max = 20, min = 6, message = "密码为6-20位字符")
    @NotBlank(message = "密码不能为空")
    @NotNull(message = "密码不能为空")
    private String password;

    @Email(message = "email格式不正确")
    @NotBlank(message = "email不能为空")
    @NotNull(message = "email不能为空")
    private String email;

    private String profile;

    @Max(1)
    @Min(0)
    private Integer gender;

    @Max(100)
    @Min(1)
    private Integer age;

    private Set<UserRoleDto> roles = new HashSet<>();

}
