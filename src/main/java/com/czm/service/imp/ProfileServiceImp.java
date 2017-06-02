package com.czm.service.imp;

import com.czm.core.util.TransactionalServer;
import com.czm.domain.PageResponse;
import com.czm.domain.ProfileResponse;
import com.czm.entity.ProfileCompany;
import com.czm.mapper.ProfileCompanyMapper;
import com.czm.mapper.ProfileCompanyMapperExt;
import com.czm.service.ProfileService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chen zhan mei on 2017/4/25.
 */
@TransactionalServer
public class ProfileServiceImp implements ProfileService {

    @Autowired
    private ProfileCompanyMapper profileCompanyMapper;

    @Autowired
    private ProfileCompanyMapperExt profileCompanyMapperExt;

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
//                        profileCompanyMapper.insert(company);
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
//                        Logs logs = new Logs();
//                        logs.setChangeInfo(company.toString());
//                        logs.setChangeId(company.getId());
//                        logs.setModifyTime(new Date());
//                        logsMapper.insert(logs);
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

    @Override
    public int dowonExcel(int num) {
        String excelPath = "F://excel/member_import" + num + ".xls";
        Page<Object> objects = PageHelper.startPage(num, 4999);
        List<ProfileCompany> profileCompanies = profileCompanyMapper.selectAll();
        System.out.println("一共有这么多数据 " + objects.getTotal());
        Workbook workbook = null;
        Sheet sheet = null;
        Cell cell = null;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("会员信息");
        Row row0 = sheet.createRow(0);
        sheet.autoSizeColumn(0);
        cell = row0.createCell(0, CellType.STRING);
        cell.setCellValue("姓名");
        cell = row0.createCell(1, CellType.STRING);
        cell.setCellValue("手机号");
        cell = row0.createCell(2, CellType.STRING);
        cell.setCellValue("电子邮件");
        cell = row0.createCell(3, CellType.STRING);
        cell.setCellValue("身份证号");
        cell = row0.createCell(4, CellType.STRING);
        cell.setCellValue("会员类型");
        cell = row0.createCell(5, CellType.STRING);
        cell.setCellValue("会员等级");

        for (int i = 0; i < profileCompanies.size(); i++) {
            Row row = sheet.createRow(i + 1);
            ProfileCompany p = profileCompanies.get(i);
            sheet.autoSizeColumn(i);
            for (int c = 0; c < 6; c++) {
                CellStyle style = workbook.createCellStyle();
                Font headerFont = workbook.createFont(); // 字体
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(HSSFColor.RED.index);
                headerFont.setFontName("宋体");
                style.setFont(headerFont);
                style.setWrapText(true);
                cell = row.createCell(c, CellType.STRING);
                switch (c) {
                    case 0:
                        cell.setCellValue(StringUtils.isEmpty(p.getName()) ? "" : p.getName());
                        break;
                    case 1:
                        cell.setCellValue(StringUtils.isEmpty(p.getMobilePhone()) ? "" : p.getMobilePhone());
                        break;
                    case 2:
                        cell.setCellValue(StringUtils.isEmpty(p.getEmailAddress()) ? "" : p.getEmailAddress());
                        break;
                    case 3:
                        cell.setCellValue("");
                        break;
                    case 4:
                        cell.setCellValue("个人会员");
                        break;
                    case 5:
                        cell.setCellValue("星会员");
                        break;
                }

            }
        }
        if (workbook == null) {
            System.out.println("为空");
            return 0;
        }
        //自动设置宽度
        for (int colNum = 1; colNum < profileCompanies.size(); colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    Cell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == CellType.STRING.getCode()) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, ((columnWidth - 2) * 256) > (255 * 256) ? (255 * 256) : ((columnWidth - 2) * 256));
            } else {
                sheet.setColumnWidth(colNum, ((columnWidth + 4) * 256) > (255 * 256) ? (255 * 256) : ((columnWidth + 4) * 256));
            }
        }
        File sss = new File(excelPath);
        try {
            FileOutputStream outputStream = new FileOutputStream(sss);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profileCompanies.size();
    }

    @Override
    public PageResponse getPage(int num, int size) {

        List<ProfileResponse> profileResponses = profileCompanyMapperExt.selectLimit(1);
        profileResponses.forEach(a -> {
            System.out.println(a.getName());
        });

        return null;
    }

    private CellStyle getStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 设置单元格字体
        Font headerFont = workbook.createFont(); // 字体
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(HSSFColor.RED.index);
        headerFont.setFontName("宋体");
        style.setFont(headerFont);
        style.setWrapText(true);
        // 设置单元格边框及颜色
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setWrapText(true);
        return style;
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


    public static void main(String[] args) {
        System.out.println(10001 / 10000);
    }
}
//
//class NewExcel {
//
//
//    private String excelPath = "data.xlsx";
//
//    public static void main(String[] args) throws Exception {
//        NewExcel excel = new NewExcel();
//        if (excel.createExcelFile()) {
//            System.out.println("data.xlsx is created successfully.");
//        }
//    }
//
//    public boolean createExcelFile() {
//        boolean isCreateSuccess = false;
//        Workbook workbook = null;
//        try {
//            workbook = new XSSFWorkbook();
//        } catch (Exception e) {
//            System.out.println("It cause Error on CREATING excel workbook: ");
//            e.printStackTrace();
//        }
//        if (workbook != null) {
//            Sheet sheet = workbook.createSheet("testdata");
//            Row row0 = sheet.createRow(0);
//            for (int i = 0; i < 11; i++) {
//                Cell cell_1 = row0.createCell(i, Cell.CELL_TYPE_STRING);
//                cell_1.setCellValue(i);
//                sheet.autoSizeColumn(i);
//            }
//            for (int rowNum = 1; rowNum < 200; rowNum++) {
//                Row row = sheet.createRow(rowNum);
//                for (int i = 0; i < 11; i++) {
//                    Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
//                    cell.setCellValue("cell" + String.valueOf(rowNum + 1) + String.valueOf(i + 1));
//                }
//            }
//            try {
//                FileOutputStream outputStream = new FileOutputStream(excelPath);
//                workbook.write(outputStream);
//                outputStream.flush();
//                outputStream.close();
//                isCreateSuccess = true;
//            } catch (Exception e) {
//                System.out.println("It cause Error on WRITTING excel workbook: ");
//                e.printStackTrace();
//            }
//        }
//
//        File sss = new File(excelPath);
//        System.out.println(sss.getAbsolutePath());
//        return isCreateSuccess;
//    }
//
//
//    public String getExcelPath() {
//        return this.excelPath;
//    }
//
//    public void setExcelPath(String excelPath) {
//        this.excelPath = excelPath;
//    }
//}