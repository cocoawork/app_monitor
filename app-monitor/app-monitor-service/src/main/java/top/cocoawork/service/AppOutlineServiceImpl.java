package top.cocoawork.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import top.cocoawork.entity.AppOutlineEntity;
import top.cocoawork.entity.GenreEntity;
import top.cocoawork.mapper.AppOutlineMapper;
import top.cocoawork.mapper.GenreMapper;
import top.cocoawork.model.AppOutline;
import top.cocoawork.model.Genre;
import top.cocoawork.util.BeanUtil;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
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
    public boolean insertAppOutline(@NotNull AppOutline appOutline){
        AppOutlineEntity appOutlineEntity = new AppOutlineEntity();
        BeanUtil.convert(appOutline, appOutlineEntity);
        try {
            appOutlineMapper.insertAssociation(appOutlineEntity);
        }catch (DuplicateKeyException e) {
            return appOutlineMapper.updateAppOutline(appOutlineEntity);
        }
        List<Genre> genres = appOutline.getGenres();
        for (Genre genre: genres) {
            GenreEntity genreEntity = new GenreEntity();
            BeanUtil.convert(genre, genreEntity);
            GenreEntity existGenre = genreMapper.selectById(genre.getGenreId());
            if (null == existGenre){
                genreMapper.insert(genreEntity);
            }
        }
        return true;
    }

    @Override
    public boolean updateAppOutline(@NotNull AppOutline appOutline) {
        AppOutlineEntity appOutlineEntity = new AppOutlineEntity();
        BeanUtil.convert(appOutline, appOutlineEntity);
        return appOutlineMapper.updateAppOutline(appOutlineEntity);

    }

    @Override
    public boolean deleteAppOutlineByAppId(@NotNull String appId) {
        return appOutlineMapper.deleteById(appId) != 0;
    }


    @Override
    public AppOutline selectAppOutlineByAppId(@NotNull String appId) {
        AppOutlineEntity entity = appOutlineMapper.selectAssociationByAppId(appId);
        if (null != entity) {
            AppOutline appOutline = new AppOutline();
            BeanUtil.convert(entity, appOutline);
            return appOutline;
        }else {
            return null;
        }
    }

    @Override
    public List<AppOutline> selectAppOutlinesByPage(Integer pageIndex, Integer pageSize) {
        List<AppOutlineEntity> list = appOutlineMapper.selectAppOutlinesByPage(pageIndex, pageSize);
        return list.stream().map(appOutlineEntity -> {
            AppOutline appOutline = new AppOutline();
            BeanUtil.convert(appOutlineEntity, appOutline);
            return appOutline;
        }).collect(Collectors.toList());

    }

    @Override
    public List<String> selectAllAppOutlineAppIds() {
        return appOutlineMapper.selectAllAppOutlineAppIds();
    }
}
