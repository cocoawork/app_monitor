package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.monitor.dao.mapper.DataFetchRecoderMapper;
import top.cocoawork.monitor.dao.entity.DataFetchRecoder;
import top.cocoawork.monitor.service.api.model.DataFetchRecoderDto;
import top.cocoawork.monitor.service.api.DataFetchRecoderService;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.NotNull;


@Service
public class DataFetchRecoderServiceImpl implements DataFetchRecoderService {

    @Autowired(required = false)
    private DataFetchRecoderMapper dataFetchRecoderMapper;

    @Override
    public boolean insertDataFetchRecoder(@NotNull DataFetchRecoderDto dataFetchRecoder) {
        DataFetchRecoder dataFetchRecoderEntity = new DataFetchRecoder();
        BeanUtil.copyProperties(dataFetchRecoder, dataFetchRecoderEntity);
        int ret = dataFetchRecoderMapper.insert(dataFetchRecoderEntity);
        dataFetchRecoder.setId(dataFetchRecoderEntity.getId());
        return  ret != 0;
    }
}
