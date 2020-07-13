package top.cocoawork.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.AppInfoEntity;
import top.cocoawork.mapper.AppInfoMapper;
import top.cocoawork.model.AppInfo;
import top.cocoawork.util.BeanUtil;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class AppInfoServiceImpl implements AppInfoService {

    private Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    public boolean insertAppInfo(@NotNull AppInfo appinfo) {
        AppInfoEntity entity = new AppInfoEntity();
        BeanUtil.convert(appinfo, entity);
        try {
            return appInfoMapper.insert(entity) != 0;
        }catch (DuplicateKeyException e) {
            return appInfoMapper.updateById(entity) != 0;
        }
    }


    @Override
    public boolean deleteAppInfoByAppId(@NotNull String appId) {
        int delete = appInfoMapper.deleteById(appId);
        return delete != 0;
    }

    @Override
    public boolean updateAppInfo(@NotNull AppInfo appinfo) {
        AppInfoEntity appInfoEntity = new AppInfoEntity();
        BeanUtil.convert(appinfo, appInfoEntity);
        return appInfoMapper.updateById(appInfoEntity) != 0;
    }

    @Override
    public AppInfo selectAppInfoByAppId(@NotNull String appId) {
        AppInfoEntity entity = appInfoMapper.selectById(appId);
        if (null != entity) {
            AppInfo appInfo = new AppInfo();
            BeanUtil.convert(entity, appInfo);
            return appInfo;
        }
        return null;
    }

    @Override
    public List<AppInfo> selectAppInfosByPage(@Min(value = 0) Integer pageIndex, @Min(value = 0) @Max(value = 100) Integer pageSize) {
        Page<AppInfoEntity> appInfoEntityPage = appInfoMapper.selectPage(new Page<>(pageIndex, pageSize), null);
        List<AppInfoEntity> records = appInfoEntityPage.getRecords();
        return records.stream().map(appInfoEntity -> {
            AppInfo appInfo = new AppInfo();
            BeanUtil.convert(appInfoEntity, appInfo);
            return appInfo;
        }).collect(Collectors.toList());
    }
}
