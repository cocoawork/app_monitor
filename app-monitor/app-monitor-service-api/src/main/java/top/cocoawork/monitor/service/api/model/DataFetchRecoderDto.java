package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class DataFetchRecoderDto extends BaseModelDto {
    private Long id;

    //开始时间
    private LocalDateTime beginTime;
    //结束时间
    private LocalDateTime endTime;

    //类型
    private String type;
}
