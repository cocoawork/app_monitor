package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class Genre extends BaseModel implements Serializable {

    private String genreId;
    private String name;
    private String url;

}
