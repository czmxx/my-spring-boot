package com.czm.service;

import com.czm.entity.Infotable;

import java.util.List;

/**
 * Created by chen zhan mei  on 2017/2/15.
 */
public interface InfotableService {

    void addInfotable(Infotable infotable);


    Infotable getInfotableById(int id);

    void deleteInfotableById(int id);

    List<Infotable> getInfotableAll();
}
