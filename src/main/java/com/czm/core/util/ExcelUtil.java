package com.czm.core.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by CHENZHANMEI on 2017/3/12.
 */
public class ExcelUtil {


    public void downloadExcel(String fileName, String[] sheetName, List<String[]> titleCellRowName, List<List<String>> contentList, HttpServletResponse response) {
        downloadExcel(fileName, sheetName, titleCellRowName, response, contentList);
    }

    public void downloadExcel(String fileName, String[] sheetName, List<String[]> cellRowName, HttpServletResponse response, List<List<String>>... cells1) {
        SXSSFWorkbook wb = new SXSSFWorkbook(10000);
        CellStyle cellStyle = getColumnTopStyle(wb, true);
        CellStyle style = getStyle(wb);
        Cell cell;
        for (int i = 0; i < sheetName.length; i++) {
            Sheet sheet = wb.createSheet(sheetName[i]);
            Row row1 = sheet.createRow(0);
            String[] titleRowNames = cellRowName.get(i);
            //设置标题栏
            for (int n = 0; n < titleRowNames.length; n++) {
                cell = row1.createCell(n);
                cell.setCellType(CellType.STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(titleRowNames[n]);
            }
            List<List<String>> cells = cells1[i];
            //主体内容
            for (int j = 0; j < cells.size(); j++) {
                Row row = sheet.createRow(j + 1);
                for (int a = 0; a < cells.get(j).size(); a++) {
                    cell = row.createCell(a);
                    cell.setCellType(CellType.STRING);
                    cell.setCellStyle(style);
                    cell.setCellValue(cells.get(j).get(a));
                }
            }
            //自动设置宽度
            for (int colNum = 1; colNum < titleRowNames.length; colNum++) {
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
        }
        try {
            String file = java.net.URLEncoder.encode(fileName, "UTF-8") + "-" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xlsx";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
            OutputStream out = response.getOutputStream();
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private CellStyle getColumnTopStyle(SXSSFWorkbook workbook, boolean type) {
        //自定义颜色
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        // 设置字体
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 12);
        //设置字体名字
        //字体加粗
        if (type) {
            font.setBold(true);
            font.setColor(HSSFColor.WHITE.index);
        }
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //设置背景颜色
        //蓝色
        byte[] bytes = {(byte) 11, (byte) 0, (byte) 112, (byte) 192};
        style.setFillForegroundColor(new XSSFColor(bytes));
        setXSSFCellStyle(style);
        style.setFont(font);
        return style;
    }

    private CellStyle getStyle(SXSSFWorkbook workbook) {
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        CellStyle style = workbook.createCellStyle();
        setXSSFCellStyle(style);
        style.setFont(font);
        return style;
    }

    private void setXSSFCellStyle(CellStyle style) {
        BorderStyle dashed = BorderStyle.THIN;
        //颜色
        short black = HSSFColor.BLACK.index;
        //设置底边框;
        style.setBorderBottom(dashed);
        //设置底边框颜色;
        style.setBottomBorderColor(black);
        //设置左边框;
        style.setBorderLeft(dashed);
        //设置左边框颜色;
        style.setLeftBorderColor(black);
        //设置右边框;
        style.setBorderRight(dashed);
        //设置右边框颜色;
        style.setRightBorderColor(black);
        //设置顶边框;
        style.setBorderTop(dashed);
        //设置顶边框颜色;
        style.setTopBorderColor(black);
        //在样式用应用设置的字体;
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }
}

