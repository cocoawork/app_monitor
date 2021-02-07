package top.cocoawork.monitor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.CountryMapper;
import top.cocoawork.monitor.dao.entity.Country;
import top.cocoawork.monitor.service.api.dto.CountryDto;
import top.cocoawork.monitor.service.api.CountryService;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Country service.
 */
@Slf4j
@CacheConfig(cacheNames = "CountryService")
@Validated
@Service
public class CountryServiceImpl extends BaseServiceImpl<Country, CountryDto> implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Cacheable(cacheNames = "all_country")
    @Override
    public List<CountryDto> selectAll() {
        List<Country> countries = countryMapper.selectList(null);
        return countries.stream().map(country -> {
            CountryDto countryDto = new CountryDto();
            try {
                d2dto(country, countryDto);
                countryDto.setCountryName(null);
                countryDto.setCreateAt(null);
                countryDto.setUpdateAt(null);
            }catch (ServiceException e) {
                log.error("转换对象错误", e);
                countryDto.setCountryCode(country.getCountryCode());
            }
            return countryDto;
        }).collect(Collectors.toList());
    }
}
