package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CountryDto extends BaseModelDto implements Serializable {

    private String countryName;
    private String countryCode;

}
