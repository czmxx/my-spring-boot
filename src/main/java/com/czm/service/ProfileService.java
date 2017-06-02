package com.czm.service;

import com.czm.domain.PageResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chen zhan mei on 2017/4/25.
 */
public interface ProfileService {

    void profileCompany(InputStream inputStream) throws IOException;


    void AnalyticalProfileCompany();


    int dowonExcel(int num);

    PageResponse  getPage(int num,int size);


}
