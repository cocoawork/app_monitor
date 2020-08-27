package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
public class GenreDto extends BaseModelDto implements Serializable {

    private String genreId;
    private String name;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GenreDto genreDto = (GenreDto) o;
        return name.equals(genreDto.name) &&
                url.equals(genreDto.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, url);
    }
}
