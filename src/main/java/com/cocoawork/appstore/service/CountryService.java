package com.cocoawork.appstore.service;

import com.cocoawork.appstore.entity.Country;

import java.util.List;

public interface CountryService {

    Integer addCountry(Country country);

    List<Country> getAllCountry();
}
