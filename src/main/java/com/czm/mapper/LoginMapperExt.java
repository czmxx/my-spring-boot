package com.czm.mapper;

import com.czm.entity.Login;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapperExt {

    /**
     * 用于 通过 昵称 手机 邮件查询用户
     *
     * @param keyword 关键字
     * @param status  状态
     * @return
     */
    List<Login> selectByKeyword(@Param("keyword") String keyword, @Param("status") int status);
}