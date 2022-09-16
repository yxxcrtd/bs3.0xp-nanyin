/*
 * 
 */

package com.joyintech.tams.disclosure.common.common.util;

import java.io.*;
import java.util.List;

import com.joyintech.framework.file.SafelyFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficeMergeUtil {
    /**
     * 日志
     */
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
         {
        OPCPackage srcPackage = null;
        XWPFDocument srcDocument = null;
        CTBody srcBody = null;
        XWPFDocument srcDocumentTmp = null;
        CTBody srcBodyTmp = null;
        FileInputStream fileInputStream = null;

        for (int i = 0; i < srcFile.size(); i++ ) {
            if(i==0){
                try {
                    fileInputStream = new FileInputStream(srcFile.get(i));
                    srcDocumentTmp = new XWPFDocument(OPCPackage.open(fileInputStream));
                    srcBodyTmp= srcDocumentTmp.getDocument().getBody();
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
                finally {
                    try {
                        if (fileInputStream != null){
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        logger.error(e.getMessage(),e);
                    }
                }
                continue;
            }

            try {
                fileInputStream = new FileInputStream(srcFile.get(i));
                srcPackage = OPCPackage.open(fileInputStream);
                srcDocument = new XWPFDocument(srcPackage);
                srcBody = srcDocument.getDocument().getBody();
                appendBody(srcBodyTmp, srcBody);
            }catch (Exception e)
            {
                logger.error(e.getMessage(),e);
            }
            finally {
                try {
                    if(fileInputStream!=null) {
                        fileInputStream.close();
                    }
                    if(srcPackage!=null) {
                        srcPackage.close();
                    }
                    if(srcDocument!=null) {
                        srcDocument.close();
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
             OutputStream dest = null;
             try {
                 dest = new FileOutputStream(new SafelyFile(destFile));
                 if(srcDocumentTmp != null){
                     srcDocumentTmp.write(dest);
                 }
             } catch (Exception e) {
                 logger.error(e.getMessage(),e);
             }finally {
                 try {
                     if(dest!=null)
                     {
                         dest.close();
                     }
                 if (srcDocument != null){
                     srcDocument.close();
                 }
                 } catch (IOException e) {
                     logger.error(e.getMessage(),e);
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
            throws XmlException {
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
        Workbook inputExcel = null;
        FileOutputStream fos = null;
        FileInputStream fis = null;
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
                //@SuppressWarnings("resource")
                fis =  new FileInputStream(new SafelyFile(fileName));
                inputExcel = new XSSFWorkbook(fis);
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
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            File outputFile = new SafelyFile(outputFileName);
            File dir = outputFile.getParentFile();
            if (!dir.exists()) {
                boolean ret = dir.mkdirs();
                if ( ret==false ) {
                	logger.error("创建目录错误："+dir.getName());
                }
            }
            fos = new FileOutputStream(outputFile);
            outputExcel.write(fos);
            }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            logger.error(pw.toString() + "_" + e.getMessage(), e);
//            System.out.println(sw.toString());
            logger.info(sw.toString());
            pw.close();
            try {
				sw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        finally {
            try {
                if(inputExcel != null){
                    inputExcel.close();
                }
                if(outputExcel != null){
                    outputExcel.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
            try {
                if(fos != null){
                    fos.close();
                }
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
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
    	
    	//update by leipng 2.1.9升级报错  目前暂无使用.注释掉
//        Row row;
//        Row rowC;
//        Cell srcCell;
//        Cell distCell;
//        for (int j = 0; j < formSheet.getPhysicalNumberOfRows(); j++ ) {// 获取每行
//            row = formSheet.getRow(j);
//            rowC = toWritableSheet.createRow(j + index);
//            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++ ) {// 获取每个单元格
//                srcCell = row.getCell(k);
//                distCell = rowC.createCell(k);
//                
//                //样式 start
//                CellStyle srcCellStyle = srcCell.getCellStyle();
//                XSSFCellStyle newstyle = outputExcel.createCellStyle();  
//                copyCellStyle(srcCellStyle, newstyle);
//                distCell.setCellStyle(newstyle);
//                //样式 end
//
//                int srcCellType = srcCell.getCellType();
//                if (srcCellType == HSSFCellType.NUMERIC) {
//                    if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
//                        distCell.setCellValue(srcCell.getDateCellValue());
//                    }
//                    else {
//                        distCell.setCellValue(srcCell.getNumericCellValue());
//                    }
//                }
//                else if (srcCellType == HSSFCellType.STRING) {
//                    distCell.setCellValue(srcCell.getRichStringCellValue());
//                }
//                else if (srcCellType == HSSFCellType.BLANK) {
//                    // nothing21
//                }
//                else if (srcCellType == HSSFCellType.BOOLEAN) {
//                    distCell.setCellValue(srcCell.getBooleanCellValue());
//                }
//                else if (srcCellType == HSSFCellType.ERROR) {
//                    distCell.setCellErrorValue(srcCell.getErrorCellValue());
//                }
//                else if (srcCellType == HSSFCellType.FORMULA) {
//                    distCell.setCellFormula(srcCell.getCellFormula());
//                }
//                else { // nothing29
//
//                }
////                distCell.setCellStyle(srcCellStyle);
//            }
//        }
//        index = index + formSheet.getPhysicalNumberOfRows();
        return 0;
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