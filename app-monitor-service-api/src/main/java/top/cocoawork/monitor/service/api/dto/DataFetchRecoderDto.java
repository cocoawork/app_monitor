package top.cocoawork.monitor.service.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class DataFetchRecoderDto extends BaseModelDto implements Serializable {
    private Long id;

    //开始时间
    @PastOrPresent(message = "开始时间不正确")
    private LocalDateTime beginTime;

    //结束时间
    @PastOrPresent(message = "结束时间不正确")
    private LocalDateTime endTime;

    //类型
    @NotNull(message = "类型不能为空")
    private String type;
}
