package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class AppOutline extends BaseModel implements Serializable {


    private String appId;
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

    private List<Genre> genres;
}
