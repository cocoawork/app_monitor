package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {


    public Integer addCountry(Country country);

    public List<Country> getAllCountry();
}
