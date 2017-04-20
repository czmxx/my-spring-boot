package com.czm.core.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by CHENZHANMEI on 2017/3/12.
 */
public class ExcelUtil {


    public static void downloadExcel(String fileName, String[] sheetName, List<String[]> titleCellRowName, List<List<String>> contentList, HttpServletResponse response) {
        downloadExcel(fileName, sheetName, titleCellRowName, response, contentList);
    }


    public static void downloadExcel(String fileName, String[] sheetName, List<String[]> cellRowName, HttpServletResponse response, List<List<String>>... cells1) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //标题样式
        HSSFCellStyle cellStyle = getColumnTopStyle(workbook, true);
        //主体内容样式
        HSSFCellStyle style = getStyle(workbook);
        HSSFCell cell;
        Pattern pattern = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
        for (int i = 0; i < sheetName.length; i++) {
            HSSFSheet sheet = workbook.createSheet(sheetName[i]);
            HSSFRow rowRowName = sheet.createRow(0);
            String[] titleRowNames = cellRowName.get(i);
            //设置标题栏
            for (int n = 0; n < titleRowNames.length; n++) {
                cell = rowRowName.createCell(n);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(titleRowNames[n]));
            }
            List<List<String>> cells = cells1[i];
            //主体内容
            for (int j = 0; j < cells.size(); ) {
                HSSFRow row = sheet.createRow(j++);
                for (int a = 0; a < cells.get(j).size(); a++) {
                    cell = row.createCell(a);
                    String s = cells.get(j).get(a);
                    cell.setCellStyle(style);
                    if (pattern.matcher(s).matches()) {
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(Double.valueOf(s));
                    } else {
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(s);
                    }
                }
            }
            //自动设置宽度
            for (int colNum = 0; colNum < titleRowNames.length; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                sheet.setColumnWidth(colNum, ((columnWidth + 4) * 256) > (255 * 256) ? (255 * 256) : ((columnWidth + 4) * 256));
            }
        }
        try {
            String file = java.net.URLEncoder.encode(fileName, "UTF-8") + "-" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
            OutputStream out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook, boolean type) {
        //自定义颜色
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //设置字体名字
        font.setFontName("微软雅黑");
        //字体加粗
        if (type) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setColor(HSSFColor.WHITE.index);
        }
        //设置背景颜色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //蓝色
        if (type) {
            palette.setColorAtIndex((short) 11, (byte) (0), (byte) (112), (byte) (192));
            style.setFillForegroundColor((short) 11);
        } else {
            //灰白
            palette.setColorAtIndex((short) 15, (byte) (216), (byte) (216), (byte) (216));
            style.setFillForegroundColor((short) 15);
        }
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    private static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }
}

