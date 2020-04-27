package com.cocoawork.appstore.service.impl;

import com.cocoawork.appstore.entity.Country;
import com.cocoawork.appstore.mapper.CountryMapper;
import com.cocoawork.appstore.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public Integer addCountry(Country country) {
        return countryMapper.addCountry(country);
    }

    @Override
    public List<Country> getAllCountry() {
        return countryMapper.getAllCountry();
    }


}
