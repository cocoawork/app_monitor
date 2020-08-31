package top.cocoawork.monitor.service.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserFavourDto extends BaseModelDto implements Serializable {

    private Long userId;

    private String appId;

    private AppOutlineDto appOutline;
}
