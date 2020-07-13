package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName(value = "t_genre")
public class GenreEntity extends BaseAutoEntity {
    @TableId(value = "genre_id")
    private String genreId;
    private String name;
    private String url;
}
