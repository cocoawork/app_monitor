package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@TableName("t_data_fetch_recoder")
public class DataFetchRecoderEntity extends BaseAutoEntity {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    //开始时间
    private LocalDateTime beginTime;
    //结束时间
    private LocalDateTime endTime;

    //类型
    private String type;


}
