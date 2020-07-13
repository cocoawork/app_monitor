package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Country extends BaseModel {

    private String countryName;
    private String countryCode;

}
