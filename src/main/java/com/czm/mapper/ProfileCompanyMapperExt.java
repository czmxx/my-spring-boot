package com.czm.mapper;

import com.czm.domain.ProfileResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileCompanyMapperExt {


    List<ProfileResponse> selectLimit(@Param("num") int num);

}