package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.monitor.dao.mapper.CountryMapper;
import top.cocoawork.monitor.dao.entity.Country;
import top.cocoawork.monitor.service.api.model.CountryDto;
import top.cocoawork.monitor.service.api.CountryService;
import top.cocoawork.monitor.util.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired(required = false)
    private CountryMapper countryMapper;

    @Override
    public List<CountryDto> selectAllCountry() {
        List<Country> countryEntities = countryMapper.selectList(null);
        return countryEntities.stream().map(countryEntity -> {
            CountryDto country = new CountryDto();
            BeanUtil.copyProperties(countryEntity, country);
            return country;
        }).collect(Collectors.toList());
    }
}
