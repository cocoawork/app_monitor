package top.cocoawork.monitor.service.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class BaseModelDto implements Serializable {

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
