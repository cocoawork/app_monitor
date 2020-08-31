package top.cocoawork.monitor.service.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserRoleDto extends BaseModelDto implements Serializable {

    private Long id;

    @Size(min = 1, max = 50)
    @NotBlank(message = "角色名称不能为空")
    private String role;

    private String tag;

    private String menu;

}
