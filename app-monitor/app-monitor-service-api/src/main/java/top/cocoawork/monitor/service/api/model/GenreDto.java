package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class GenreDto extends BaseModelDto implements Serializable {

    private String genreId;
    private String name;
    private String url;

}
