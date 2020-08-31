package top.cocoawork.monitor.service.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
public class GenreDto extends BaseModelDto implements Serializable {

    private Long id;

    @NotBlank(message = "类别名称不能为空")
    private String name;

    @NotBlank(message = "类别url不能为空")
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
