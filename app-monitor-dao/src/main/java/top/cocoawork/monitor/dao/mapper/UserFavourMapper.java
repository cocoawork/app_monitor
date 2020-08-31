package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.UserFavour;

import java.io.Serializable;
import java.util.List;

@Component
@Mapper
public interface UserFavourMapper extends BaseMapper<UserFavour> {

    List<UserFavour> selectByUserId(Long userId);

    List<UserFavour> selectByAppId(String appId);

    @Override
    UserFavour selectById(Serializable id);

    IPage<UserFavour> selectPageByUserId(Long userId, Page page);
}
