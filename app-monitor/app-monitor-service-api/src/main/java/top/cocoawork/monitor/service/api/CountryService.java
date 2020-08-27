package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.CountryDto;

import java.util.List;

public interface CountryService {

    /**
    * @Description: 获取所有国家
    * @Param: []
    * @return: java.util.List<top.cocoawork.model.Country>
    */
    List<CountryDto> selectAllCountry();

}
