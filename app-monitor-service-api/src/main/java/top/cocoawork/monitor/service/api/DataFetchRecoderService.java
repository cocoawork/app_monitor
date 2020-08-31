package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.DataFetchRecoderDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface DataFetchRecoderService {

    DataFetchRecoderDto insert(@Valid @NotNull(message = "插入对象不能为空") DataFetchRecoderDto dataFetchRecoder);


}
