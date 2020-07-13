package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.AppOutlineEntity;

import java.util.List;

@Component
@Mapper
public interface AppOutlineMapper extends BaseMapper<AppOutlineEntity> {

    AppOutlineEntity selectAssociationByAppId(@Param("appId") String appId);

    boolean updateAppOutline(AppOutlineEntity appOutlineEntity);

    boolean insertAssociation(AppOutlineEntity appOutlineEntity);

    List<AppOutlineEntity> selectAppOutlinesByPage(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<String> selectAllAppOutlineAppIds();


}
