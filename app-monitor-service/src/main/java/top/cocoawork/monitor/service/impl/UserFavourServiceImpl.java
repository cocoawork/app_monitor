package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.UserFavourMapper;
import top.cocoawork.monitor.dao.entity.UserFavour;
import top.cocoawork.monitor.service.api.dto.UserFavourDto;
import top.cocoawork.monitor.service.api.UserFavourService;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class UserFavourServiceImpl implements UserFavourService {

    @Autowired(required = false)
    private UserFavourMapper userAppMapper;

    @Override
    public boolean insert(@NotNull UserFavourDto userApp) {

        UserFavour userAppEntity = new UserFavour();
        BeanUtil.copyProperties(userApp, userAppEntity);
        userAppMapper.insert(userAppEntity);
        userApp.setAppId(userAppEntity.getAppId());
        return true;
    }

    @Override
    public boolean deleteById(@NotNull UserFavourDto userApp) {
        Wrapper<UserFavour> wrapper = new QueryWrapper<UserFavour>().eq("app_id", userApp.getAppId())
                                                                        .eq("user_id", userApp.getUserId());
        int delete = userAppMapper.delete(wrapper);
        return delete != 0;
    }

    @Override
    public List<UserFavourDto> selectUserAppsByUserId(@NotNull Long userId) {
        List<UserFavour> userAppEntities = userAppMapper.selectByUserId(userId);
        return userAppEntities.stream().map(userAppEntity -> {
            UserFavourDto userApp = new UserFavourDto();
            BeanUtil.copyProperties(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserFavourDto> selectUserAppsByAppId(@NotNull String appId) {
        QueryWrapper<UserFavour> queryWrapper = new QueryWrapper<UserFavour>().eq("app_id", appId);
        List<UserFavour> userAppEntities = userAppMapper.selectList(queryWrapper);
        return userAppEntities.stream().map(userAppEntity -> {
            UserFavourDto userApp = new UserFavourDto();
            BeanUtil.copyProperties(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

}
