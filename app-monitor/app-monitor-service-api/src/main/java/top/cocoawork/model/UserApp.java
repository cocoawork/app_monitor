package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserApp extends BaseModel implements Serializable {
    private String userId;
    private String appId;
}
