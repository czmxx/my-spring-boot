package com.czm.service.imp;

import com.czm.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

/**
 * Created by chen zhan mei on 2017/4/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceImpTest {

    @Autowired
    private ProfileService profileService;

//    @Test
//    public void profileCompany() throws Exception {
//
//        this.profileService.profileCompany(new FileInputStream(new File("F://Profile/TravelAgent.xls")));
//    }


    @Test
    public void AnalyticalProfileCompany() throws Exception {

//        this.profileService.AnalyticalProfileCompany();
        for (int i = 1; i <= 20; i++)
            this.profileService.dowonExcel(i);
    }


//    @Test
//    public void profileCompanyDelete() {
//        this.profileService.deleteprofileCompany();
//    }
}