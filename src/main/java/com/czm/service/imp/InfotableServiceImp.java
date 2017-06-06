package com.czm.service.imp;

import com.czm.core.util.TransactionalService;
import com.czm.entity.Infotable;
import com.czm.mapper.InfotableMapper;
import com.czm.service.InfotableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试接开发
 *
 * @author xiaoyin
 * @create 2017-06-02
 */

@TransactionalService
public class InfotableServiceImp implements InfotableService {

    @Autowired
    private InfotableMapper infotableMapper;



    @Override
    public void addInfotable(Infotable infotable) {

    }

    @Override
    public Infotable getInfotableById(int id) {
        return null;
    }

    @Override
    public void deleteInfotableById(int id) {

    }

    @Override
    public List<Infotable> getInfotableAll() {
        return infotableMapper.selectAll();
    }


}
