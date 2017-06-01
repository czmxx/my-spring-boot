package com.czm.mapper;

import com.czm.domain.ProfileResponse;
import com.czm.entity.ProfileCompany;
import com.czm.uitl.MyMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileCompanyMapper extends MyMapper<ProfileCompany> {
    //List<ProfileResponse> selectLimit(@Param("num") int num);
}