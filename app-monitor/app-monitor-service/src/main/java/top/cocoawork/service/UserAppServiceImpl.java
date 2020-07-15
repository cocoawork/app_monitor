package top.cocoawork.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.entity.UserAppEntity;
import top.cocoawork.mapper.UserAppMapper;
import top.cocoawork.model.UserApp;
import top.cocoawork.util.BeanUtil;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired
    private UserAppMapper userAppMapper;

    @Override
    public boolean inserUserApp(@NotNull UserApp userApp) {

        UserAppEntity userAppEntity = new UserAppEntity();
        BeanUtil.convert(userApp, userAppEntity);
        userAppMapper.insert(userAppEntity);
        userApp.setAppId(userAppEntity.getAppId());
        return true;
    }

    @Override
    public boolean deleteUserApp(@NotNull UserApp userApp) {
        Wrapper<UserAppEntity> wrapper = new QueryWrapper<UserAppEntity>().eq("app_id", userApp.getAppId())
                                                                        .eq("user_id", userApp.getUserId());
        int delete = userAppMapper.delete(wrapper);
        return delete != 0;
    }

    @Override
    public List<UserApp> selectUserAppsByUserId(@NotNull String userId) {
        List<UserAppEntity> userAppEntities = userAppMapper.selectUserAppsByUserId(userId);
        return userAppEntities.stream().map(userAppEntity -> {
            UserApp userApp = new UserApp();
            BeanUtil.convert(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserApp> selectUserAppsByAppId(@NotNull String appId) {
        QueryWrapper<UserAppEntity> queryWrapper = new QueryWrapper<UserAppEntity>().eq("app_id", appId);
        List<UserAppEntity> userAppEntities = userAppMapper.selectList(queryWrapper);
        return userAppEntities.stream().map(userAppEntity -> {
            UserApp userApp = new UserApp();
            BeanUtil.convert(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

}
