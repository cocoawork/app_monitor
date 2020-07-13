package top.cocoawork.service;

import top.cocoawork.model.Country;

import java.util.List;

public interface CountryService {

    /**
    * @Description: 获取所有国家
    * @Param: []
    * @return: java.util.List<top.cocoawork.model.Country>
    */
    List<Country> selectAllCountry();

}
