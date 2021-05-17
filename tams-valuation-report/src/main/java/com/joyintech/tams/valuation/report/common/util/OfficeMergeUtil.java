/*
 * 
 */

package com.joyintech.tams.valuation.report.common.util;

import java.io.*;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficeMergeUtil {

    private static Logger logger = LoggerFactory.getLogger(OfficeMergeUtil.class);
    /**
     * 合并word文档
     * 
     * @param destFile
     * @param srcFile
     * @throws Exception
     * @see
     * @since 1.0
     */
    public static void mergeWord(String destFile,List<String> srcFile)
        throws Exception {
        OPCPackage srcPackage = null;
        XWPFDocument srcDocument = null;
        CTBody srcBody = null;
        XWPFDocument srcDocumentTmp = null;
        CTBody srcBodyTmp = null;
        for (int i = 0; i < srcFile.size(); i++ ) {
            if(i==0){
                try {
                    srcDocumentTmp = new XWPFDocument(OPCPackage.open(new FileInputStream(srcFile.get(i))));
                    srcBodyTmp= srcDocumentTmp.getDocument().getBody();
                }
                catch (IOException | InvalidFormatException e) {
                    throw e;
                }
                finally {
                    if (srcDocumentTmp != null) {
                        srcDocumentTmp.close();
                    }
                }
                continue;
            }
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(srcFile.get(i));
                srcPackage = OPCPackage.open(fileInputStream);
                srcDocument = new XWPFDocument(srcPackage);
                srcBody = srcDocument.getDocument().getBody();
                appendBody(srcBodyTmp, srcBody);
            }
            catch (IOException | InvalidFormatException e) {
                throw e;
            }
            finally {
                if (srcDocument != null) {
                    try {
                        srcDocument.close();
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    try {
                        srcPackage.close();
                    } catch (Exception e) {
                        logger.error("", e);
                    }

                }
            }
        }
        OutputStream dest = null;
        try {
            dest = new FileOutputStream(destFile);
            if (dest != null){
                srcDocumentTmp.write(dest);
            }
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (dest != null) {
                dest.close();
            }
        }
    }

    /**
     * 添加CTBody
     * 
     * @param src
     * @param append
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    private static CTBody appendBody(CTBody src, CTBody append)
        throws Exception {
        if(null == src){
            return append;
        }
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);
        String srcString = src.xmlText();
        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
        String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
        String sufix = srcString.substring(srcString.lastIndexOf("<"));
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
        
        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
        src.set(makeBody);
        return src;
    }

    /**
     * @introduction: 将多个Excel文件中的sheel合并到一个Excel
     * @param outputFileName 生成文件全名
     * @param inputFileNameArray 资源文件全名数组
     */
    public static void mergeExcel(String outputFileName, List<String> inputFileNameArray) {
        XSSFWorkbook outputExcel = null;
        FileOutputStream fos = null;
        try {
            // 生成的excel
            outputExcel = new XSSFWorkbook();
            // 生成的excel中创建sheet
            Sheet writableSheet;
            if (0 == outputExcel.getNumberOfSheets()) {
                writableSheet = outputExcel.createSheet();
            }
            else {
                writableSheet = outputExcel.getSheetAt(0);
            }
            int index = 0;
            for (String fileName : inputFileNameArray) {
                // 创建excel文件的工作簿对象book
                @SuppressWarnings("resource")
                Workbook inputExcel = null;
                try {
                    inputExcel = new XSSFWorkbook(new FileInputStream(new File(fileName)));
                    // 获取excel文件工作簿的工作表数量sheets
                    Sheet sheet = null;
                    for (int i = 0; i < inputExcel.getNumberOfSheets(); i++ ) {// 获取每个Sheet表
                        // 需要合并的源文件中的sheet
                        sheet = inputExcel.getSheetAt(i);

                        // 复制源表中的合并单元格
                        MergerRegion(writableSheet,sheet,index);
                        index = copy(outputExcel,sheet, writableSheet, index);
                    }
                    //每个文件增加n行空行
                    index = index + 2;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputExcel !=null){
                        inputExcel.close();
                    }
                }
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            File outputFile = new File(outputFileName);
            File dir = outputFile.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fos = new FileOutputStream(outputFile);
            outputExcel.write(fos);
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
        finally {
            /** *********关闭文件************* */
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputExcel != null) {
                try {
                    outputExcel.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制原有sheet的合并单元格到新创建的sheet
     * @param sheetCreat 新创建sheet
     * @param sheet 原有的sheet
     * @see
     * @since 1.0
     */
    private static void MergerRegion(Sheet sheetCreat, Sheet sheet,int index) {
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress mergedRegionAt = sheet.getMergedRegion(i);
            mergedRegionAt.setFirstRow(mergedRegionAt.getFirstRow() + index);
            mergedRegionAt.setLastRow(mergedRegionAt.getLastRow() + index);
            sheetCreat.addMergedRegion(mergedRegionAt);
        }
    }
    /**
     * 复制excel单元格
     * @param formSheet
     * @param toWritableSheet
     * @param index
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
    private static int copy(XSSFWorkbook outputExcel,Sheet formSheet, Sheet toWritableSheet, int index) {
        Row row;
        Row rowC;
        Cell srcCell;
        Cell distCell;
        for (int j = 0; j < formSheet.getPhysicalNumberOfRows(); j++ ) {// 获取每行
            row = formSheet.getRow(j);
            rowC = toWritableSheet.createRow(j + index);
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++ ) {// 获取每个单元格
                srcCell = row.getCell(k);
                distCell = rowC.createCell(k);
                
                //样式 start
                CellStyle srcCellStyle = srcCell.getCellStyle();
                XSSFCellStyle newstyle = outputExcel.createCellStyle();  
                copyCellStyle(srcCellStyle, newstyle);
                distCell.setCellStyle(newstyle);
                //样式 end

                CellType srcCellType = srcCell.getCellType();
                if (CellType.NUMERIC.equals(srcCellType)) {
                    if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                        distCell.setCellValue(srcCell.getDateCellValue());
                    }
                    else {
                        distCell.setCellValue(srcCell.getNumericCellValue());
                    }
                }
                else if (CellType.STRING.equals(srcCellType)) {
                    distCell.setCellValue(srcCell.getRichStringCellValue());
                }
                else if (CellType.BLANK.equals(srcCellType)) {
                    // nothing21
                }
                else if (CellType.BOOLEAN.equals(srcCellType)) {
                    distCell.setCellValue(srcCell.getBooleanCellValue());
                }
                else if (CellType.ERROR.equals(srcCellType)) {
                    distCell.setCellErrorValue(srcCell.getErrorCellValue());
                }
                else if (CellType.FORMULA.equals(srcCellType)) {
                    distCell.setCellFormula(srcCell.getCellFormula());
                }
                else { // nothing29

                }
//                distCell.setCellStyle(srcCellStyle);
            }
        }
        index = index + formSheet.getPhysicalNumberOfRows();
        return index;
    }
    
    /**
     * 复制一个单元格样式到目的单元格样式
     * @param fromStyle
     * @param toStyle
     */
    public static void copyCellStyle(CellStyle fromStyle,
            CellStyle toStyle) {
        toStyle.cloneStyleFrom(fromStyle);//此一行代码搞定
        /*
        toStyle.setAlignment(fromStyle.getAlignment());
        //边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        //背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
//      toStyle.setFont(fromStyle.getFont(null));
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());//首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());//旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());
*/
    }
}