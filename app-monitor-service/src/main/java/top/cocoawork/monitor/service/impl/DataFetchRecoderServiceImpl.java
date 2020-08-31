package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.DataFetchRecoderMapper;
import top.cocoawork.monitor.dao.entity.DataFetchRecoder;
import top.cocoawork.monitor.service.api.model.DataFetchRecoderDto;
import top.cocoawork.monitor.service.api.DataFetchRecoderService;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Service
public class DataFetchRecoderServiceImpl extends BaseServiceImpl<DataFetchRecoder, DataFetchRecoderDto> implements DataFetchRecoderService {

    @Autowired
    private DataFetchRecoderMapper dataFetchRecoderMapper;

    @Override
    public DataFetchRecoderDto insert(@Valid @NotNull(message = "插入对象不能为空") DataFetchRecoderDto dataFetchRecoderDto) {
        DataFetchRecoder dataFetchRecoder = dto2d(dataFetchRecoderDto);
        dataFetchRecoderMapper.insert(dataFetchRecoder);
        return d2dto(dataFetchRecoder);
    }
}
