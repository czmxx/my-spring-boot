package com.czm.service.imp;

import com.czm.core.exceptions.MsgException;
import com.czm.core.util.PwdUtil;
import com.czm.core.util.TransactionalService;
import com.czm.core.util.ValidUtil;
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
import java.util.Date;
import java.util.List;

/**
 * Created by CHENZHANMEI on 2017/6/2.
 * 该接口用于用户相关的接口
 */
@TransactionalService
public class LoginServiceImp extends BaseService implements LoginService {

    @Autowired
    private LoginMapperExt loginMapperExt;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public ResponseDomain login(String keyword, String password, HttpServletRequest request) {

        if (StringUtils.isEmpty(keyword) || StringUtils.isEmpty(password))
            return fail("参数异常!");

        Login login = checkLogin(keyword, 0);
        if (null == login)
            return fail("用户不存在!请注册!");
        if (!PwdUtil.verifyPassword(password, login.getPasswordHash(), login.getPasswordSalt())) {
            return fail("密码错误!");
        }
        HttpSession session = request.getSession();
        session.setAttribute("login", "success");
        session.setAttribute("nickName", login.getNickName());
        session.setAttribute("handerImage", login.getHanderImage());
        return success();
    }

    @Override
    public ResponseDomain register(String mobile, String email, String nickName, String headImageUrl, String password) {

        if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(email))
            return fail("手机号或者邮件不能为空!");

        if (StringUtils.isEmpty(password))
            return fail("密码不能为空!");

        if (checkLogin(mobile, 0) != null || checkLogin(email, 0) != null)
            return fail("该账户已存在!");

        if (!StringUtils.isEmpty(mobile) && !ValidUtil.isMobile(mobile))
            return fail("手机号错误!");

        Login login = new Login();
        login.setEmail(StringUtils.isEmpty(mobile) ? null : mobile);
        login.setMobile(StringUtils.isEmpty(email) ? null : email);
        login.setHanderImage(headImageUrl);
        login.setNickName(nickName);
        String salt = PwdUtil.generatePasswordSalt();
        login.setPasswordSalt(salt);
        login.setPasswordHash(PwdUtil.getPasswordHash(password, salt));
        login.setStatus((byte) 0);
        login.setDeletedFlag(0L);
        login.setCreatedBy(1L);
        login.setCreatedOn(new Date());
        return validResult(this.loginMapper.insert(login) > 0);
    }


    /**
     * 用于校验用户是否存在
     *
     * @param keyword
     * @param status
     * @return
     */
    private Login checkLogin(String keyword, int status) {

        List<Login> logins = this.loginMapperExt.selectByKeyword(keyword, status);
        return logins.size() > 0 ? logins.get(0) : null;
    }

}
