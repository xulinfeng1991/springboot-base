package com.parsec.sb.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * POI操作Excel工具类 for parsec team
 * 代码来源于网上
 * code review by xujiahong
 */
public class POIUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 根据自定义信息，生成 XSSFWorkbook 对象（目前只支持Excel2007）
     *
     * @param titles
     * @param list
     * @param fields
     * @param clazz
     * @param <T>
     * @return
     * @author wuhao
     */
    public static <T> XSSFWorkbook createWorkbook(String[] titles, List list, String[] fields, Class<T> clazz) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet();
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                row.createCell(i).setCellValue(titles[i]);
            }
            for (int i = 1; i <= list.size(); i++) {
                XSSFRow hssfRow = sheet.createRow(i);
//                clazz = (Class<T>) list.get(i - 1).getClass();
                for (int j = 0; j < fields.length; j++) {
                    Field declaredField = clazz.getDeclaredField(fields[j]);
                    declaredField.setAccessible(true);
                    if (declaredField.getType().toString().equals("int")) {
                        hssfRow.createCell(j).setCellValue(declaredField.getInt(list.get(i - 1)));
                    } else if (declaredField.getType().toString().equals("class java.time.LocalDateTime")) {
                        String str = "" + declaredField.get(list.get(i - 1));
                        str = str.replace("T", " ");//格式化时间
                        hssfRow.createCell(j).setCellValue("null".equals(str) ? "" : str);
                    } else {
                        String str = "" + declaredField.get(list.get(i - 1));
                        hssfRow.createCell(j).setCellValue("null".equals(str) ? "" : str);
                    }
                }
            }
            return wb;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据自定义信息，生成 CSV 数据
     *
     * @param titles
     * @param list
     * @param fields
     * @param clazz
     * @param <T>
     * @return
     * @author wuhao
     */
    public static <T> String createCsv(String[] titles, List list, String[] fields, Class<T> clazz) {
        try {
            StringBuffer csvStr = new StringBuffer();
            for (String title : titles) {
                csvStr.append(title + ",");
            }
            csvStr.append("\n");
            for (int i = 1; i <= list.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    Field declaredField = clazz.getDeclaredField(fields[j]);
                    declaredField.setAccessible(true);
                    if (declaredField.getType().toString().equals("int")) {
                        csvStr.append(declaredField.getInt(list.get(i - 1))).append(",");
                    } else {
                        String str = "" + declaredField.get(list.get(i - 1));
                        csvStr.append("null".equals(str) ? "" : str).append(",");
                    }
                }
                csvStr.append("\n");
            }
            return csvStr.toString();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出Excel文件
     *
     * @param response
     * @param title
     * @author xujiahong
     */
    public static void exportExcel(HttpServletResponse response, XSSFWorkbook xssfWorkbook, String fileName) {
        OutputStream ouputStream = null;
        try {
            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");

            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");

            ouputStream = response.getOutputStream();
            xssfWorkbook.write(ouputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ouputStream != null) {
                try {
                    ouputStream.flush();
                    ouputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 简单的一步导出 .xlsx 格式的Excel文件
     * 推荐使用此方法
     *
     * @param response
     * @param fileName
     * @param titles
     * @param fields
     * @param list
     * @param clazz
     * @param <T>
     * @author xujiahong
     */
    public static <T> void exportByOneStep(HttpServletResponse response, String fileName,
                                           String[] titles, String[] fields, List list, Class<T> clazz) {
        XSSFWorkbook xssfWorkbook = createWorkbook(titles, list, fields, clazz);
        exportExcel(response, xssfWorkbook, fileName);
    }

    /**
     * 读入excel文件，解析后返回
     *
     * @param file
     * @throws IOException
     * @author internet
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }

    /**
     * 校验文件
     *
     * @param file
     * @throws IOException
     * @author internet
     */
    private static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            throw new IOException(fileName + "不是excel文件");
        }
    }

    /**
     * 将上传的Excel文件读取为 Workbook 对象
     *
     * @param file
     * @return
     * @throws IOException
     * @author internet
     */
    private static Workbook getWorkBook(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        //获取excel文件的io流
        InputStream is = file.getInputStream();
        //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
        if (fileName.endsWith(xls)) {
            //2003
            workbook = new HSSFWorkbook(is);
        } else if (fileName.endsWith(xlsx)) {
            //2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    /**
     * 读取单元格的值
     *
     * @param cell
     * @return
     * @author internet
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}

