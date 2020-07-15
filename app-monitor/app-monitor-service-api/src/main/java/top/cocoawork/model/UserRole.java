package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserRole implements Serializable {

    private String userId;
    private String userRole;

}
