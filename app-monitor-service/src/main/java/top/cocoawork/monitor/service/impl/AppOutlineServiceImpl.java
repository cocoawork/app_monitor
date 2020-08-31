package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.AppOutlineMapper;
import top.cocoawork.monitor.dao.mapper.GenreMapper;
import top.cocoawork.monitor.dao.entity.AppOutline;
import top.cocoawork.monitor.dao.entity.Genre;
import top.cocoawork.monitor.service.api.model.AppOutlineDto;
import top.cocoawork.monitor.service.api.model.GenreDto;
import top.cocoawork.monitor.service.api.AppOutlineService;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Service
public class AppOutlineServiceImpl extends BaseServiceImpl<AppOutline, AppOutlineDto> implements AppOutlineService {

    private Logger logger = LoggerFactory.getLogger(AppOutlineServiceImpl.class);

    @Autowired
    private AppOutlineMapper appOutlineMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppOutlineDto insert(@Valid @NotNull(message = "插入对象不能为空") AppOutlineDto appOutlineDto){
        AppOutline appOutline = dto2d(appOutlineDto);

        //先插入类别
        Set<Genre> genres = appOutline.getGenre();
        Set<Long> genreIds = new HashSet<>(genres.size());

        for (Genre genre : genres) {
            genreMapper.insert(genre);
            genreIds.add(genre.getId());
        }

        String genreIdString = StringUtils.join(genreIds, ",");
        appOutline.setGenres(genreIdString);

        AppOutline exist = appOutlineMapper.selectById(appOutline.getId());
        if (null == exist) {
            appOutlineMapper.insert(appOutline);
        }else {
            appOutlineMapper.updateById(appOutline);
        }

        return d2dto(appOutline);
    }

    @Override
    public AppOutlineDto update(@NotNull(message = "更新对象不能为空") AppOutlineDto appOutlineDto) {
        AppOutline appOutline = dto2d(appOutlineDto);
        appOutlineMapper.updateById(appOutline);
        return d2dto(appOutline);
    }

    @Override
    public boolean deleteById(@NotNull(message = "id不能为空") String appId) {
        return appOutlineMapper.deleteById(appId) != 0;
    }


    @Override
    public AppOutlineDto selectById(@NotNull(message = "id不能为空")  String appId) {
        AppOutline appOutline = appOutlineMapper.selectById(appId);
        if (null != appOutline) {
            return d2dto(appOutline);
        }else {
            return null;
        }
    }

    @Override
    public List<AppOutlineDto> selectPage(String countryCode, @Min(0) Integer pageIndex, @Min(1) @Max(100) Integer pageSize) {
        IPage<AppOutline> list = appOutlineMapper.selectPage(new Page<>(pageIndex, pageSize), countryCode);
        return list.getRecords().stream().map(appOutline -> d2dto(appOutline)).collect(Collectors.toList());
    }

    @Override
    public List<String> selectAllIds() {
        return appOutlineMapper.selectAllIds();
    }
}
