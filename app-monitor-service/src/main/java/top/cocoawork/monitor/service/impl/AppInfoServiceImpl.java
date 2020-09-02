package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.AppInfoMapper;
import top.cocoawork.monitor.dao.entity.AppInfo;
import top.cocoawork.monitor.service.api.AppInfoService;
import top.cocoawork.monitor.service.api.dto.AppInfoDto;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cocoawork
 */

@Validated
@Service
public class AppInfoServiceImpl extends BaseServiceImpl<AppInfo, AppInfoDto> implements AppInfoService {

    private Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    public AppInfoDto insert(@Valid @NotNull(message = "插入对象不能为空") AppInfoDto appInfoDto) {
        AppInfo appInfo = dto2d(appInfoDto);
        AppInfo exist = appInfoMapper.selectById(appInfoDto.getAppId());
        if (null == exist) {
            appInfoMapper.insert(appInfo);
        }else {
            appInfoMapper.updateById(appInfo);
        }
        return d2dto(appInfo);
    }


    @Override
    public boolean deleteById(@NotNull(message = "id不能为空") String appId) {
        int delete = appInfoMapper.deleteById(appId);
        return delete != 0;
    }

    @Override
    public AppInfoDto update(@NotNull(message = "更新对象不能为空") AppInfoDto appInfoDto) {
        AppInfo appInfo = dto2d(appInfoDto);
        appInfoMapper.updateById(appInfo);
        return d2dto(appInfo);
    }

    @Override
    public AppInfoDto selectById(@NotNull(message = "id不能为空") String appId) {
        AppInfo appInfo = appInfoMapper.selectById(appId);
        if (null != appInfo) {
            return d2dto(appInfo);
        }
        return null;
    }

    @Override
    public AppInfoDto selectByBundleId(@NotNull(message = "bundle id不能为空") String bundleId) {
        QueryWrapper<AppInfo> wrapper = new QueryWrapper<AppInfo>().eq("bundleId", bundleId);
        AppInfo appInfo = appInfoMapper.selectOne(wrapper);
        if (null != appInfo) {
            return d2dto(appInfo);
        }
        return null;
    }

    @Override
    public List<AppInfoDto> selectPage(@Min(0) Integer pageIndex, @Min(1) @Max(100) Integer pageSize) {
        Page<AppInfo> appInfoEntityPage = appInfoMapper.selectPage(new Page<>(pageIndex, pageSize), null).addOrder(OrderItem.desc("createAt"));
        List<AppInfo> records = appInfoEntityPage.getRecords();
        return records.stream().map(appInfo -> {
            return d2dto(appInfo);
        }).collect(Collectors.toList());
    }
}
