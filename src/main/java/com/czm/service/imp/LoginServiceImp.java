package com.czm.service.imp;

import com.czm.core.exceptions.MyException;
import com.czm.core.util.TransactionalServer;
import com.czm.domain.BaseService;
import com.czm.domain.ResponseDomain;
import com.czm.entity.Login;
import com.czm.mapper.LoginMapper;
import com.czm.mapper.LoginMapperExt;
import com.czm.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by CHENZHANMEI on 2017/6/2.
 * 该接口用于用户相关的接口
 */
@TransactionalServer
public class LoginServiceImp extends BaseService implements LoginService {

    @Autowired
    private LoginMapperExt loginMapperExt;

    @Override
    public ResponseDomain login(String keyword, String password, HttpServletRequest request) {

        if (StringUtils.isEmpty(keyword) || StringUtils.isEmpty(password))
            throw new MyException("参数异常!");

        List<Login> logins = this.loginMapperExt.selectByKeyword(keyword, 0);
        if (logins.size() == 0)
            throw new MyException("没有改用户");

        Login login = logins.get(0);
        if (!password.equals(login.getPasswordHash())) {
            throw new MyException("密码错误!");
        }
        HttpSession session = request.getSession();
        session.setAttribute("login", "success");
        session.setAttribute("nickName", login.getNickName());
        session.setAttribute("handerImage", login.getHanderImage());
        return success();
    }

}
