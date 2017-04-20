package com.czm.service;

import com.czm.entity.Login;

import java.util.List;

/**
 * Created by chen zhan mei  on 2017/2/15.
 */
public interface CityService {

    void addLogin(Login login);

    List<Login> getLoginAll();

    Login getLoginById(Long id);

    void deleteLoginById(Long id);
}
