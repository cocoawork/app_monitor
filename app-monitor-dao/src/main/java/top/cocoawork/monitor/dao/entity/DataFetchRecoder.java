package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.cocoawork.monitor.dao.entity.base.BaseAutoIdEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@TableName("t_data_fetch_recoder")
public class DataFetchRecoder extends BaseAutoIdEntity {


    //开始时间
    private LocalDateTime beginTime;
    //结束时间
    private LocalDateTime endTime;

    //类型
    private String type;


}
