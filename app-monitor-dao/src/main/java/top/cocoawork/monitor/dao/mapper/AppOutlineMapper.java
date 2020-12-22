package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.AppOutline;

import java.io.Serializable;
import java.util.List;

@Component
@Mapper
public interface AppOutlineMapper extends BaseMapper<AppOutline> {

    @Override
    AppOutline selectById(Serializable id);

    @Select("select id from t_app_outline where is_delete = 0")
    List<String> selectAllIds();

    IPage<AppOutline> selectPage(Page<?> page, String countryCode);
}
