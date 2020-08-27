package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.mapper.AppInfoMapper;
import top.cocoawork.monitor.dao.entity.AppInfo;
import top.cocoawork.monitor.service.api.AppInfoService;
import top.cocoawork.monitor.service.api.model.AppInfoDto;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class AppInfoServiceImpl implements AppInfoService {

    private Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);

    @Autowired(required = false)
    private AppInfoMapper appInfoMapper;

    @Override
    public boolean insert(@NotNull AppInfoDto appinfo) {
        AppInfo entity = new AppInfo();
        BeanUtil.copyProperties(appinfo, entity);
        try {
            return appInfoMapper.insert(entity) != 0;
        }catch (DuplicateKeyException e) {
            return appInfoMapper.updateById(entity) != 0;
        }
    }


    @Override
    public boolean deleteById(@NotNull String appId) {
        int delete = appInfoMapper.deleteById(appId);
        return delete != 0;
    }

    @Override
    public boolean update(@NotNull AppInfoDto appinfo) {
        AppInfo appInfoEntity = new AppInfo();
        BeanUtil.copyProperties(appinfo, appInfoEntity);
        return appInfoMapper.updateById(appInfoEntity) != 0;
    }

    @Override
    public AppInfoDto selectById(@NotNull String appId) {
        AppInfo entity = appInfoMapper.selectById(appId);
        if (null != entity) {
            AppInfoDto appInfo = new AppInfoDto();
            BeanUtil.copyProperties(entity, appInfo);
            return appInfo;
        }
        return null;
    }

    @Override
    public AppInfoDto selectByBundleId(@NotNull String bundleId) {
        QueryWrapper<AppInfo> wrapper = new QueryWrapper<AppInfo>().eq("bundleId", bundleId);
        AppInfo entity = appInfoMapper.selectOne(wrapper);
        if (null != entity) {
            AppInfoDto appInfo = new AppInfoDto();
            BeanUtil.copyProperties(entity, appInfo);
            return appInfo;
        }
        return null;
    }

    @Override
    public List<AppInfoDto> selectByPage(@Min(value = 0) Integer pageIndex, @Min(value = 0) @Max(value = 100) Integer pageSize) {
        Page<AppInfo> appInfoEntityPage = appInfoMapper.selectPage(new Page<>(pageIndex, pageSize), null).addOrder(OrderItem.desc("createAt"));
        List<AppInfo> records = appInfoEntityPage.getRecords();
        return records.stream().map(appInfoEntity -> {
            AppInfoDto appInfo = new AppInfoDto();
            BeanUtil.copyProperties(appInfoEntity, appInfo);
            return appInfo;
        }).collect(Collectors.toList());
    }
}
