package com.cocoawork.appstore.service;

import com.cocoawork.appstore.entity.Country;

import java.util.List;

public interface CountryService {

    public Integer addCountry(Country country);

    public List<Country> getAllCountry();
}
