package com.czm.service.imp;

import com.czm.core.util.TransactionalServer;
import com.czm.entity.Logs;
import com.czm.entity.ProfileCompany;
import com.czm.mapper.LogsMapper;
import com.czm.mapper.ProfileCompanyMapper;
import com.czm.service.ProfileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by chen zhan mei on 2017/4/25.
 */
@TransactionalServer
public class ProfileServiceImp implements ProfileService {

    @Autowired
    private ProfileCompanyMapper profileCompanyMapper;
    @Autowired
    private LogsMapper logsMapper;

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
                        if (getCellValue(row.getCell(name)).equals("Name"))
                            continue;
                        ProfileCompany company = new ProfileCompany();
                        company.setName(getCellValue(row.getCell(name)));
                        company.setCity(getCellValue(row.getCell(city)));
                        company.setStreet(getCellValue(row.getCell(street)));
                        company.setCountry(getCellValue(row.getCell(country)));
                        company.setState(getCellValue(row.getCell(state)));
                        company.setPostalCode(getCellValue(row.getCell(postalCode)));
                        //VIP获取 数据不对
                        company.setStays(getCellValue(row.getCell(vip)));
                        company.setCommunications(getCellValue(row.getCell(stays)));
                        company.setNumber(getCellValue(row.getCell(communications)));
                        profileCompanyMapper.insert(company);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AnalyticalProfileCompany() {

        List<ProfileCompany> profileCompanies = profileCompanyMapper.selectAll();

        for (int i = 0; i < profileCompanies.size(); i++) {
            ProfileCompany company = profileCompanies.get(i);
            //只有名字不为空的才新增数据解析
            if (company != null && StringUtils.isNoneEmpty(company.getName()) && StringUtils.isNoneEmpty(company.getCity()) && StringUtils.isNoneEmpty(company.getStreet())) {
                for (int j = 1; j <= 2; j++) {
                    ProfileCompany company1 = profileCompanies.get(j + i);
                    if (company1 != null) {
                        //名字不为空,城市为空,街道为空 表示上一个名字分割的 并且是相邻的一个数据
                        if (j == 1 && StringUtils.isNoneEmpty(company1.getName()) && StringUtils.isEmpty(company1.getCity())) {
                            company.setName(company.getName() + " " + company1.getName());
                            //名字为空,城市为空 ,街道为空 表示 上一个数据遗留的通信方式
                        } else if (StringUtils.isEmpty(company1.getName()) && StringUtils.isEmpty(company1.getCity())) {
                            getCommunications(company1.getPostalCode(), company1.getNumber(), company);
                        } else if (StringUtils.isNoneEmpty(company1.getName()) && StringUtils.isNoneEmpty(company1.getCity())) {
                            break;
                        }
                        this.profileCompanyMapper.updateByPrimaryKey(company);
                        Logs logs = new Logs();
                        logs.setChangeInfo(company.toString());
                        logs.setChangeId(company.getId());
                        logs.setModifyTime(new Date());
                        logsMapper.insert(logs);
                    }
                }
            }
            if (company != null && StringUtils.isNoneEmpty(company.getCommunications()) && StringUtils.isNoneEmpty(company.getName())) {
                getCommunications(company.getCommunications(), company.getNumber(), company);
                company.setCommunications(null);
                company.setNumber(null);
                this.profileCompanyMapper.updateByPrimaryKey(company);
            }
        }

    }

    private void getCommunications(String communication, String number, ProfileCompany profileCompany) {
        if (communication.contains("Email Address")) {
            profileCompany.setEmailAddress(number);
        } else if (communication.contains("Fax Number")) {
            profileCompany.setFaxNumber(number);
        } else if (communication.contains("Business Phone")) {
            profileCompany.setBusinessPhone(number);
        } else if (communication.contains("Mobile Phone")) {
            profileCompany.setMobilePhone(number);
        } else {
            profileCompany.setOtherCommunications(number);
        }
    }

    private String getCellValue(HSSFCell cell) {
        if (cell == null)
            return "";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}