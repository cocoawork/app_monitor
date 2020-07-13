package top.cocoawork.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.entity.UserAppEntity;
import top.cocoawork.mapper.UserAppMapper;
import top.cocoawork.model.UserApp;
import top.cocoawork.util.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired
    private UserAppMapper userAppMapper;

    @Override
    public boolean inserUserApp(UserApp userApp) {

        UserAppEntity userAppEntity = new UserAppEntity();
        BeanUtil.convert(userApp, userAppEntity);
        userAppMapper.insert(userAppEntity);
        userApp.setAppId(userAppEntity.getAppId());
        return true;
    }

    @Override
    public boolean deleteUserApp(UserApp userApp) {
        return userAppMapper.deleteById(userApp.getAppId()) != 0;
    }

    @Override
    public List<UserApp> selectUserAppsByUserId(String userId) {
        QueryWrapper<UserAppEntity> queryWrapper = new QueryWrapper<UserAppEntity>().eq("user_id", userId);
        List<UserAppEntity> userAppEntities = userAppMapper.selectList(queryWrapper);
        return userAppEntities.stream().map(userAppEntity -> {
            UserApp userApp = new UserApp();
            BeanUtil.convert(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserApp> selectUserAppsByAppId(String appId) {
        QueryWrapper<UserAppEntity> queryWrapper = new QueryWrapper<UserAppEntity>().eq("app_id", appId);
        List<UserAppEntity> userAppEntities = userAppMapper.selectList(queryWrapper);
        return userAppEntities.stream().map(userAppEntity -> {
            UserApp userApp = new UserApp();
            BeanUtil.convert(userAppEntity, userApp);
            return userApp;
        }).collect(Collectors.toList());
    }

}
