package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryDto extends BaseModelDto {

    private String countryName;
    private String countryCode;

}
