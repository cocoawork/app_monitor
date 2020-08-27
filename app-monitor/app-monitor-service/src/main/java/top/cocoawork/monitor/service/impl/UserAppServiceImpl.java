package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.monitor.dao.mapper.UserAppMapper;
import top.cocoawork.monitor.dao.entity.UserApp;
import top.cocoawork.monitor.service.api.model.UserAppDto;
import top.cocoawork.monitor.service.api.UserAppService;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired(required = false)
    private UserAppMapper userAppMapper;

    @Override
    public boolean inserUserApp(@NotNull UserAppDto userApp) {

        UserApp userAppEntity = new UserApp();
        BeanUtil.copyProperties(userApp, userAppEntity);
        userAppMapper.insert(userAppEntity);
        userApp.setAppId(userAppEntity.getAppId());
        return true;
    }

    @Override
    public boolean deleteUserApp(@NotNull UserAppDto userApp) {
        Wrapper<UserApp> wrapper = new QueryWrapper<UserApp>().eq("app_id", userApp.getAppId())
                                                                        .eq("user_id", userApp.getUserId());
        int delete = userAppMapper.delete(wrapper);
        return delete != 0;
    }

    @Override
    public List<UserAppDto> selectUserAppsByUserId(@NotNull String userId) {
        List<UserApp> userAppEntities = userAppMapper.selectUserAppsByUserId(userId);
        return userAppEntities.stream().map(userAppEntity -> {
            UserAppDto userApp = new UserAppDto();
            BeanUtil.copyProperties(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserAppDto> selectUserAppsByAppId(@NotNull String appId) {
        QueryWrapper<UserApp> queryWrapper = new QueryWrapper<UserApp>().eq("app_id", appId);
        List<UserApp> userAppEntities = userAppMapper.selectList(queryWrapper);
        return userAppEntities.stream().map(userAppEntity -> {
            UserAppDto userApp = new UserAppDto();
            BeanUtil.copyProperties(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

}
