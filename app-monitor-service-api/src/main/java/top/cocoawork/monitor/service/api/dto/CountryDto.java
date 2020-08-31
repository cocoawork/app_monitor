package top.cocoawork.monitor.service.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CountryDto extends BaseModelDto implements Serializable {

    @NotBlank(message = "国家名称不能为空")
    private String countryName;

    @NotBlank(message = "国家码不能为空")
    private String countryCode;

}
