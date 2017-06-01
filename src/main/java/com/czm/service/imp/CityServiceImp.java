package com.czm.service.imp;

import com.czm.core.util.TransactionalServer;
import com.czm.entity.Login;
import com.czm.entity.LoginExample;
import com.czm.mapper.LoginMapper;
import com.czm.service.CityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created by chen zhan mei  on 2017/2/15.
 */
@TransactionalServer
//@Service
public class CityServiceImp implements CityService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public void addLogin(Login login) {
        PageHelper.startPage(1, 10);
        this.loginMapper.insert(login);
//        throw new MyException("出错了");
    }

    @Override
    public List<Login> getLoginAll() {
        return loginMapper.selectAll();
    }

    @Override
    public Login getLoginById(Long id) {
        LoginExample example = new LoginExample();
        example.createCriteria().andIdEqualTo(id);
        return loginMapper.selectByExample(example).get(0);
    }

    @Override
    public void deleteLoginById(Long id) {
        Login loginById = getLoginById(id);
        loginMapper.delete(loginById);
    }

    @Override
    public boolean getLoginByName(String name,String password){
        LoginExample example = new LoginExample();
        example.createCriteria().andNameEqualTo(name);
        Optional<Login> first = loginMapper.selectByExample(example).stream().findFirst();
        if(first.isPresent()){
            return password.equals(first.get().getPassword());
        }
        return false;
    }

}
