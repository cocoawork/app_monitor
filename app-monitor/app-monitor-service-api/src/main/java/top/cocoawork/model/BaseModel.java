package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class BaseModel implements Serializable {

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
