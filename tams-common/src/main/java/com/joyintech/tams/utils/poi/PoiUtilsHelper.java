package com.joyintech.tams.utils.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.utils.echarts.EchartsException;

/**
 * 主要功能：实现将docx文档中的参数，用指定的参数集合替换功能
 * 包括图片
 */
public class PoiUtilsHelper {

    /**
     * 替换docx文档，并生成docx文档
     * @param srcFile，源文件
     * @param os，目标输出流
     * @param params，替换的变量集合
     * @return
     * @throws PoiException
     */
    public static boolean mergeParasToDocx(String srcFile, OutputStream os, Map<String, Object> params) throws PoiException {
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcFile));

            // 替换段落中的指定文字
            //标记特殊的集合替换
            boolean startflag = false;
            boolean endflag = true;

            Iterator<XWPFParagraph> itParagraph = document.getParagraphsIterator();
            while (itParagraph.hasNext()) {                                       //遍历段落
                XWPFParagraph paragraph = (XWPFParagraph) itParagraph.next();
                List<XWPFRun> runs = paragraph.getRuns();                         //取出段落中的runs
                //System.out.println("============");
                int idx = 0;
                String paracode="";
                for (int i = 0; i < runs.size(); i++) {                           //遍历runs，每个run才是word中操作的最小单元
                    XWPFRun run = runs.get(i);
                    if (run==null ) continue;
                    idx = i;
                    String oneparaString = run.getText(run.getTextPosition());  //getTextPosition: 取纯文本的起始位置，本行代码取出本run中的纯文本信息（不含控制符信息）
                    //System.out.println("oneparaString======="+oneparaString);
                    if ( oneparaString==null ) continue;

                    //判断获取的参数是否完整
                    paracode+=oneparaString;
//					if ( paracode.equals("<"))
//						System.out.println("hhhhhhhh");
                    if ( paracodeIsComplete(paracode)==false ) {  //删除不完整的变量设置，如${xx，但后续没有 }，或者<，后续没有>
                        run.setText("", 0);
                        continue;
                    }

                    if ( paracode==null ) continue;

                    //System.out.println("替换前："+paracode);
                    if ( paracode!=null && paracode.indexOf("<foreach:")!=-1) {
                        startflag = true;
                        endflag = false;
                    }

                    //2.替换集合文本，如：
                    //开始：<foreach:mylist>债券名称：${mylist.aa},债券费用：${mylist.bb}测试,${mylist.cc},${mylist.dd}</foreach>结束
                    if ( startflag==true && endflag==false && paracode!=null) {
                        //datalist.append(paracode);
                        run.setText("", 0);
                        if ( paracode.indexOf("</foreach>")!=-1) {
                            startflag = false;
                            endflag=true;
                            if ( paracode!=null && paracode.length()>0){
                                String value = updateTextForList(paracode,params);
                                //datalist.setLength(0);
                                runs.get(idx).setText(value, 0);
                                paracode="";
                            }
                        }
                        continue;
                    }

                    if ( paracode!=null && paracode.length()>0 ) {

                        //先判断是否是图片标志
                        String re = "\\$\\{@image:([^\\}]+)\\}";
                        Pattern p = Pattern.compile(re);
                        Matcher m = p.matcher(paracode);
                        List<String> imageList = new ArrayList<>();
                        while(m.find()){
                            imageList.add(m.group(1));
                        }
                        if ( imageList.size()>0 ) {               //替换图片
                            for (String image : imageList) {
                                String key = "${@image:"+image+"}";
                                updateImage(run, (Map) params.get(key));
                            }
                        } else {

                            //1.替换普通文本，如：本理财产品名称为${prodName}
                            paracode = updateTextForPlain(paracode, params);
                            //System.out.println("替换后："+paracode);
                            run.setText(paracode, 0);
                        }

                        paracode = "";
                    }
                }
                //System.out.println("============");

            }

            // 替换表格中的指定文字
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int rcount = table.getNumberOfRows();
                String keystring = table.getRow(1).getCell(0).getText().trim();
                if ( keystring!=null && keystring.length()>8) {
                    if ( keystring.substring(1,8)!=null && keystring.substring(1,8).equals("foreach") ) {  //4.特殊的循环表，根据数据条数，动态创建表的行
                        if ( updateTableForList(table,params)<0)
                            return false;
                        continue;
                    }
                }
                for (int i = 0; i < rcount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        String cellTextString = cell.getText();

                        //5.表格中的集合数据：表格中的一个单元格，填写集合数据
                        if ( cellTextString.indexOf("<foreach:")!=-1&& cellTextString.indexOf("</foreach>")!=-1)  {
                            String value = updateTextForList(cellTextString,params);
                            cell.removeParagraph(0);
                            cell.setText(value);
                        } else {  //3.普通表格，根据单元格，直接填写变量
                            cellTextString = updateTextForPlain(cellTextString,params);
//							for (Entry<String, Object> e : params.entrySet()) {
//								if (cellTextString.contains(e.getKey()) && e.getValue()!=null)
//									cellTextString = cellTextString.replace(
//											e.getKey(), e.getValue().toString());
//							}
                            cell.removeParagraph(0);
                            cell.setText(cellTextString);
                        }
                    }
                }
            }
            document.write(os);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PoiException("FileTemplateExceptioin",new Throwable("找不到模板或者模板配置错误，请使用office2010格式编辑."),"" );
        }
    }

    /**
     * 表格中第一行、第二行填写规则，根据数据集合条数，动态创建表的行数，并填充数据
     * 模板设置如下格式：
     资产类型	上限	下限	备注
     <foreach:mylist>
     ${mylist.a}	${mylist.b}	${mylist.c}	${mylist.d}
     * @param table
     * @param params
     * @return
     */
    private static int updateTableForList(XWPFTable table,
                                          Map<String, Object> params) {

        //规则行，取出list名称
        XWPFTableRow ruleRow1 = table.getRow(1);
        String rule = ruleRow1.getCell(0).getText();
        int beginIndex = rule.indexOf(':');
        int endIndex = rule.indexOf('>');
        String listname=rule.substring(beginIndex+1,endIndex);
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> list = (List<Map<String,Object>>)params.get(listname);

        //获取明细列的规则
        XWPFTableRow ruleRow2 = table.getRow(2);
        List<String> cols = new ArrayList<String>();
        List<XWPFTableCell> ruleCells = ruleRow2.getTableCells();
        for (XWPFTableCell cell : ruleCells) {
            String cellTextString = cell.getText();
            String prop="";
            if ( cellTextString!=null ) {
                int idx = cellTextString.indexOf('.');
                int endIdx = cellTextString.indexOf('}');
                if ( idx!=-1 && endIdx!=-1) {
                    prop = cellTextString.substring(idx+1,endIdx);
                    if ( prop!=null )
                        cols.add(prop);
                }
            }
        }

        //填充数据
        if ( list!=null ) {
            for ( int i=0;i<list.size();i++) {
                XWPFTableRow dataRow = table.createRow();
                Map<String,Object> data = list.get(i);
                List<XWPFTableCell> cells = dataRow.getTableCells();

                for ( int j=0;j<cells.size();j++) {
                    XWPFTableCell cell = cells.get(j);
                    if ( cell.getParagraphs().size()>0 )
                        cell.removeParagraph(0);
                    Object o = data.get(cols.get(j));
                    String cellString="";
                    if ( o!=null )
                        cellString = o.toString();
                    if (cellString==null) cellString="";
                    cell.setText(cellString);
                }
            }
        }
        //删除规则行
        table.removeRow(1);  //删除规则行1
        table.removeRow(1);  //删除规则行2

        return list==null?0:list.size();
    }

    /**
     * 替换集合文本
     * 例如：<foreach:mylist>债券名称：${mylist.aa},债券费用：${mylist.bb}测试,${mylist.cc},${mylist.dd}</foreach>
     * @param rule
     * @param params
     * @return
     */
    private static String updateTextForList(String rule,Map<String, Object> params) {
        //System.out.println("rule----------"+rule);
        StringBuilder value = new StringBuilder();

        int beginIndex = rule.indexOf(':');
        int endIndex = rule.indexOf('>');
        String listname=rule.substring(beginIndex+1,endIndex);
        beginIndex = endIndex+1;
        endIndex = rule.indexOf("</foreach>");
        rule = rule.substring(beginIndex,endIndex);
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> list = (List<Map<String,Object>>)params.get(listname);

//		if ( list==null )
//			return rule;

        //获取明细列的规则
        List<String> cols = new ArrayList<String>();
        String re = "\\$\\{([^\\}]+)\\}";
        Pattern p = Pattern.compile(re);
        Matcher m = p.matcher(rule);
        while(m.find()){
            cols.add(m.group(1));
        }

        //填充数据
        if ( list!=null && list.size()>0) {
            for ( int i=0;i<list.size();i++) {
                Map<String,Object> data = list.get(i);
                String str1= rule;
                for ( String str:cols) {
                    String key=str.substring(str.indexOf('.')+1);
                    Object o = data.get(key);
                    str1 = str1.replace("${"+str+"}",o==null?"":o.toString());
                }
                value.append(str1);
                value.append(";");
            }
        } else {
            String str1= rule;
            for ( String str:cols) {
                str1 = str1.replace("${"+str+"}","");
            }
            value.append(str1);
            value.append(";");
        }
        //System.out.println("value---------"+value.toString());
        return value.toString();
    }

    /**
     * 替换普通的文本
     * 例如：本理财产品名称为${prodName}
     * @param text
     * @param params
     * @return
     */
    private static String updateTextForPlain(String text,Map<String, Object> params) {
        //System.out.println("普通文本替换前："+text);

        //获取明细列的规则
        List<String> cols = new ArrayList<String>();
        String re = "\\$\\{([^\\}]+)\\}";
        Pattern p = Pattern.compile(re);
        Matcher m = p.matcher(text);
        while(m.find()){
            cols.add(m.group(1));
        }

        //填充数据
        for ( String str:cols) {
            String key="${"+str+"}";
            Object o = params.get(str);
            
            if ( o instanceof Date ) {
            	o = DateUtil.format((Date)o);
            }
            
            text = text.replace(key,o==null?" ":o.toString());
        }
        //System.out.println("普通文本替换后："+text);
        return text;
    }

    private static void updateImage(XWPFRun run,Map<String,Object> pic) throws EchartsException {
        int width = Integer.parseInt(pic.get("width").toString());
        int height = Integer.parseInt(pic.get("height").toString());
        int picType = getPictureType(pic.get("type").toString());
        String byteArray = (String) pic.get("content");

        //System.out.println("==" + byteArray);
        FileInputStream fis = null;
        try {
            if ( byteArray!=null )
                fis = new FileInputStream(byteArray);
            run.addPicture(fis, getPictureType("png"), "", Units.toEMU(width), Units.toEMU(height));
            run.setText(" ",0);
        } catch (Exception e ) {
            throw new EchartsException("updateImage",e.getCause(),"");
        } finally {
            if ( fis!=null ) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 判断参数代码是否完整，判断标志：
     * 如果前面有<，后面必须有>，否则不完整
     * 或者如果前面有${，后面必须有}，否则不完整
     * 如果都没有，则是完整的字符串
     * @param paracode
     * @return
     */
    private static boolean paracodeIsComplete(String paracode) {
        //boolean ret =false;
        int beginpos = paracode.indexOf('<');
        if ( beginpos!=-1) {  //是关键字"<"
            if ( paracode.substring(beginpos+1).indexOf('>')!=-1 )  //完整
                return true;
            else
                return false;
        }else {  //不是关键字"<"
            beginpos = paracode.indexOf("${");
            if ( beginpos!=-1) {  //是关键字"${"
                if ( paracode.substring(beginpos+1).indexOf('}')!=-1 )  //完整
                    return true;
                else
                    return false;
            }else {  //不是关键字
                return true;
            }
        }
    }

    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = Document.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = Document.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = Document.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = Document.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = Document.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = Document.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }


}
