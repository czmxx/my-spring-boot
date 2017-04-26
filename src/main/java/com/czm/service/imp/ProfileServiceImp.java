package com.czm.service.imp;

import com.czm.core.util.TransactionalServer;
import com.czm.entity.ProfileCompany;
import com.czm.mapper.ProfileCompanyMapper;
import com.czm.service.ProfileService;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chen zhan mei on 2017/4/25.
 */
//@TransactionalServer
@Service
public class ProfileServiceImp implements ProfileService {
    @Autowired
    private ProfileCompanyMapper profileCompanyMapper;


    @Override
    public void profileCompany(InputStream inputStream) {
        HSSFWorkbook wb = null;
        POIFSFileSystem fs = null;
        HSSFSheet sheet = null;
        int i = 0;
        int name = 0, city = 0, street = 0, country = 0, state = 0, postalCode = 0, vip = 0, stays = 0, communications = 0;
        boolean isBegin = true;
        try {
            fs = new POIFSFileSystem(inputStream);
            wb = new HSSFWorkbook(fs);
            //获取所以的sheet
            for (; i < wb.getNumberOfSheets(); i++) {
                sheet = wb.getSheetAt(i);
                //获取全部的的行
                isBegin = true;
                for (int g = 0; g < sheet.getPhysicalNumberOfRows(); g++) {
                    HSSFRow row = sheet.getRow(g);
                    if (row == null)
                        continue;
                    //获取全部的列
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        if (isBegin) {
                            HSSFCell cell = row.getCell(j);
                            String object = getCellValue(cell);
                            if (object.equals("Name")) {
                                name = j;
                                continue;
                            }
                            if (object.equals("City")) {
                                city = j;
                                continue;
                            }
                            if (object.equals("Street")) {
                                street = j;
                                continue;
                            }
                            if (object.equals("Country")) {
                                country = j;
                                continue;
                            }
                            if (object.equals("State")) {
                                state = j;
                                continue;
                            }
                            if (object.equals("Postal Code")) {
                                postalCode = j;
                                continue;
                            }
                            if (object.equals("VIP")) {
                                vip = j;
                                continue;
                            }
                            if (object.equals("Stays")) {
                                stays = j;
                                continue;
                            }
                            if (object.equals("Communications")) {
                                communications = j;
                                isBegin = false;
                                continue;
                            }
                        } else {
                            if (getCellValue(row.getCell(name)).contains("Filter From Name All") || getCellValue(row.getCell(name - 1)).contains("Filter From Name All")) {
                                break;
                            }
                        }
                    }
                    if (!isBegin) {
                        ProfileCompany company = new ProfileCompany();
                        company.setName(getCellValue(row.getCell(name)));
                        company.setCity(getCellValue(row.getCell(city)));
                        company.setStreet(getCellValue(row.getCell(street)));
                        company.setCountry(getCellValue(row.getCell(country)));
                        company.setState(getCellValue(row.getCell(state)));
                        company.setPostalCode(getCellValue(row.getCell(postalCode)));
                        company.setVip(getCellValue(row.getCell(vip)));
                        company.setStays(getCellValue(row.getCell(stays)));
                        company.setCommunications(getCellValue(row.getCell(communications)));
                        company.setNumber(getCellValue(row.getCell(communications + 1)));
                        company.setNumber2(getCellValue(row.getCell(communications + 2)));
                        profileCompanyMapper.insert(company);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteprofileCompany() {
        List<ProfileCompany> profileCompanies = this.profileCompanyMapper.selectAll();
        profileCompanies.forEach(a -> {
            if (StringUtil.isEmpty(a.getName()) || (StringUtils.isEmpty(a.getNumber()) && StringUtils.isEmpty(a.getNumber2()))) {
                this.profileCompanyMapper.delete(a);
            }
        });
    }

    @Override
    public void deleteProfileCompanyNotMobile() {

        List<ProfileCompany> profileCompanies = this.profileCompanyMapper.selectAll();

        profileCompanies.forEach(a -> {


        });

    }

    private String getCellValue(HSSFCell cell) {
        if (cell == null)
            return "";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}