package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
public class AppOutlineDto extends BaseModelDto implements Serializable {

    @NotBlank(message = "app id不能为空")
    @NotNull(message = "app id不能为空")
    private String id;
    private String kind;

    private String artistName;
    private String name;


    private String copyright;

    private String countryCode;

    private String mediaType;

    private String feedType;

    private String artistId;

    private String artistUrl;

    private String artworkUrl100;

    private String url;

    private Set<GenreDto> genre = new HashSet<>();
}
