package top.cocoawork.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.entity.DataFetchRecoderEntity;
import top.cocoawork.mapper.DataFetchRecoderMapper;
import top.cocoawork.model.DataFetchRecoder;
import top.cocoawork.util.BeanUtil;

import javax.validation.constraints.NotNull;


@Service
public class DataFetchRecoderServiceImpl implements DataFetchRecoderService {

    @Autowired
    private DataFetchRecoderMapper dataFetchRecoderMapper;

    @Override
    public boolean insertDataFetchRecoder(@NotNull DataFetchRecoder dataFetchRecoder) {
        DataFetchRecoderEntity dataFetchRecoderEntity = new DataFetchRecoderEntity();
        BeanUtil.convert(dataFetchRecoder, dataFetchRecoderEntity);
        int ret = dataFetchRecoderMapper.insert(dataFetchRecoderEntity);
        dataFetchRecoder.setId(dataFetchRecoderEntity.getId());
        return  ret != 0;
    }
}
