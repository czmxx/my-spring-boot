package com.czm.service.imp;

import com.czm.core.util.TransactionalServer;
import com.czm.entity.ProfileCompany;
import com.czm.mapper.ProfileCompanyMapper;
import com.czm.service.ProfileService;
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

/**
 * Created by chen zhan mei on 2017/4/25.
 */
@TransactionalServer
public class ProfileServiceImp implements ProfileService {
    @Autowired
    private ProfileCompanyMapper profileCompanyMapper;

    @Override
    public void profileCompany(InputStream inputStream) {
        HSSFWorkbook wb = null;
        POIFSFileSystem fs = null;
        HSSFSheet sheet = null;
        try {
            fs = new POIFSFileSystem(inputStream);
            wb = new HSSFWorkbook(fs);
            //获取所以的sheet
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                sheet = wb.getSheetAt(i);
                //获取所以的的行
                for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                    HSSFRow row = sheet.getRow(j);
                    if (row == null)
                        continue;
                    ProfileCompany company = new ProfileCompany();
                    HSSFCell cell = row.getCell(1);
                    if (cell == null)
                        continue;
                    company.setName(getCellValue(row.getCell(2)));
                    company.setCity(getCellValue(row.getCell(3)));
                    company.setStreet(getCellValue(row.getCell(4)));
                    company.setCountry(getCellValue(row.getCell(5)));
                    company.setState(getCellValue(row.getCell(6)));
                    company.setPostalCode(getCellValue(row.getCell(7)));
                    company.setVip(getCellValue(row.getCell(8)));
                    company.setCommunications(getCellValue(row.getCell(9)));
                    company.setNumber(getCellValue(row.getCell(10)));
                    profileCompanyMapper.insert(company);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getCellValue(HSSFCell cell) {
        if (cell == null)
            return "";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}