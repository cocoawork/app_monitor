package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.cocoawork.monitor.dao.mapper.AppOutlineMapper;
import top.cocoawork.monitor.dao.mapper.GenreMapper;
import top.cocoawork.monitor.dao.entity.AppOutline;
import top.cocoawork.monitor.dao.entity.Genre;
import top.cocoawork.monitor.service.api.model.AppOutlineDto;
import top.cocoawork.monitor.service.api.model.GenreDto;
import top.cocoawork.monitor.service.api.AppOutlineService;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppOutlineServiceImpl implements AppOutlineService {

    private Logger logger = LoggerFactory.getLogger(AppOutlineServiceImpl.class);

    @Autowired
    private AppOutlineMapper appOutlineMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppOutlineDto insert(@NotNull AppOutlineDto appOutline){
        AppOutline appOutlineEntity = new AppOutline();
        BeanUtil.copyProperties(appOutline, appOutlineEntity);

        //先插入类别
        Set<GenreDto> genres = appOutline.getGenre();
        Set<Long> genreIds = new HashSet<>(genres.size());

        for (GenreDto genreDto : genres) {
            Genre genre = new Genre();
            BeanUtil.copyProperties(genreDto, genre);
            genreMapper.insert(genre);
            genreIds.add(genre.getId());
        }

        String genreIdString = StringUtils.join(genreIds, ",");
        appOutlineEntity.setGenres(genreIdString);

        AppOutline exist = appOutlineMapper.selectById(appOutlineEntity.getId());
        if (null == exist) {
            appOutlineMapper.insert(appOutlineEntity);
        }else {
            appOutlineMapper.updateById(appOutlineEntity);
        }

        BeanUtil.copyProperties(appOutlineEntity, appOutline);
        return appOutline;
    }

    @Override
    public AppOutlineDto update(@NotNull AppOutlineDto appOutline) {
        AppOutline appOutlineEntity = new AppOutline();
        BeanUtil.copyProperties(appOutline, appOutlineEntity);
        appOutlineMapper.updateById(appOutlineEntity);
        BeanUtil.copyProperties(appOutlineEntity, appOutline);
        return appOutline;
    }

    @Override
    public boolean deleteById(@NotNull String appId) {
        return appOutlineMapper.deleteById(appId) != 0;
    }


    @Override
    public AppOutlineDto selectById(@NotNull String appId) {
        AppOutline entity = appOutlineMapper.selectById(appId);
        if (null != entity) {
            AppOutlineDto appOutline = new AppOutlineDto();
            BeanUtil.copyProperties(entity, appOutline);
            return appOutline;
        }else {
            return null;
        }
    }

    @Override
    public List<AppOutlineDto> selectByPage(String countryCode, @Min(0) Integer pageIndex, @Max(100) Integer pageSize) {
        IPage<AppOutline> list = appOutlineMapper.selectPage(new Page<>(pageIndex, pageSize), countryCode);
        return list.getRecords().stream().map(appOutlineEntity -> {
            AppOutlineDto appOutline = new AppOutlineDto();
            BeanUtil.copyProperties(appOutlineEntity, appOutline);
            return appOutline;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> selectAllIds() {
        return appOutlineMapper.selectAllIds();
    }
}
