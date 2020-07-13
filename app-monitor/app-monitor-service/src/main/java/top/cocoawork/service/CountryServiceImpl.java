package top.cocoawork.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.entity.CountryEntity;
import top.cocoawork.mapper.CountryMapper;
import top.cocoawork.model.Country;
import top.cocoawork.util.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> selectAllCountry() {
        List<CountryEntity> countryEntities = countryMapper.selectList(null);
        return countryEntities.stream().map(countryEntity -> {
            Country country = new Country();
            BeanUtil.convert(countryEntity, country);
            return country;
        }).collect(Collectors.toList());
    }
}
